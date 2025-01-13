package com.example.dbeaver.facade;

import com.example.dbeaver.criteria.Condition;
import com.example.dbeaver.criteria.Criteria;
import com.example.dbeaver.criteria.conditions.*;
import com.example.dbeaver.dto.response.*;
import com.example.dbeaver.entity.account.Account;
import com.example.dbeaver.entity.contact.Contact;
import com.example.dbeaver.entity.lead.Lead;
import com.example.dbeaver.entity.opportunity.Opportunity;
import com.example.dbeaver.exception.WrongIdException;
import com.example.dbeaver.mapper.response.ResponseMapper;
import com.example.dbeaver.repository.AccountCriteriaRepository;
import com.example.dbeaver.repository.ContactCriteriaRepository;
import com.example.dbeaver.repository.LeadCriteriaRepository;
import com.example.dbeaver.repository.OpportunityCriteriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class DefaultFacade implements Facade {
    private final AccountCriteriaRepository accountRepository;
    private final ContactCriteriaRepository contactRepository;
    private final LeadCriteriaRepository leadRepository;
    private final OpportunityCriteriaRepository opportunityRepository;
    private final ResponseMapper responseMapper;

    @Override
    public ResponseLeadDTO findLeadById(String id, LocalDate date) {
        Criteria<Contact> contactCriteria = new Criteria<>();
        this.setConditionsForAll(date, contactCriteria);
        Lead lead = leadRepository.findById(id, new Criteria<>()).orElseThrow(WrongIdException::new);
        if (lead.getContact() == null) {
            return responseMapper.mapToLeadDTO(lead, null, null);
        }
        Account account = lead.getContact().getAccount(); // TODO OWNER OR CONTACT // checked only contact
        List<Contact> contacts = List.of(lead.getContact());
        if (account == null) {
            log.info("account id null; contact id = " + lead.getContact().getId());
        } else {
            contactCriteria.addCondition(new EqualCondition<>("account", account));
            contacts = contactRepository.findAll(contactCriteria);
        }
        return responseMapper.mapToLeadDTO(lead, account, contacts);
    }

    @Override
    public ResponseContactDTO findContactById(String id, LocalDate date) {
        Criteria<Lead> leadCriteria = new Criteria<>();
        Criteria<Opportunity> opportunityCriteria = new Criteria<>();
        this.setConditionsForAll(date, leadCriteria, opportunityCriteria);
        Contact contact = contactRepository.findById(id, new Criteria<>()).orElseThrow(WrongIdException::new);
        Account account = contact.getAccount();
        leadCriteria.addCondition(new EqualCondition<>("contact", contact)); // checked
        List<Lead> leads = leadRepository.findAll(leadCriteria);
        opportunityCriteria.addCondition(new EqualCondition<>("owner", contact)); // checked all null by date
        List<Opportunity> opportunities = opportunityRepository.findAll(opportunityCriteria);
        return responseMapper.mapToContactDTO(contact, leads, opportunities, account);
    }

    @Override
    public ResponseOpportunityDTO findOpportunityById(String id, LocalDate date) {
        Criteria<Contact> contactCriteria = new Criteria<>();
        Opportunity opportunity = opportunityRepository.findById(id, new Criteria<>()).orElseThrow(WrongIdException::new);
        Account account = opportunity.getOwner().getAccount();
        List<Contact> contacts = List.of(opportunity.getOwner());
        if (account == null) {
            log.info("account id null; contact id = " + id);
        } else {
            contactCriteria.addCondition(new EqualCondition<>("account", account));
            contacts = contactRepository.findAll(contactCriteria);
        }
        return responseMapper.mapToOpportunityDTO(opportunity, contacts, account);
    }

    @Override
    public ResponseCompanyDTO findCompanyById(String id, LocalDate date) {
        Criteria<Opportunity> opportunityCriteria = new Criteria<>();
        Criteria<Lead> leadCriteria = new Criteria<>();
        this.setConditionsForAll(date, leadCriteria, opportunityCriteria);
        Account account = accountRepository.findById(id, new Criteria<>()).orElseThrow(WrongIdException::new);
        Criteria<Contact> contactCriteria = new Criteria<>();
        contactCriteria.addCondition(new EqualCondition<>("account", account));
        List<Contact> contacts = contactRepository.findAll(contactCriteria);
        List<Lead> leads = contacts.parallelStream()
                .filter(Objects::nonNull)
                .map(contact -> {
                    Criteria<Lead> localLeadCriteria = new Criteria<>(leadCriteria); // leads after 01.03.2024
                    localLeadCriteria.addCondition(new EqualCondition<>("contact", contact)); // checked contact
                    return leadRepository.findAll(localLeadCriteria);
                })
                .flatMap(List::parallelStream).toList();
        opportunityCriteria.addCondition(new EqualCondition<>("account", account));
        List<Opportunity> opportunities = opportunityRepository.findAll(opportunityCriteria);
        return responseMapper.mapToCompanyDTO(account, leads, opportunities, contacts);
    }

    private void setConditionsForAll(LocalDate date, Criteria<?>... criterias) {
        for (Criteria<?> criteria : criterias) {
            criteria.addCondition(new GreaterThanCondition<>("createdOn", LocalDateTime.of(date, LocalTime.of(0, 0))));
//            criteria.addCondition(new IsNotNullCondition<>("id"));
//            criteria.unionConditionWithAnd();
        }
    }

    @Override
    public List<ResponseLeadDTO> findLeads(int limit, int offset, LocalDate localDate) {
        Criteria<Lead> leadCriteria = new Criteria<>();
        leadCriteria.setLimit(limit);
        leadCriteria.setOffset(offset);
        this.setConditionsForAll(localDate, leadCriteria);
        leadCriteria.addCondition(new IsNotNullCondition<>("id"));
        List<ResponseLeadDTO> result = leadRepository.findAll(leadCriteria).parallelStream()
                .map(lead -> findLeadById(lead.getId(), localDate)).toList();
        return result;
    }

    @Override
    public List<ResponseContactDTO> findContacts(int limit, int offset, LocalDate localDate) {
        Criteria<Contact> contactCriteria = new Criteria<>();
        contactCriteria.setLimit(limit);
        contactCriteria.setOffset(offset);
        this.setConditionsForAll(localDate, contactCriteria);
        List<ResponseContactDTO> result = contactRepository.findAll(contactCriteria).parallelStream()
                .map(contact -> findContactById(contact.getId(), localDate)).toList();
        return result;
    }

    @Override
    public List<ResponseOpportunityDTO> findOpportunities(int limit, int offset, LocalDate localDate) {
        Criteria<Opportunity> opportunityCriteria = new Criteria<>();
        opportunityCriteria.setLimit(limit);
        opportunityCriteria.setOffset(offset);
        this.setConditionsForAll(localDate, opportunityCriteria);
        List<ResponseOpportunityDTO> result = opportunityRepository.findAll(opportunityCriteria).parallelStream()
                .map(opportunity -> findOpportunityById(opportunity.getId(), localDate)).toList();
        return result;
    }

    @Override
    public List<ResponseCompanyDTO> findCompanies(int limit, int offset, LocalDate localDate) {
        Criteria<Account> accountCriteria = new Criteria<>();
        accountCriteria.setLimit(limit);
        accountCriteria.setOffset(offset);
        this.setConditionsForAll(localDate, accountCriteria);
        List<ResponseCompanyDTO> result = accountRepository.findAll(accountCriteria).parallelStream()
                .map(account -> findCompanyById(account.getId(), localDate)).toList();
        return result;
    }

    @Override
    public long countAllLeads(LocalDate localDate) {
        Criteria<Lead> criteria = new Criteria<>();
        this.setConditionsForAll(localDate, criteria);
        return leadRepository.countAll(criteria);
    }

    @Override
    public long countAllCompanies(LocalDate localDate) {
        Criteria<Account> criteria = new Criteria<>();
        this.setConditionsForAll(localDate, criteria);
        return accountRepository.countAll(criteria);
    }

    @Override
    public long countAllContacts(LocalDate localDate) {
        Criteria<Contact> criteria = new Criteria<>();
        this.setConditionsForAll(localDate, criteria);
        return contactRepository.countAll(criteria);
    }

    @Override
    public long countAllOpportunities(LocalDate localDate) {
        Criteria<Opportunity> criteria = new Criteria<>();
        this.setConditionsForAll(localDate, criteria);
        return opportunityRepository.countAll(criteria);
    }

    @Override
    public List<ResponseCompanyWithContactsDTO> findCompaniesWithContacts(int limit, int offset, LocalDate date) {
        Criteria<Account> accountCriteria = new Criteria<>();
        accountCriteria.setLimit(limit);
        accountCriteria.setOffset(offset);
        this.setConditionsForAll(date, accountCriteria);

        List<ResponseCompanyWithContactsDTO> result = accountRepository.findAll(accountCriteria).stream()
                .map(account -> {
                    Criteria<Contact> contactCriteria = new Criteria<>();
                    contactCriteria.addCondition(new EqualCondition<>("account", account));
                    List<Contact> contacts = contactRepository.findAll(contactCriteria);
                    return responseMapper.mapToCompanyWithContactsDTO(account, contacts);
                }).toList();
        return result;
    }

    @Override
    public List<ResponseContactWithoutCompanyDTO> findContactsWithoutCompany(int limit, int offset, LocalDate date) {
        Criteria<Contact> contactCriteria = new Criteria<>();
        setConditionsForAll(date, contactCriteria);

        Criteria<Account> accountCriteria = new Criteria<>();
        accountCriteria.addCondition(new LessThanCondition<>("createdOn", date));

        return contactRepository.findContactsJoinAccounts(contactCriteria, accountCriteria).stream().map(responseMapper::mapToContactWithoutCompanyDTO).toList();
    }

    @Override
    public long countContactsWithoutCompanies(LocalDate date) {
        Criteria<Contact> contactCriteria = new Criteria<>();
        setConditionsForAll(date, contactCriteria);

        Criteria<Account> accountCriteria = new Criteria<>();
        accountCriteria.addCondition(new LessThanCondition<>("createdOn", date));

        return contactRepository.countContactsJoinAccounts(contactCriteria, accountCriteria);
    }
}
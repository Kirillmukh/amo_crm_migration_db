package com.example.dbeaver.facade;

import com.example.dbeaver.criteria.Criteria;
import com.example.dbeaver.criteria.conditions.EqualCondition;
import com.example.dbeaver.criteria.conditions.GreaterThanCondition;
import com.example.dbeaver.criteria.conditions.IsNotNullCondition;
import com.example.dbeaver.dto.response.ResponseCompanyDTO;
import com.example.dbeaver.dto.response.ResponseContactDTO;
import com.example.dbeaver.dto.response.ResponseLeadDTO;
import com.example.dbeaver.dto.response.ResponseOpportunityDTO;
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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DefaultFacade implements Facade {
    private final AccountCriteriaRepository accountRepository;
    private final ContactCriteriaRepository contactRepository;
    private final LeadCriteriaRepository leadRepository;
    private final OpportunityCriteriaRepository opportunityRepository;
    private final ResponseMapper responseMapper;

    @Override
    public ResponseLeadDTO findLeadById(String id) {
        Criteria<Contact> contactCriteria = new Criteria<>();
        this.setConditionsForAll(contactCriteria);
        Lead lead = leadRepository.findById(id, new Criteria<>()).orElseThrow(WrongIdException::new);
        if (lead.getContact() == null) {
            return responseMapper.mapToLeadDTO(lead, null, null);
        }
        Account account = lead.getContact().getAccount(); // TODO OWNER OR CONTACT // checked only contact
        List<Contact> contacts = List.of(lead.getContact());
        if (account == null) {
            System.out.println("account id null; contact id = " + lead.getContact().getId());
        } else {
            contactCriteria.addCondition(new EqualCondition<>("account", account));
            contacts = contactRepository.findAll(contactCriteria);
        }
        return responseMapper.mapToLeadDTO(lead, account, contacts);
    }

    @Override
    public ResponseContactDTO findContactById(String id) {
        Criteria<Lead> leadCriteria = new Criteria<>();
        Criteria<Opportunity> opportunityCriteria = new Criteria<>();
        this.setConditionsForAll(leadCriteria, opportunityCriteria);
        Contact contact = contactRepository.findById(id, new Criteria<>()).orElseThrow(WrongIdException::new);
        Account account = contact.getAccount();
        leadCriteria.addCondition(new EqualCondition<>("contact", contact)); // checked
        List<Lead> leads = leadRepository.findAll(leadCriteria);
        opportunityCriteria.addCondition(new EqualCondition<>("owner", contact)); // checked all null by date
        List<Opportunity> opportunities = opportunityRepository.findAll(opportunityCriteria);
        return responseMapper.mapToContactDTO(contact, leads, opportunities, account);
    }

    @Override
    public ResponseOpportunityDTO findOpportunityById(String id) {
        Criteria<Contact> contactCriteria = new Criteria<>();
        Opportunity opportunity = opportunityRepository.findById(id, new Criteria<>()).orElseThrow(WrongIdException::new);
        Account account = opportunity.getOwner().getAccount();
        List<Contact> contacts = List.of(opportunity.getOwner());
        if (account == null) {
            System.out.println("account id null; contact id = " + id);
        } else {
            contactCriteria.addCondition(new EqualCondition<>("account", account));
            contacts = contactRepository.findAll(contactCriteria);
        }
        return responseMapper.mapToOpportunityDTO(opportunity, contacts, account);
    }

    @Override
    public ResponseCompanyDTO findCompanyById(String id) {
        Criteria<Opportunity> opportunityCriteria = new Criteria<>();
        Criteria<Lead> leadCriteria = new Criteria<>();
        this.setConditionsForAll(leadCriteria, opportunityCriteria);
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
        List<Opportunity> opportunities = contacts.parallelStream()// all nulls because sql
                .filter(Objects::nonNull)
                .map(contact -> {
                    Criteria<Opportunity> localOpportunityCriteria = new Criteria<>(opportunityCriteria); // opportunities after 01.03.2024 // all null
                    localOpportunityCriteria.addCondition(new EqualCondition<>("owner", contact)); // checked owner
                    return opportunityRepository.findAll(localOpportunityCriteria);
                })
                .flatMap(List::parallelStream)
                .toList();
//        opportunityCriteria.addCondition(new EqualCondition<>("accountid", account.getId()));
//        List<Opportunity> opportunities = opportunityRepository.findAll(opportunityCriteria);
        return responseMapper.mapToCompanyDTO(account, leads, opportunities, contacts);
    }

    private void setConditionsForAll(Criteria<?>... criterias) {
        for (Criteria<?> criteria : criterias) {
            criteria.addCondition(new GreaterThanCondition<>("createdOn", LocalDateTime.of(2024, 3, 1, 0, 0)));
            criteria.addCondition(new IsNotNullCondition<>("id"));
            criteria.unionConditionWithAnd();
        }
    }

    @Override
    public List<ResponseLeadDTO> findLeads(int limit, int offset) {
        Criteria<Lead> leadCriteria = new Criteria<>();
        leadCriteria.setLimit(limit);
        leadCriteria.setOffset(offset);
        this.setConditionsForAll(leadCriteria);
        leadCriteria.addCondition(new IsNotNullCondition<>("id"));
        List<ResponseLeadDTO> result = leadRepository.findAll(leadCriteria).parallelStream()
                .map(lead -> findLeadById(lead.getId())).toList();
        return result;
    }

    @Override
    public List<ResponseContactDTO> findContacts(int limit, int offset) {
        Criteria<Contact> contactCriteria = new Criteria<>();
        contactCriteria.setLimit(limit);
        contactCriteria.setOffset(offset);
        this.setConditionsForAll(contactCriteria);
        List<ResponseContactDTO> result = contactRepository.findAll(contactCriteria).parallelStream()
                .map(contact -> findContactById(contact.getId())).toList();
        return result;
    }

    @Override
    public List<ResponseOpportunityDTO> findOpportunities(int limit, int offset) {
        Criteria<Opportunity> opportunityCriteria = new Criteria<>();
        opportunityCriteria.setLimit(limit);
        opportunityCriteria.setOffset(offset);
        this.setConditionsForAll(opportunityCriteria);
        List<ResponseOpportunityDTO> result = opportunityRepository.findAll(opportunityCriteria).parallelStream()
                .map(opportunity -> findOpportunityById(opportunity.getId())).toList();
        return result;
    }

    @Override
    public List<ResponseCompanyDTO> findCompanies(int limit, int offset) {
        Criteria<Account> accountCriteria = new Criteria<>();
        accountCriteria.setLimit(limit);
        accountCriteria.setOffset(offset);
        this.setConditionsForAll(accountCriteria);
        List<ResponseCompanyDTO> result = accountRepository.findAll(accountCriteria).parallelStream()
                .map(account -> findCompanyById(account.getId())).toList();
        return result;
    }
}

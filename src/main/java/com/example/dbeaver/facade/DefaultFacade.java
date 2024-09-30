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
import com.example.dbeaver.repository.criteria.AccountCriteriaRepository;
import com.example.dbeaver.repository.criteria.ContactCriteriaRepository;
import com.example.dbeaver.repository.criteria.LeadCriteriaRepository;
import com.example.dbeaver.repository.criteria.OpportunityCriteriaRepository;
import lombok.RequiredArgsConstructor;
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
        if (lead.getOwner() == null) {
            return null;
        }
        Account account = lead.getOwner().getAccount(); // TODO OWNER OR CONTACT
        contactCriteria.addCondition(new EqualCondition<>("account", account));
        List<Contact> contacts = contactRepository.findAll(contactCriteria);
        return responseMapper.mapToLeadDTO(lead, account, contacts);
    }

    @Override
    public ResponseContactDTO findContactById(String id) {
        Criteria<Lead> leadCriteria = new Criteria<>();
        Criteria<Opportunity> opportunityCriteria = new Criteria<>();
        this.setConditionsForAll(leadCriteria, opportunityCriteria);
        Contact contact = contactRepository.findById(id, new Criteria<>()).orElseThrow(WrongIdException::new);
        Account account = contact.getAccount();
        if (account == null) {
            System.out.println("account id null; contact id = " + id);
        }
        leadCriteria.addCondition(new EqualCondition<>("contact", contact));
        List<Lead> leads = leadRepository.findAll(leadCriteria);
        opportunityCriteria.addCondition(new EqualCondition<>("owner", contact));
        List<Opportunity> opportunities = opportunityRepository.findAll(opportunityCriteria);
        return responseMapper.mapToContactDTO(contact, leads, opportunities, account);
    }

    @Override
    public ResponseOpportunityDTO findOpportunityById(String id) {
        Criteria<Contact> contactCriteria = new Criteria<>();
        Opportunity opportunity = opportunityRepository.findById(id, new Criteria<>()).orElseThrow(WrongIdException::new);
        if (opportunity.getOwner() == null) {
            return null;
        }
        Account account = opportunity.getOwner().getAccount(); // TODO OWNER OR CONTACT
        contactCriteria.addCondition(new EqualCondition<>("account", account.getId()));
        List<Contact> contacts = contactRepository.findAll(contactCriteria);
        return responseMapper.mapToOpportunityDTO(opportunity, contacts, account);
    }

    @Override
    public ResponseCompanyDTO findCompanyById(String id) {
        Criteria<Contact> contactCriteria = new Criteria<>();
        Criteria<Opportunity> opportunityCriteria = new Criteria<>();
        Criteria<Lead> leadCriteria = new Criteria<>();
        this.setConditionsForAll(leadCriteria, opportunityCriteria);
        Account account = accountRepository.findById(id, new Criteria<>()).orElseThrow(WrongIdException::new);
        contactCriteria.addCondition(new EqualCondition<>("account", account));
        List<Contact> contacts = contactRepository.findAll(contactCriteria);
        List<Lead> leads = contacts.stream()
                .map(contact -> {
                    Criteria<Lead> localLeadCriteria = new Criteria<>(leadCriteria);
                    localLeadCriteria.addCondition(new EqualCondition<>("owner", contact));
                    return leadRepository.findAll(localLeadCriteria);
                })
                .flatMap(List::stream).toList();
        List<Opportunity> opportunities = contacts.stream()
                .map(contact -> {
                    Criteria<Opportunity> localOpportunityCriteria= new Criteria<>(opportunityCriteria);
                    localOpportunityCriteria.addCondition(new EqualCondition<>("owner", contact));
                    return opportunityRepository.findAll(localOpportunityCriteria);
                })
                .flatMap(List::stream)
                .toList();
//        opportunityCriteria.addCondition(new EqualCondition<>("accountid", account.getId()));
//        List<Opportunity> opportunities = opportunityRepository.findAll(opportunityCriteria);
        return responseMapper.mapToCompanyDTO(account, leads, opportunities, contacts);
    }

    @Override
    public List<ResponseLeadDTO> findLeads()  {
        Criteria<Lead> leadCriteria = new Criteria<>();
        this.setConditionsForAll(leadCriteria);
        List<Lead> leads = leadRepository.findAll(leadCriteria);
        List<ResponseLeadDTO> result = leads.stream().map(lead -> findLeadById(lead.getId())).toList();
        return result;
    }

    @Override
    public List<ResponseContactDTO> findContacts() {
        Criteria<Contact> contactCriteria = new Criteria<>();
        this.setConditionsForAll(contactCriteria);
        List<Contact> contacts = contactRepository.findAll(contactCriteria);
        List<ResponseContactDTO> result = contacts.stream()
                .filter(Objects::nonNull)
                .map(contact -> findContactById(contact.getId())).toList();
        return result;
    }

    @Override
    public List<ResponseOpportunityDTO> findOpportunities() {
        Criteria<Opportunity> opportunityCriteria = new Criteria<>();
        this.setConditionsForAll(opportunityCriteria);
        List<Opportunity> opportunities = opportunityRepository.findAll(opportunityCriteria);
        List<ResponseOpportunityDTO> result = opportunities.stream()
                .map(opportunity -> findOpportunityById(opportunity.getId())).toList();
        return result;
    }

    @Override
    public List<ResponseCompanyDTO> findCompanies() {
        Criteria<Account> accountCriteria = new Criteria<>();
        this.setConditionsForAll(accountCriteria);
        List<Account> accounts = accountRepository.findAll(accountCriteria);
        List<ResponseCompanyDTO> result = accounts.stream()
                .map(account -> findCompanyById(account.getId())).toList();
        return result;
    }

    private void setConditionsForAll(Criteria<?> ... criteries) {
        for (Criteria<?> criteria : criteries) {
            criteria.addCondition(new GreaterThanCondition<>("createdOn", LocalDateTime.of(2024, 3, 1, 0, 0)));
            criteria.addCondition(new IsNotNullCondition<>("id"));
            criteria.unionConditionWithAnd();
        }
    }


}

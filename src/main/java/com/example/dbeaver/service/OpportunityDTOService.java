package com.example.dbeaver.service;

import com.example.dbeaver.criteria.Criteria;
import com.example.dbeaver.criteria.conditions.GreaterThanCondition;
import com.example.dbeaver.dto.util.ActivityByCompanyDTO;
import com.example.dbeaver.dto.util.ContactByCompanyDTO;
import com.example.dbeaver.dto.util.OpportunityByCompanyDTO;
import com.example.dbeaver.entity.account.Activity;
import com.example.dbeaver.entity.contact.Contact;
import com.example.dbeaver.entity.opportunity.Opportunity;
import com.example.dbeaver.mapper.OpportunityActivityMapper;
import com.example.dbeaver.mapper.OpportunityContactMapper;
import com.example.dbeaver.mapper.OpportunityOpportunityMapper;
import com.example.dbeaver.repository.account.AccountRepository;
import com.example.dbeaver.repository.queries.OpportunityCriteriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpportunityDTOService {
    private final OpportunityOpportunityMapper opportunityMapper;
    private final OpportunityActivityMapper activityMapper;
    private final OpportunityContactMapper contactMapper;
    private final AccountRepository accountRepository;
    public List<OpportunityByCompanyDTO> getOpportunityByCompanyDTO(String accountId) {
        return accountRepository.findOpportunities(accountId).stream()
                .map(opportunityMapper::mapToDTO).toList();
    }
    public List<ActivityByCompanyDTO> getActivityByCompanyDTO(String accountId) {
        return accountRepository.findActivities(accountId).stream()
                .map(activityMapper::mapToDTO).toList();
    }
    public List<ContactByCompanyDTO> getContactByCompanyDTO(String accountId) {
        return accountRepository.findContacts(accountId).stream()
                .map(contactMapper::mapToDTO).toList();
    }

    // CRITERIA TODO CLEAN ^
    private final OpportunityCriteriaRepository criteriaRepository;

    public List<ContactByCompanyDTO> getContactByCompanyDTO() {
        Criteria<Contact> criteria = new Criteria<>();
        criteria.addCondition(new GreaterThanCondition<>("createdOn", LocalDateTime.of(2024, 3, 1, 0, 0)));
        return this.criteriaRepository.getContactsByCompany(criteria).stream()
                .map(contactMapper::mapToDTO).toList();
    }
    public List<OpportunityByCompanyDTO> getOpportunityByCompanyDTO() {
        Criteria<Opportunity> criteria = new Criteria<>();
        criteria.addCondition(new GreaterThanCondition<>("createdOn", LocalDateTime.of(2024, 3, 1, 0, 0)));
        return this.criteriaRepository.getSellsByCompany(criteria).stream()
                 .map(opportunityMapper::mapToDTO).toList();
    }
    public List<ActivityByCompanyDTO> getActivityByCompanyDTO() {
        Criteria<Activity> criteria = new Criteria<>();
        criteria.addCondition(new GreaterThanCondition<>("createdOn", LocalDateTime.of(2024, 3, 1, 0, 0)));
        return this.criteriaRepository.getActivitiesByCompany(criteria).stream()
                 .map(activityMapper::mapToDTO).toList();
    }

}

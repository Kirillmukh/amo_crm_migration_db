package com.example.dbeaver.facade;

import com.example.dbeaver.criteria.Criteria;
import com.example.dbeaver.criteria.conditions.GreaterThanCondition;
import com.example.dbeaver.dto.AccountDTO;
import com.example.dbeaver.dto.ContactDTO;
import com.example.dbeaver.dto.LeadDTO;
import com.example.dbeaver.dto.OpportunityDTO;
import com.example.dbeaver.dto.response.ResponseDTO;
import com.example.dbeaver.dto.util.ActivityByCompanyDTO;
import com.example.dbeaver.dto.util.ContactByCompanyDTO;
import com.example.dbeaver.dto.util.OpportunityByAccountDTO;
import com.example.dbeaver.dto.util.OpportunityByCompanyDTO;
import com.example.dbeaver.entity.account.Account;
import com.example.dbeaver.entity.contact.Contact;
import com.example.dbeaver.entity.lead.Lead;
import com.example.dbeaver.entity.opportunity.Opportunity;
import com.example.dbeaver.mapper.AccountMapper;
import com.example.dbeaver.mapper.ContactMapper;
import com.example.dbeaver.mapper.LeadMapper;
import com.example.dbeaver.mapper.OpportunityMapper;
import com.example.dbeaver.repository.criteria.AccountCriteriaRepository;
import com.example.dbeaver.repository.criteria.ContactCriteriaRepository;
import com.example.dbeaver.repository.criteria.LeadCriteriaRepository;
import com.example.dbeaver.repository.criteria.OpportunityCriteriaRepository;
import com.example.dbeaver.service.AccountDTOService;
import com.example.dbeaver.service.OpportunityDTOService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultFacade implements Facade {
    private final AccountCriteriaRepository accountRepository;
    private final AccountDTOService accountDTOService;
    private final AccountMapper accountMapper;
    private final ContactCriteriaRepository contactRepository;
    private final ContactMapper contactMapper;
    private final LeadCriteriaRepository leadRepository;
    private final LeadMapper leadMapper;
    private final OpportunityCriteriaRepository opportunityRepository;
    private final OpportunityDTOService opportunityDTOService;
    private final OpportunityMapper opportunityMapper;
    @Override
    public ResponseDTO getResponse() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setAccountDTOList(getAccountDTO());
        responseDTO.setContactDTOList(getContactDTO());
        responseDTO.setLeadDTOList(getLeadDTO());
        responseDTO.setOpportunityDTOList(getOpportunityDTO());
        return responseDTO;
    }
    private List<AccountDTO> getAccountDTO() {
        Criteria<Account> criteria = new Criteria<>();
        setConditionsForAll(criteria);
        List<Account> accounts = accountRepository.findAll(criteria);
        List<AccountDTO> result = new ArrayList<>(accounts.size());
        for (Account account : accounts) {
            List<OpportunityByAccountDTO> opportunityByAccountDTO = accountDTOService.getOpportunityByAccountDTO(account);
            result.add(accountMapper.mapToDTO(account, opportunityByAccountDTO));
        }
        return result;
    }
    private List<ContactDTO> getContactDTO() {
        Criteria<Contact> criteria = new Criteria<>();
        setConditionsForAll(criteria);
        List<ContactDTO> result = contactRepository.findAll(criteria).parallelStream().map(contactMapper::mapToDTO).toList();
        return result;
    }
    private List<LeadDTO> getLeadDTO() {
        Criteria<Lead> criteria = new Criteria<>();
        setConditionsForAll(criteria);
        List<LeadDTO> result = leadRepository.findAll(criteria).parallelStream().map(leadMapper::mapToDTO).toList();
        return result;
    }
    private List<OpportunityDTO> getOpportunityDTO() {
        Criteria<Opportunity> criteria = new Criteria<>();
        setConditionsForAll(criteria);
        List<Opportunity> opportunities = opportunityRepository.findAll(criteria);
        List<OpportunityDTO> result = new ArrayList<>(opportunities.size());
        for (Opportunity opportunity : opportunities) {
            List<ContactByCompanyDTO> contactByCompanyDTO = opportunityDTOService.getContactByCompanyDTO(opportunity.getAccount());
            List<OpportunityByCompanyDTO> opportunityByCompanyDTO = opportunityDTOService.getOpportunityByCompanyDTO(opportunity.getAccount());
            List<ActivityByCompanyDTO> activityByCompanyDTO = opportunityDTOService.getActivityByCompanyDTO(opportunity.getAccount());
            result.add(opportunityMapper.mapToDTO(opportunity, contactByCompanyDTO, opportunityByCompanyDTO, activityByCompanyDTO));
        }
        return result;
    }

    private void setConditionsForAll(Criteria<?> criteria) {
        criteria.addCondition(new GreaterThanCondition<>("createdOn", LocalDateTime.of(2024, 3, 1, 0, 0)));
    }

}

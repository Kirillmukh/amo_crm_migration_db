package com.example.dbeaver.service;

import com.example.dbeaver.dto.util.ActivityByCompanyDTO;
import com.example.dbeaver.dto.util.ContactByCompanyDTO;
import com.example.dbeaver.dto.util.OpportunityByCompanyDTO;
import com.example.dbeaver.mapper.OpportunityActivityMapper;
import com.example.dbeaver.mapper.OpportunityContactMapper;
import com.example.dbeaver.mapper.OpportunityOpportunityMapper;
import com.example.dbeaver.repository.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}

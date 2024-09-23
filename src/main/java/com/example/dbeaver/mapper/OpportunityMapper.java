package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.OpportunityDTO;
import com.example.dbeaver.entity.opportunity.Opportunity;
import com.example.dbeaver.repository.account.AccountRepository;
import com.example.dbeaver.service.OpportunityDTOService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpportunityMapper {
    private final ContactMapper contactMapper;
    private final OpportunityDTOService opportunityDTOService;
    public OpportunityDTO mapToDTO(Opportunity opportunity) {
        String accountId = opportunity.getAccount().getId();
        return new OpportunityDTO(
                opportunity.getTitle(),
                opportunity.getEvent().getName(),
                opportunity.getAmount(),
                opportunity.getPersonsCount(),
                opportunity.getOwner().getName(),
                opportunity.getOpportunityType().getName(),
                opportunity.getOpportunityDepartment().getName(),
                opportunity.getCreatedOn(),
                opportunity.getDescription(),
                contactMapper.mapToDTO(opportunity.getContact()),
                opportunityDTOService.getOpportunityByCompanyDTO(accountId),
                opportunityDTOService.getActivityByCompanyDTO(accountId),
                opportunityDTOService.getContactByCompanyDTO(accountId)
        );
    }
}

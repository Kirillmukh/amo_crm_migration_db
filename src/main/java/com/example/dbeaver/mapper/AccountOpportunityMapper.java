package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.util.OpportunityByAccountDTO;
import com.example.dbeaver.entity.opportunity.Opportunity;
import org.springframework.stereotype.Component;

@Component
public class AccountOpportunityMapper {
    public OpportunityByAccountDTO mapToDTO(Opportunity opportunity) {
        OpportunityByAccountDTO dto = new OpportunityByAccountDTO();
        dto.setTitle(opportunity.getTitle());
        dto.setStage(opportunity.getOpportunityStage().getName());
        dto.setEvent(opportunity.getEvent().getName());
        dto.setAmount(opportunity.getAmount());
        return dto;
    }
}

package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.util.OpportunityByCompanyDTO;
import com.example.dbeaver.entity.opportunity.Opportunity;
import org.springframework.stereotype.Component;

@Component
public class OpportunityOpportunityMapper {
    public OpportunityByCompanyDTO mapToDTO(Opportunity opportunity) {
        OpportunityByCompanyDTO dto = new OpportunityByCompanyDTO();
        dto.setTitle(opportunity.getTitle());
        dto.setStage(opportunity.getOpportunityStage().getName());
        dto.setAmount(opportunity.getAmount());
        return dto;
    }
}

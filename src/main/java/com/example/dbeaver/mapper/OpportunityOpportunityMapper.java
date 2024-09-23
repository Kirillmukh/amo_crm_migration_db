package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.util.OpportunityByCompanyDTO;
import com.example.dbeaver.entity.opportunity.Opportunity;
import org.springframework.stereotype.Component;

@Component
public class OpportunityOpportunityMapper {
    public OpportunityByCompanyDTO mapToDTO(Opportunity opportunity) {
        return new OpportunityByCompanyDTO(
                opportunity.getTitle(),
                opportunity.getOpportunityStage().getName(),
                opportunity.getAmount()
        );
    }
}

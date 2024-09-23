package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.util.OpportunityByAccountDTO;
import com.example.dbeaver.entity.opportunity.Opportunity;
import org.springframework.stereotype.Component;

@Component
public class AccountOpportunityMapper {
    public OpportunityByAccountDTO mapToDTO(Opportunity opportunity) {
        return new OpportunityByAccountDTO(
                opportunity.getTitle(),
                opportunity.getOpportunityStage().getName(),
                opportunity.getEvent().getName(),
                opportunity.getAmount()
        );
    }
}

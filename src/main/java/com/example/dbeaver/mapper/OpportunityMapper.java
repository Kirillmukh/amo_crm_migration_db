package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.ContactDTO;
import com.example.dbeaver.dto.OpportunityDTO;
import com.example.dbeaver.entity.opportunity.Opportunity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpportunityMapper {
    private final ContactMapper contactMapper;
    public OpportunityDTO mapToDTO(Opportunity opportunity) {
        ContactDTO contact = opportunity.getContact() != null ? contactMapper.mapToDTO(opportunity.getContact()) : null;
        String department = opportunity.getOpportunityDepartment() != null ? opportunity.getOpportunityDepartment().getName() : null;
        String event = opportunity.getEvent() != null ? opportunity.getEvent().getName() : null;
        OpportunityDTO dto = new OpportunityDTO();
        dto.setTitle(opportunity.getTitle());
        dto.setUsrEventInOpportunity(event);
        dto.setAmount(opportunity.getAmount());
        dto.setUsrUchastnikov(opportunity.getPersonsCount());
        dto.setOwner(opportunity.getOwner().getName());
        dto.setType(opportunity.getOpportunityType().getName());
        dto.setDepartment(department);
        dto.setCreatedOn(opportunity.getCreatedOn());
        dto.setDescription(opportunity.getDescription());
        dto.setContactInOpportunity(contact);
        return dto;
    }
}

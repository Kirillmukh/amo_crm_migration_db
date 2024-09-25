package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.OpportunityDTO;
import com.example.dbeaver.dto.util.ActivityByCompanyDTO;
import com.example.dbeaver.dto.util.ContactByCompanyDTO;
import com.example.dbeaver.dto.util.OpportunityByCompanyDTO;
import com.example.dbeaver.entity.opportunity.Opportunity;
import com.example.dbeaver.repository.account.AccountRepository;
import com.example.dbeaver.service.OpportunityDTOService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OpportunityMapper {
    private final ContactMapper contactMapper;
    private final OpportunityDTOService opportunityDTOService;
    public OpportunityDTO mapToDTO(Opportunity opportunity, List<OpportunityByCompanyDTO> opportunityByCompanyDTOList,
               List<ActivityByCompanyDTO> activityByCompanyDTOList, List<ContactByCompanyDTO> contactByCompanyDTOList) {
        OpportunityDTO dto = new OpportunityDTO();
        dto.setTitle(opportunity.getTitle());
        dto.setUsrEventInOpportunity(opportunity.getEvent().getName());
        dto.setAmount(opportunity.getAmount());
        dto.setUsrUchastnikov(opportunity.getPersonsCount());
        dto.setOwner(opportunity.getOwner().getName());
        dto.setType(opportunity.getOpportunityType().getName());
        dto.setDepartment(opportunity.getOpportunityDepartment().getName());
        dto.setCreatedOn(opportunity.getCreatedOn());
        dto.setDescription(opportunity.getDescription());
        dto.setContactInOpportunity(contactMapper.mapToDTO(opportunity.getContact()));
        dto.setSellsByCompanyDTO(opportunityByCompanyDTOList);
        dto.setActivitiesByCompanyDTO(activityByCompanyDTOList);
        dto.setContactsByCompanyDTO(contactByCompanyDTOList);
        return dto;
    }
}

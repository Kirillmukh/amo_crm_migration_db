package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.LeadDTO;
import com.example.dbeaver.entity.lead.Lead;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LeadMapper {
    private final ContactMapper contactMapper;
    public LeadDTO mapToDTO(Lead lead) {
        LeadDTO dto = new LeadDTO();
        dto.setType(lead.getLeadType().getName());
        dto.setRegisterMethod(lead.getLeadRegisterMethod().getName());
        dto.setCreatedOn(lead.getCreatedOn());
        dto.setOpportunity(lead.getOpportunity().getTitle());
        dto.setBudget(lead.getBudget());
        dto.setQualifiedContact(contactMapper.mapToDTO(lead.getContact()));
        dto.setAccount(lead.getAccount());
        dto.setUsrPOSTInfo(lead.getUsrPostInfo());
        dto.setUsrPriceInfoFloat(lead.getUsrPriceInfoFloat());
        dto.setUsrPaymentType(lead.getUsrPaymentType().getName());
        dto.setUsrPrim(lead.getUsrsPrim());
        dto.setConference(lead.getEvent().getName());
        dto.setEmail(lead.getEmail());
        dto.setUsrInfoConf(lead.getUsrInfoConf());
        dto.setUsrPromo(lead.getUsrPromo());
        dto.setMobilePhone(lead.getMobilePhone());
        dto.setUsrConfId(lead.getUsrConfId());
        dto.setLeadMedium(lead.getLeadMedium().getName());
        dto.setLeadSource(lead.getLeadSource().getName());
        dto.setUsrYaMetrikaClientID(lead.getUsrYaMetrikaClientId());
        dto.setBpmRef(lead.getBpmRef());
        dto.setLanding(lead.getGeneratedWebForm().getName());
        dto.setCampaign(lead.getCampaign().getName());
        dto.setOwner(lead.getOwner().getName());
        dto.setCommentary(lead.getCommentary());
        return dto;
    }
}

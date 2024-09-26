package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.ContactDTO;
import com.example.dbeaver.dto.LeadDTO;
import com.example.dbeaver.entity.lead.Lead;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LeadMapper {
    private final ContactMapper contactMapper;
    public LeadDTO mapToDTO(Lead lead) {
        String title = lead.getOpportunity() != null ? lead.getOpportunity().getTitle() : null;
        String ursPaymentType = lead.getUsrPaymentType() != null ? lead.getUsrPaymentType().getName() : null;
        String campaign = lead.getCampaign() != null ? lead.getCampaign().getName() : null;
        ContactDTO contact = lead.getContact() != null ? contactMapper.mapToDTO(lead.getContact()) : null;
        String owner = lead.getOwner() != null ? lead.getOwner().getName() : null;
        String leadMedium = lead.getLeadMedium() != null ? lead.getLeadMedium().getName() : null;
        String leadSource = lead.getLeadSource() != null ? lead.getLeadSource().getName() : null;
        String landing = lead.getGeneratedWebForm() != null ? lead.getGeneratedWebForm().getName() : null;
        LeadDTO dto = new LeadDTO();
        dto.setType(lead.getLeadType().getName());
        dto.setRegisterMethod(lead.getLeadRegisterMethod().getName());
        dto.setCreatedOn(lead.getCreatedOn());
        dto.setOpportunity(title);
        dto.setBudget(lead.getBudget());
        dto.setQualifiedContact(contact);
        dto.setAccount(lead.getAccount());
        dto.setUsrPOSTInfo(lead.getUsrPostInfo());
        dto.setUsrPriceInfoFloat(lead.getUsrPriceInfoFloat());
        dto.setUsrPaymentType(ursPaymentType);
        dto.setUsrPrim(lead.getUsrsPrim());
        dto.setConference(lead.getEvent().getName());
        dto.setEmail(lead.getEmail());
        dto.setUsrInfoConf(lead.getUsrInfoConf());
        dto.setUsrPromo(lead.getUsrPromo());
        dto.setMobilePhone(lead.getMobilePhone());
        dto.setUsrConfId(lead.getUsrConfId());
        dto.setLeadMedium(leadMedium);
        dto.setLeadSource(leadSource);
        dto.setUsrYaMetrikaClientID(lead.getUsrYaMetrikaClientId());
        dto.setBpmRef(lead.getBpmRef());
        dto.setLanding(landing);
        dto.setCampaign(campaign);
        dto.setOwner(owner);
        dto.setCommentary(lead.getCommentary());
        return dto;
    }
}

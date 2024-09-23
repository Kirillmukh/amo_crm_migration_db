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
        return new LeadDTO(
                lead.getLeadType().getName(),
                lead.getLeadRegisterMethod().getName(),
                lead.getCreatedOn(),
                lead.getOpportunity().getTitle(),
                lead.getBudget(),
                contactMapper.mapToDTO(lead.getContact()),
                lead.getAccount(),
                lead.getUsrPostInfo(),
                lead.getUsrPriceInfoFloat(),
                lead.getUsrPaymentType().getName(),
                lead.getUsrsPrim(),
                lead.getEvent().getName(),
                lead.getEmail(),
                lead.getUsrInfoConf(),
                lead.getUsrPromo(),
                lead.getMobilePhone(),
                lead.getUsrConfId(),
                lead.getLeadMedium().getName(),
                lead.getLeadSource().getName(),
                lead.getUsrYaMetrikaClientId(),
                lead.getBpmRef(),
                lead.getGeneratedWebForm().getName(),
                lead.getCampaign().getName(),
                lead.getOwner().getName(),
                lead.getCommentary()
        );
    }
}

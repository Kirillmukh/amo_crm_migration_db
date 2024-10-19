package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.LeadDTO;
import com.example.dbeaver.entity.lead.Lead;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {ContactMapper.class})
public interface LeadMapper {
    @Mapping(target = "type", source = "leadType.name")
    @Mapping(target = "registerMethod", source = "leadRegisterMethod.name")
    @Mapping(target = "opportunity", source = "opportunity.title")
    @Mapping(target = "qualifiedContact", source = "contact")
    @Mapping(target = "usrPaymentType", source = "usrPaymentType.name")
    @Mapping(target = "leadMedium", source = "leadMedium.name")
    @Mapping(target = "leadSource", source = "leadSource.name")
    @Mapping(target = "landing", source = "generatedWebForm.name")
    @Mapping(target = "campaign", source = "campaign.name")
    @Mapping(target = "usrPOSTInfo", source = "usrPostInfo")
    @Mapping(target = "conference", source = "event.name")
    @Mapping(target = "owner", source = "owner.name")
    LeadDTO mapToDTO(Lead lead);
}

package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.OpportunityDTO;
import com.example.dbeaver.entity.opportunity.Opportunity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {ContactMapper.class})
public interface OpportunityMapper {
    @Mapping(target = "usrEventInOpportunity", source = "event.name")
    @Mapping(target = "usrUchastnikov", source = "personsCount")
    @Mapping(target = "owner", source = "owner.name")
    @Mapping(target = "type", source = "opportunityType.name")
    @Mapping(target = "contactInOpportunity", source = "contact")
    OpportunityDTO mapToDTO(Opportunity opportunity);
}

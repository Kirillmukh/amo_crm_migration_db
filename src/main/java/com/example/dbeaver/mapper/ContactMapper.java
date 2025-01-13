package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.ContactDTO;
import com.example.dbeaver.entity.contact.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactMapper {
    @Mapping(target = "company", source = "account.name")
    @Mapping(target = "alternativeEmail", source = "usrAdvancedEmail")
    @Mapping(target = "type", source = "contactType.name")
    @Mapping(target = "io", source = "usrIO")
    @Mapping(target = "role", source = "contactDecisionRole.name")
    @Mapping(target = "department", source = "department.name")
    @Mapping(target = "usrOldEventsOfRivals", source = "usrOldEvents")
    @Mapping(target = "moderation", source = "usrModeration.name")
    ContactDTO mapToDTO(Contact contact);
    List<ContactDTO> mapToDTO(List<Contact> contacts);
}

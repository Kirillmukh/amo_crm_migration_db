package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.util.ContactByCompanyDTO;
import com.example.dbeaver.entity.contact.Contact;
import org.springframework.stereotype.Component;

@Component
public class OpportunityContactMapper {
    public ContactByCompanyDTO mapToDTO(Contact contact) {
        ContactByCompanyDTO dto = new ContactByCompanyDTO();
        dto.setName(contact.getName());
        dto.setJobTitle(contact.getJobTitle());
        dto.setMobilePhone(contact.getMobilePhone());
        dto.setPhone(contact.getPhone());
        return dto;
    }
}

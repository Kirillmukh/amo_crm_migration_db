package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.ContactDTO;
import com.example.dbeaver.entity.contact.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {
    public ContactDTO mapToDTO(Contact contact) {
        ContactDTO dto = new ContactDTO();
        dto.setName(contact.getName());
        dto.setJobTitle(contact.getJobTitle());
        dto.setCompany(contact.getAccount().getName());
        dto.setPhone(contact.getPhone());
        dto.setMobilePhone(contact.getMobilePhone());
        dto.setEmail(contact.getEmail());
        dto.setAlternativeEmail(contact.getUsrAdvancedEmail());
        dto.setType(contact.getContactType().getName());
        dto.setDear(contact.getDear());
        dto.setIo(contact.getUsrIO());
        dto.setRole(contact.getContactDecisionRole().getName());
        dto.setDepartment(contact.getDepartment().getName());
        dto.setUsrOldEvents(contact.getUsrOldEvents());
        dto.setUsrOldEventsOfRivals(contact.getUsrOldEvents());
        dto.setUsrDiscCard(contact.getUsrDiscCard());
        dto.setModeration(contact.getUsrModeration().getName());
        //  multilist
        dto.setUsrPrimKontakta(contact.getUsrPrimKontakta());
        return dto;
    }
}

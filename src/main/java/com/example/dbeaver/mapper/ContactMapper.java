package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.ContactDTO;
import com.example.dbeaver.entity.contact.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {
    public ContactDTO mapToDTO(Contact contact) {
        String role = contact.getContactDecisionRole() != null ? contact.getContactDecisionRole().getName() : null;
        String account = contact.getAccount() != null ? contact.getAccount().getName() : null;
        String moderation = contact.getUsrModeration() != null ? contact.getUsrModeration().getName() : null;
        String department = contact.getDepartment() != null ? contact.getDepartment().getName() : null;
        ContactDTO dto = new ContactDTO();
        dto.setName(contact.getName());
        dto.setJobTitle(contact.getJobTitle());
        dto.setCompany(account);
        dto.setPhone(contact.getPhone());
        dto.setMobilePhone(contact.getMobilePhone());
        dto.setEmail(contact.getEmail());
        dto.setAlternativeEmail(contact.getUsrAdvancedEmail());
        dto.setType(contact.getContactType().getName());
        dto.setDear(contact.getDear());
        dto.setIo(contact.getUsrIO());
        dto.setRole(role);
        dto.setDepartment(department);
        dto.setUsrOldEvents(contact.getUsrOldEvents());
        dto.setUsrOldEventsOfRivals(contact.getUsrOldEvents());
        dto.setUsrDiscCard(contact.getUsrDiscCard());
        dto.setModeration(moderation);
        //  multilist
        dto.setUsrPrimKontakta(contact.getUsrPrimKontakta());
        return dto;
    }
}

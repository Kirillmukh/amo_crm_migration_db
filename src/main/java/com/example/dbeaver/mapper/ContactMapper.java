package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.ContactDTO;
import com.example.dbeaver.entity.contact.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {
    public ContactDTO mapToDTO(Contact contact) {
        return new ContactDTO(
                contact.getName(),
                contact.getJobTitle(),
                contact.getAccount().getName(),
                contact.getPhone(),
                contact.getMobilePhone(),
                contact.getEmail(),
                contact.getUsrAdvancedEmail(),
                contact.getContactType().getName(),
                contact.getDear(),
                contact.getUsrIO(),
                contact.getContactDecisionRole().getName(),
                contact.getDepartment().getName(),
                contact.getUsrOldEvents(),
                contact.getUsrEventsOfRival(),
                contact.getUsrDiscCard(),
                contact.getUsrModeration().getName(),
                // multilist
                contact.getUsrPrimKontakta()
        );
    }
}

package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.util.ContactByCompanyDTO;
import com.example.dbeaver.dto.util.OpportunityByCompanyDTO;
import com.example.dbeaver.entity.contact.Contact;
import com.example.dbeaver.entity.opportunity.Opportunity;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {
    public ContactByCompanyDTO mapToDTO(Contact contact) {
        return new ContactByCompanyDTO(
                contact.getName(),
                contact.getJobTitle(),
                contact.getMobilePhone(),
                contact.getPhone()
        );
    }
}

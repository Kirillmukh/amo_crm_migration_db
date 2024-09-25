package com.example.dbeaver.repository.criteria;

import com.example.dbeaver.entity.contact.Contact;
import org.springframework.stereotype.Repository;

@Repository
public class ContactCriteriaRepository extends CriteriaRepository<Contact, String> {
    public ContactCriteriaRepository(Class<Contact> entityClass) {
        super(entityClass);
    }
}

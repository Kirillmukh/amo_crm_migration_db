package com.example.dbeaver.repository;

import com.example.dbeaver.entity.contact.Contact;

public class ContactCriteriaRepository extends CriteriaRepository<Contact, String> {
    public ContactCriteriaRepository(Class<Contact> entityClass) {
        super(entityClass);
    }
}

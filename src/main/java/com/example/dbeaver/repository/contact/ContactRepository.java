package com.example.dbeaver.repository.contact;

import com.example.dbeaver.entity.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, String> {
    @Query(value = "select top(20) * from Contact", nativeQuery = true)
    List<Contact> findAll();
}

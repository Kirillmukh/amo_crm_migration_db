package com.example.dbeaver.controller.contact;

import com.example.dbeaver.repository.contact.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/c")
@RequiredArgsConstructor
public class ContactController {
    private final ContactRepository repository;
    @GetMapping
    public List<?> findAll() {
        return repository.findAll();
    }
}

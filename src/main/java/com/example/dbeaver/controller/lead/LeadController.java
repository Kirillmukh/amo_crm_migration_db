package com.example.dbeaver.controller.lead;

import com.example.dbeaver.repository.lead.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/l")
@RequiredArgsConstructor
public class LeadController {
    private final LeadRepository repository;
//    @GetMapping
//    public List<?> findAll() {
//        return repository.findAll();
//    }
}

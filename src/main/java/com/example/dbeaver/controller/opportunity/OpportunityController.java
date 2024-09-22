package com.example.dbeaver.controller.opportunity;

import com.example.dbeaver.repository.opportunity.OpportunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/opportunity")
@RequiredArgsConstructor
public class OpportunityController {
    private final OpportunityRepository repository;
    @GetMapping
    public List<?> findAll() {
        return repository.findAll();
    }
}

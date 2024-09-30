package com.example.dbeaver.controller.opportunity;

import com.example.dbeaver.repository.opportunity.OpportunityRepository;
import com.example.dbeaver.service.OpportunityDTOService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/o")
@RequiredArgsConstructor
public class OpportunityController {
    private final OpportunityRepository repository;
    private final OpportunityDTOService opportunityDTOService;
    @GetMapping
    public List<?> findAll() {
        return repository.findAll();
    }
}

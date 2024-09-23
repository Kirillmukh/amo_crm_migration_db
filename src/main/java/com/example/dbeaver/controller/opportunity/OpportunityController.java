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
@RequestMapping("/opportunity")
@RequiredArgsConstructor
public class OpportunityController {
    private final OpportunityRepository repository;
    private final OpportunityDTOService opportunityDTOService;
    @GetMapping
    public List<?> findAll() {
        return repository.findAll();
    }
    @GetMapping("/contacts")
    public List<?> findContacts(@RequestParam("id") String accountId) {
        return opportunityDTOService.getContactByCompanyDTO(accountId);
    }
    @GetMapping("/opportunities")
    public List<?> findOpportunities(@RequestParam("id") String accountId) {
        return opportunityDTOService.getOpportunityByCompanyDTO(accountId);
    }
    @GetMapping("/activities")
    public List<?> findActivities(@RequestParam("id") String accountId) {
        return opportunityDTOService.getActivityByCompanyDTO(accountId);
    }
}

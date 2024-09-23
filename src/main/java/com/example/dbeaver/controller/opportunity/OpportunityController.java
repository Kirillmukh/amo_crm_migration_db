package com.example.dbeaver.controller.opportunity;

import com.example.dbeaver.mapper.ActivityMapper;
import com.example.dbeaver.mapper.ContactMapper;
import com.example.dbeaver.mapper.OpportunityMapper;
import com.example.dbeaver.repository.account.AccountRepository;
import com.example.dbeaver.repository.opportunity.OpportunityRepository;
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
    private final AccountRepository accountRepository;
    private final ContactMapper contactMapper;
    private final OpportunityMapper opportunityMapper;
    private final ActivityMapper activityMapper;
    @GetMapping
    public List<?> findAll() {
        return repository.findAll();
    }
    @GetMapping("/contacts")
    public List<?> findContacts(@RequestParam("id") String accountId) {
        return accountRepository.findContacts(accountId).stream()
                .map(contactMapper::mapToDTO).toList();
    }
    @GetMapping("/opportunities")
    public List<?> findOpportunities(@RequestParam("id") String accountId) {
        return accountRepository.findOpportunities(accountId).stream()
                .map(opportunityMapper::mapToDTO).toList();
    }
    @GetMapping("/activities")
    public List<?> findActivities(@RequestParam("id") String accountId) {
        return accountRepository.findActivities(accountId).stream()
                .map(activityMapper::mapToDTO).toList();
    }
}

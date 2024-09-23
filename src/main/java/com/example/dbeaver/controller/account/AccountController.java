package com.example.dbeaver.controller.account;

import com.example.dbeaver.mapper.AccountOpportunityMapper;
import com.example.dbeaver.repository.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountRepository repository;
    private final AccountOpportunityMapper mapper;
    @GetMapping
    public List<?> findAll() {
        return repository.findAll();
    }
    @GetMapping("/opportunity")
    public List<?> findOpportunities(@RequestParam("id") String accountId) {
        return this.repository.findAccountOpportunities(accountId).stream()
                        .map(mapper::mapToDTO)
                        .toList();
    }
}

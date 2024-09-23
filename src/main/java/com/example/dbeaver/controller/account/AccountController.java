package com.example.dbeaver.controller.account;

import com.example.dbeaver.repository.account.AccountRepository;
import com.example.dbeaver.service.AccountDTOService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountDTOService accountDTOService;
    private final AccountRepository repository;
    @GetMapping
    public List<?> findAll() {
        return repository.findAll();
    }
    @GetMapping("/opportunity")
    public List<?> findOpportunities(@RequestParam("id") String accountId) {
        return accountDTOService.getOpportunityByAccountDTO(accountId);
    }
}

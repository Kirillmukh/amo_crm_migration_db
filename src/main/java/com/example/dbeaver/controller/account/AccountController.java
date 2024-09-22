package com.example.dbeaver.controller.account;

import com.example.dbeaver.repository.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountRepository repository;
    @GetMapping
    public List<?> findAll() {
        return repository.findAll();
    }
}

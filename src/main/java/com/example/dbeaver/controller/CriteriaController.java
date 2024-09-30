package com.example.dbeaver.controller;

import com.example.dbeaver.service.AccountDTOService;
import com.example.dbeaver.service.OpportunityDTOService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/criteria")
@RequiredArgsConstructor
public class CriteriaController {
    private final OpportunityDTOService opportunityDTOService;
    private final AccountDTOService accountDTOService;
    @GetMapping("/opportunity")
    public List<?> getOpportunityByCompanyDTO() {
        return opportunityDTOService.getOpportunityByCompanyDTO();
    }
    @GetMapping("/contact")
    public List<?> getContactByCompanyDTO() {
        return opportunityDTOService.getContactByCompanyDTO();
    }

    @GetMapping("/account")
    public List<?> get() {
        return accountDTOService.getOpportunityByAccountDTO();
    }

}

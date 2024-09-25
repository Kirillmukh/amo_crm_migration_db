package com.example.dbeaver.controller.opportunity;

import com.example.dbeaver.dto.util.OpportunityByCompanyDTO;
import com.example.dbeaver.repository.queries.OpportunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/criteria")
@RequiredArgsConstructor
public class CriteriaOpportunityController {
    private final OpportunityRepository repository;
    @GetMapping("/opportunity")
    public List<OpportunityByCompanyDTO> getList() {
        return this.repository.getSellsByAccount();
    }
}

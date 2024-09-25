package com.example.dbeaver.service;

import com.example.dbeaver.criteria.Criteria;
import com.example.dbeaver.criteria.conditions.EqualCondition;
import com.example.dbeaver.criteria.conditions.GreaterThanCondition;
import com.example.dbeaver.dto.util.OpportunityByAccountDTO;
import com.example.dbeaver.entity.account.Account;
import com.example.dbeaver.entity.opportunity.Opportunity;
import com.example.dbeaver.mapper.AccountOpportunityMapper;
import com.example.dbeaver.repository.account.AccountRepository;
import com.example.dbeaver.repository.criteria.AccountCriteriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountDTOService {
    private final AccountOpportunityMapper accountMapper;
    private final AccountCriteriaRepository criteriaRepository;
    public List<OpportunityByAccountDTO> getOpportunityByAccountDTO() {
        Criteria<Opportunity> criteria = new Criteria<>();
        criteria.addCondition(new GreaterThanCondition<>("createdOn", LocalDateTime.of(2024, 3, 1, 0, 0)));
        return criteriaRepository.getOpportunityByAccount(criteria).stream()
                .map(accountMapper::mapToDTO).toList();
    }
    public List<OpportunityByAccountDTO> getOpportunityByAccountDTO(Account account) {
        Criteria<Opportunity> criteria = new Criteria<>();
        criteria.addCondition(new EqualCondition<>("account", account));
        return criteriaRepository.getOpportunityByAccount(criteria).stream()
                .map(accountMapper::mapToDTO).toList();
    }
    public List<OpportunityByAccountDTO> getOpportunityByAccountDTO(Criteria<Opportunity> criteria) {
        return criteriaRepository.getOpportunityByAccount(criteria).stream()
                .map(accountMapper::mapToDTO).toList();
    }
}

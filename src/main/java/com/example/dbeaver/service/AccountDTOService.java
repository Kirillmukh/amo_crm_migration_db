package com.example.dbeaver.service;

import com.example.dbeaver.dto.util.OpportunityByAccountDTO;
import com.example.dbeaver.mapper.AccountOpportunityMapper;
import com.example.dbeaver.repository.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountDTOService {
    private final AccountRepository accountRepository;
    private final AccountOpportunityMapper accountMapper;
    public List<OpportunityByAccountDTO> getOpportunityByAccountDTO(String accountId) {
        return accountRepository.findAccountOpportunities(accountId).stream()
                .map(accountMapper::mapToDTO).toList();
    }

}

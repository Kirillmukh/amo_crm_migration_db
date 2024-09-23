package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.AccountDTO;
import com.example.dbeaver.entity.account.Account;
import com.example.dbeaver.service.AccountDTOService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountMapper {
    private final AccountDTOService accountDTOService;
    public AccountDTO mapToDTO(Account account) {
        return new AccountDTO(
                account.getName(),
                account.getAlternativeName(),
                account.getAccountType().getName(),
                account.getWeb(),
                account.getPhone(),
                account.getAccountCategory().getName(),
                account.getAccountIndustry().getName(),
                account.getUsrCompanyUseEDM(),
                account.getUsrArchiveEvents(),
                account.getUsrEventsOfRival(),
                accountDTOService.getOpportunityByAccountDTO(account.getId()),
                account.getUsrPrimKontr()
        );
    }
}

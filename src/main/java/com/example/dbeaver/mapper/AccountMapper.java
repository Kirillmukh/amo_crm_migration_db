package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.AccountDTO;
import com.example.dbeaver.entity.account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountMapper {
    public AccountDTO mapToDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setName(account.getName());
        dto.setAlternativeName(account.getAlternativeName());
        dto.setType(account.getAccountType().getName());
        dto.setWeb(account.getWeb());
        dto.setPhone(account.getPhone());
        dto.setCategory(account.getAccountCategory().getName());
        dto.setIndustry(account.getAccountIndustry().getName());
        dto.setUsrCompanyUseEDM(account.isUsrCompanyUseEDM());
        dto.setUsrOldEvents(account.getUsrOldEvents());
        dto.setUsrEventsOfRival(account.getUsrEventsOfRival());
        dto.setUsrPrimKontr(account.getUsrPrimKontr());
        return dto;
    }
}

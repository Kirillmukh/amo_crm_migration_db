package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.AccountDTO;
import com.example.dbeaver.dto.util.OpportunityByAccountDTO;
import com.example.dbeaver.entity.account.Account;
import com.example.dbeaver.service.AccountDTOService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountMapper {
    private final AccountDTOService accountDTOService;
    public AccountDTO mapToDTO(Account account, List<OpportunityByAccountDTO> opportunityByAccountDTOList) {
        AccountDTO dto = new AccountDTO();
        dto.setName(account.getName());
        dto.setAlternativeName(account.getAlternativeName());
        dto.setType(account.getAccountType().getName());
        dto.setWeb(account.getWeb());
        dto.setPhone(account.getPhone());
        dto.setCategory(account.getAccountCategory().getName());
        dto.setIndustry(account.getAccountIndustry().getName());
        dto.setUsrCompanyUseEDM(account.getUsrCompanyUseEDM());
        dto.setUsrArchiveEvents(account.getUsrArchiveEvents());
        dto.setUsrEventsOfRival(account.getUsrEventsOfRival());
        dto.setOpportunitiesByAccountDTO(opportunityByAccountDTOList);
        dto.setUsrPrimKontr(account.getUsrPrimKontr());
        return dto;
    }
}

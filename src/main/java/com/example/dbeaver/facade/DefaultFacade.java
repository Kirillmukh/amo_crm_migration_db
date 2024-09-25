package com.example.dbeaver.facade;

import com.example.dbeaver.criteria.Criteria;
import com.example.dbeaver.criteria.conditions.GreaterThanCondition;
import com.example.dbeaver.dto.AccountDTO;
import com.example.dbeaver.dto.response.ResponseDTO;
import com.example.dbeaver.dto.util.OpportunityByAccountDTO;
import com.example.dbeaver.entity.account.Account;
import com.example.dbeaver.repository.criteria.AccountCriteriaRepository;
import com.example.dbeaver.service.AccountDTOService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultFacade implements Facade {
    private final AccountCriteriaRepository accountRepository;
    private final AccountDTOService accountDTOService;
    @Override
    public ResponseDTO getResponse() {
        ResponseDTO responseDTO = new ResponseDTO();
        return null;
    }
    //TODO
    private List<AccountDTO> getAccountDTO() {
        Criteria<Account> criteria = new Criteria<>();
        criteria.addCondition(new GreaterThanCondition<>("createdOn", LocalDateTime.of(2024, 3, 1, 0, 0)));
        List<Account> accounts = accountRepository.findAll(criteria);
        for (Account account : accounts) {
            List<OpportunityByAccountDTO> opportunityByAccountDTO = accountDTOService.getOpportunityByAccountDTO(account);

        }
        return null;
    }

}

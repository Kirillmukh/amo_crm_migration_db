package com.example.dbeaver.dto.response;

import com.example.dbeaver.dto.AccountDTO;
import com.example.dbeaver.dto.ContactDTO;
import com.example.dbeaver.dto.LeadDTO;
import com.example.dbeaver.dto.OpportunityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseDTO {
    private List<AccountDTO> accountDTOList;
    private List<ContactDTO> contactDTOList;
    private List<LeadDTO> leadDTOList;
    private List<OpportunityDTO> opportunityDTOList;
}

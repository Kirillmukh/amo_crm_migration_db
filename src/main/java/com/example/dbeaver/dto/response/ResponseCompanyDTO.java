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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCompanyDTO {
    private AccountDTO company;
    private List<LeadDTO> leads;
    private List<OpportunityDTO> opportunities;
    private List<ContactDTO> contacts;
}

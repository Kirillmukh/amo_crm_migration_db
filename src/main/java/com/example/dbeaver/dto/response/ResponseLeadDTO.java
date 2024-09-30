package com.example.dbeaver.dto.response;

import com.example.dbeaver.dto.ContactDTO;
import com.example.dbeaver.dto.LeadDTO;
import com.example.dbeaver.dto.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseLeadDTO {
    private LeadDTO lead;
    private AccountDTO company;
    private List<ContactDTO> contacts;
}

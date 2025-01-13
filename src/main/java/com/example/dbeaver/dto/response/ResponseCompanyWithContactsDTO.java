package com.example.dbeaver.dto.response;

import com.example.dbeaver.dto.AccountDTO;
import com.example.dbeaver.dto.ContactDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCompanyWithContactsDTO {
    private AccountDTO company;
    private List<ContactDTO> contacts;
}

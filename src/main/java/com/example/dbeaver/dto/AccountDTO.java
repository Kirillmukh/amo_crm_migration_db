package com.example.dbeaver.dto;

import com.example.dbeaver.dto.util.OpportunityByAccountDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class AccountDTO {
    private String name;
    private String alternativeName;
    private String type; // TypeId -> name
    private String web;
    private String phone;
    private String category; // accountCategoryId -> name
    private String industry; // industryId (AccountIndustry.java) -> name
    private byte usrCompanyUseEDM;
    private String usrArchiveEvents;
    private String usrEventsOfRival;
    private List<OpportunityByAccountDTO> opportunitiesByAccountDTO;
    private String usrPrimKontr;
}

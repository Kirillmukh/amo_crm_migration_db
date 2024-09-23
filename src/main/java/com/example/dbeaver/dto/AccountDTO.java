package com.example.dbeaver.dto;

import com.example.dbeaver.dto.util.ContactByCompanyDTO;
import com.example.dbeaver.dto.util.OpportunityByAccountDTO;

import java.util.List;

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

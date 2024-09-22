package com.example.dbeaver.dto;

import com.example.dbeaver.dto.util.SellsOfCompanyDTO;

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
    private SellsOfCompanyDTO sellsByCompanyDTO;
    private String usrPrimKontr;
}

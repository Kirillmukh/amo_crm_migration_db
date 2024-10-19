package com.example.dbeaver.dto;

import lombok.Getter;
import lombok.Setter;
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
    private boolean usrCompanyUseEDM;
    private String usrOldEvents;
    private String usrEventsOfRival;
    private String usrPrimKontr;
}

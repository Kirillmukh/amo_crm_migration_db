package com.example.dbeaver.entity.account;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "account", schema = "dbo")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(name = "createdon")
    private LocalDateTime createdOn;
    @Column(name = "modifiedon")
    private LocalDateTime modifiedOn;
    private String name;
    @Column(name = "alternativename")
    private String alternativeName;
    @ManyToOne
    @JoinColumn(name = "typeid")
    private AccountType accountType;
    private String web;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "accountcategoryid")
    private AccountCategory accountCategory;
    @ManyToOne
    @JoinColumn(name = "industryid")
    private AccountIndustry accountIndustry;
    @Column(name = "usrcompanyuseedm")
    private byte usrCompanyUseEDM;
    @Column(name = "usrarchiveevents")
    private String usrArchiveEvents;
    @Column(name = "usreventsofrival")
    private String usrEventsOfRival;
    @Column(name = "usrprimkontr")
    private String usrPrimKontr;
}

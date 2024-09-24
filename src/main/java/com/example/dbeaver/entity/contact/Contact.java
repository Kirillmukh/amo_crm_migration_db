package com.example.dbeaver.entity.contact;

import com.example.dbeaver.entity.account.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "contact", schema = "dbo")
@Getter
@Setter
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(name = "createdon")
    private LocalDateTime createdOn;
    @Column(name = "modifiedon")
    private LocalDateTime modifiedOn;
    private String name;
    @Column(name = "jobtitle")
    private String jobTitle;
    @ManyToOne
    @JoinColumn(name = "accountid")
    private Account account;
    private String phone;
    @Column(name = "mobilephone")
    private String mobilePhone;
    private String email;
    @Column(name = "usradvancedemail")
    private String usrAdvancedEmail;
    @ManyToOne
    @JoinColumn(name = "typeid")
    private ContactType contactType;
    private String dear;
    @Column(name = "usrio")
    private String usrIO;
    @ManyToOne
    @JoinColumn(name = "usrdecisionroleid")
    private ContactDecisionRole contactDecisionRole;
    @ManyToOne
    @JoinColumn(name = "departmentid")
    private Department department;
    @Column(name = "usroldevents2")
    private String usrOldEvents;
    @Column(name = "usreventsofrival")
    private String usrEventsOfRival;
    @Column(name = "usrdisccard")
    private int usrDiscCard;
    @ManyToOne
    @JoinColumn(name = "usrmoderationid")
    private UsrModeration usrModeration;
    /*
    мультисписок
     */
    @Column(name = "usrprimkontakta")
    private String usrPrimKontakta;
}

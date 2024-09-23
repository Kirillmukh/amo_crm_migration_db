package com.example.dbeaver.entity.opportunity;

import com.example.dbeaver.entity.Event;
import com.example.dbeaver.entity.account.Account;
import com.example.dbeaver.entity.contact.Contact;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "opportunity", schema = "dbo")
@Getter
@Setter
public class Opportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "usreventinopportid")
    private Event event;
    private double amount;
    @Column(name = "usruchastnikov")
    private int personsCount;
    @ManyToOne
    @JoinColumn(name = "ownerid")
    private Contact owner;
    @ManyToOne
    @JoinColumn(name = "typeid")
    private OpportunityType opportunityType;
    @ManyToOne
    @JoinColumn(name = "responsibledepartmentid")
    private OpportunityDepartment opportunityDepartment;
    @Column(name = "createdon")
    private LocalDateTime createdOn;
    private String description;
    @ManyToOne
    @JoinColumn(name = "contactid")
    private Contact contact;
    @ManyToOne
    @JoinColumn(name = "accountid")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "stageid")
    private OpportunityStage opportunityStage;
}

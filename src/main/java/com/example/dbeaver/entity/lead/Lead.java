package com.example.dbeaver.entity.lead;

import com.example.dbeaver.entity.Event;
import com.example.dbeaver.entity.contact.Contact;
import com.example.dbeaver.entity.opportunity.Opportunity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "lead", schema = "dbo")
@Getter
@Setter
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(name = "createdon")
    private LocalDateTime createdOn;
    @OneToOne
    @JoinColumn(name = "opportunityid")
    private Opportunity opportunity;
    private double budget;
    @ManyToOne
    @JoinColumn(name = "qualifiedcontactid")
    private Contact contact;
    @Column(name = "usrpostinfo")
    private String usrPostInfo;
    @Column(name = "usrpriceinfofloat")
    private double usrPriceInfoFloat;
    @ManyToOne
    @JoinColumn(name = "usrlookupid")
    private UsrPaymentType usrPaymentType;
    @Column(name = "usrsprim")
    private String usrsPrim;
    @ManyToOne
    @JoinColumn(name = "usrmerupid")
    private Event event;
    private String email;
    @Column(name = "usrinfoconf")
    private String usrInfoConf;
    @Column(name = "usrpromo")
    private String usrPromo;
    @Column(name = "mobilephone")
    private String mobilePhone;
    @Column(name = "usrconfid")
    private int usrConfId;
    @ManyToOne
    @JoinColumn(name = "leadmediumid")
    private LeadMedium leadMedium;
    @ManyToOne
    @JoinColumn(name = "leadsourceid")
    private LeadSource leadSource;
    @Column(name = "usryametrikaclientid")
    private String usrYaMetrikaClientId;
    @ManyToOne
    @JoinColumn(name = "ownerid")
    private Contact owner;
    private String commentary;
}

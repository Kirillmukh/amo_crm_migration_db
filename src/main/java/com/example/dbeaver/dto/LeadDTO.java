package com.example.dbeaver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class LeadDTO {
    private String id;
    private String type; // leadTypeId (LeadType.java) -> name
    private String registerMethod; // RegisterMethodId (LeadRegisterMethod.java) -> name
    private LocalDateTime createdOn;
    private String opportunity; // OpportunityId -> title
    private double budget;
    private ContactDTO qualifiedContact; // QualifiedContactId -> ?
    private String account;
    private String usrPOSTInfo; // Должность
    private double usrPriceInfoFloat; // Цена на момент регистрации
    private String usrPaymentType; // UsrLOOKUPId (UsrPaymentType.java) -> name
    private String usrPrim; // notes
    private String conference; // usrmerupid (Event.java) -> name
    private String email;
    private String usrInfoConf; // Откуда узнали
    private String usrPromo;
    private String mobilePhone;
    private int usrConfId;
    private String leadMedium; // LeadMediumId -> name
    private String leadSource; // LeadSourceId -> name
    private String usrYaMetrikaClientId;
    private String bpmRef;
    private String landing; // WebFormId (GeneratedWebForm.java) -> name
    private String campaign; // CampaignId -> name
    private String owner; // OpportunityId (Opportunity.java) -> OwnerId (Contact.java) -> name
    private String commentary;
}

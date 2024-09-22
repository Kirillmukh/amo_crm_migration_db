package com.example.dbeaver.dto;

public class ContactDTO {
    private String ownerId; // sqlIgnore
    private String name; // contact -> name
    private String jobTitle; // contact -> jobTitle
    private String company; // accountId -> name
    private String workPhone; // contact -> phone
    private String mobilePhone; // contact -> mobilePhone
    private String email; // contact -> email
    private String alternativeEmail; // contact -> usrAdvancedEmail
    private String type; // TypeId (ContactType.java) -> name
    private String dear; // SalutationTypeId (ContactSalutationType.java) -> dear || dear || usrDear;
    private String io; // Contact -> usrIO
    private String role; // usrDecisionRoleId -> name
    private String department; // departmentId (Department.java) -> name
    private String usrOldEvents; // contact -> usrOldEvents
    private String usrOldEventsOfRivals; // contact -> usrOldEvents
    private int usrDiscCard; // contact -> UsrDiscCard
    private String moderation; // usrModerationId (UsrModeration.java) -> name

    /* multilist
//    @Column(name = "donotuseemail")
    private byte doNotUseEmail;
//    @Column(name = "donotusecall")
    private byte doNotUseCall;
//    @Column(name = "donotusefax")
//    byte doNotUseFax;
//    @Column(name = "donotusesms")
    private byte doNotUseSms;
//    @Column(name = "donotusemail")
    private byte doNotUsEmail;
     */

    private String usrPrimKontakta; // contact -> usrPrimKontakta // notes


}

package com.example.dbeaver.dto;

import com.example.dbeaver.dto.util.ActivitiesByCompanyDTO;
import com.example.dbeaver.dto.util.ContactsOfCompanyDTO;
import com.example.dbeaver.dto.util.SellsByCompanyDTO;

import java.time.LocalDateTime;

public class OpportunityDTO {
    private String title;
    private String usrEventInOpportunity; // usrEventInOpportunityId (Event.java) -> name
    private String amount;
    private double usrUchastnikov;
    private String owner; // OwnerId (Contact.java)
    private String type; // typeId (OpportunityType) -> name
    private String department; // responsibleDepartmentId (OpportunityDepartment.java) -> name
    private LocalDateTime createdOn; // or dueDate
    private String description;
    private ContactDTO contactInOpportunity; // contactId (Contact.java)
    private ContactsOfCompanyDTO contactsByAccountDTO;
    private SellsByCompanyDTO sellsByCompanyDTO;
    private ActivitiesByCompanyDTO activitesByCompanyDTO;

}

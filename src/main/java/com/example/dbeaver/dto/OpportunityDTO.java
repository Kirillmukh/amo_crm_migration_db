package com.example.dbeaver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class OpportunityDTO {
    private String id;
    private String title;
    private String usrEventInOpportunity; // usrEventInOpportunityId (Event.java) -> name
    private double amount;
    private int usrUchastnikov;
    private String owner; // OwnerId (Contact.java)
    private String type; // typeId (OpportunityType) -> name
    private String department; // responsibleDepartmentId (OpportunityDepartment.java) -> name
    private LocalDateTime createdOn; // or dueDate
    private String description;
    private ContactDTO contactInOpportunity; // contactId (Contact.java)
}
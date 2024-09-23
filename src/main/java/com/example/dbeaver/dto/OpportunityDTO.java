package com.example.dbeaver.dto;

import com.example.dbeaver.dto.util.ActivityByCompanyDTO;
import com.example.dbeaver.dto.util.ContactByCompanyDTO;
import com.example.dbeaver.dto.util.OpportunityByAccountDTO;
import com.example.dbeaver.dto.util.OpportunityByCompanyDTO;

import java.time.LocalDateTime;
import java.util.List;

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
    private ContactByCompanyDTO contactsByAccountDTO;
    private List<OpportunityByCompanyDTO> sellsByCompanyDTO;
    private List<ActivityByCompanyDTO> activitiesByCompanyDTO;
    private List<ContactByCompanyDTO> contactsByCompanyDTO;
}

package com.example.dbeaver.mapper.response;

import com.example.dbeaver.dto.response.ResponseCompanyDTO;
import com.example.dbeaver.dto.response.ResponseContactDTO;
import com.example.dbeaver.dto.response.ResponseLeadDTO;
import com.example.dbeaver.dto.response.ResponseOpportunityDTO;
import com.example.dbeaver.entity.account.Account;
import com.example.dbeaver.entity.contact.Contact;
import com.example.dbeaver.entity.lead.Lead;
import com.example.dbeaver.entity.opportunity.Opportunity;
import com.example.dbeaver.mapper.AccountMapper;
import com.example.dbeaver.mapper.ContactMapper;
import com.example.dbeaver.mapper.LeadMapper;
import com.example.dbeaver.mapper.OpportunityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ResponseMapper {
    private final LeadMapper leadMapper;
    private final ContactMapper contactMapper;
    private final OpportunityMapper opportunityMapper;
    private final AccountMapper accountMapper;
    public ResponseLeadDTO mapToLeadDTO(Lead lead, Account account, List<Contact> contacts) {
        ResponseLeadDTO responseLeadDTO = new ResponseLeadDTO();
        responseLeadDTO.setLead(leadMapper.mapToDTO(lead));
        if (Objects.nonNull(account))
            responseLeadDTO.setCompany(accountMapper.mapToDTO(account));
        if (Objects.nonNull(contacts))
            responseLeadDTO.setContacts(contacts.stream().map(contactMapper::mapToDTO).toList());
        return responseLeadDTO;
    }

    public ResponseContactDTO mapToContactDTO(Contact contact, List<Lead> leads, List<Opportunity> opportunities, Account account) {
        ResponseContactDTO responseContactDTO = new ResponseContactDTO();
        responseContactDTO.setContact(contactMapper.mapToDTO(contact));
        responseContactDTO.setLeads(leads.parallelStream().map(leadMapper::mapToDTO).toList());
        responseContactDTO.setOpportunities(opportunities.parallelStream().map(opportunityMapper::mapToDTO).toList());
        if (Objects.nonNull(account)) {
            responseContactDTO.setCompany(accountMapper.mapToDTO(account));
        }
        return responseContactDTO;
    }

    public ResponseOpportunityDTO mapToOpportunityDTO(Opportunity opportunity, List<Contact> contacts, Account account) {
        ResponseOpportunityDTO responseOpportunityDTO = new ResponseOpportunityDTO();
        responseOpportunityDTO.setOpportunity(opportunityMapper.mapToDTO(opportunity));
        responseOpportunityDTO.setContacts(contacts.parallelStream().map(contactMapper::mapToDTO).toList());
        if (Objects.nonNull(account)) {
            responseOpportunityDTO.setCompany(accountMapper.mapToDTO(account));
        }
        return responseOpportunityDTO;
    }

    public ResponseCompanyDTO mapToCompanyDTO(Account account, List<Lead> leads, List<Opportunity> opportunities, List<Contact> contacts) {
        ResponseCompanyDTO responseCompanyDTO = new ResponseCompanyDTO();
        responseCompanyDTO.setCompany(accountMapper.mapToDTO(account));
        responseCompanyDTO.setLeads(leads.parallelStream().map(leadMapper::mapToDTO).toList());
        responseCompanyDTO.setOpportunities(opportunities.parallelStream().map(opportunityMapper::mapToDTO).toList());
        responseCompanyDTO.setContacts(contacts.parallelStream().map(contactMapper::mapToDTO).toList());
        return responseCompanyDTO;
    }
}

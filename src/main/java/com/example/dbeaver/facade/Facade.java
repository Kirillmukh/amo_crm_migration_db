package com.example.dbeaver.facade;

import com.example.dbeaver.dto.response.*;

import java.time.LocalDate;
import java.util.List;

public interface Facade {

    ResponseLeadDTO findLeadById(String id, LocalDate date);
    ResponseContactDTO findContactById(String id, LocalDate date);
    ResponseOpportunityDTO findOpportunityById(String id, LocalDate date);
    ResponseCompanyDTO findCompanyById(String id, LocalDate date);
    List<ResponseLeadDTO> findLeads(int limit, int offset, LocalDate date);
    List<ResponseContactDTO> findContacts(int limit, int offset, LocalDate date);
    List<ResponseOpportunityDTO> findOpportunities(int limit, int offset, LocalDate date);
    List<ResponseCompanyDTO> findCompanies(int limit, int offset, LocalDate date);
    long countAllLeads(LocalDate localDate);
    long countAllContacts(LocalDate localDate);
    long countAllOpportunities(LocalDate localDate);
    long countAllCompanies(LocalDate localDate);

    List<ResponseCompanyWithContactsDTO> findCompaniesWithContacts(int limit, int offset, LocalDate date);
    List<ResponseContactWithoutCompanyDTO> findContactsWithoutCompany(int limit, int offset, LocalDate date);
    long countContactsWithoutCompanies(LocalDate date);
}

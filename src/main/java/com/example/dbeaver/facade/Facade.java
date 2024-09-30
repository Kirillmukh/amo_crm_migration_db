package com.example.dbeaver.facade;

import com.example.dbeaver.dto.response.ResponseCompanyDTO;
import com.example.dbeaver.dto.response.ResponseContactDTO;
import com.example.dbeaver.dto.response.ResponseLeadDTO;
import com.example.dbeaver.dto.response.ResponseOpportunityDTO;

import java.util.List;

public interface Facade {

    ResponseLeadDTO findLeadById(String id);
    ResponseContactDTO findContactById(String id);
    ResponseOpportunityDTO findOpportunityById(String id);
    ResponseCompanyDTO findCompanyById(String id);
    List<ResponseLeadDTO> findLeads();
    List<ResponseContactDTO> findContacts();
    List<ResponseOpportunityDTO> findOpportunities();
    List<ResponseCompanyDTO> findCompanies();

    List<ResponseLeadDTO> findLeads(int limit, int offset);
    List<ResponseContactDTO> findContacts(int limit, int offset);
    List<ResponseOpportunityDTO> findOpportunities(int limit, int offset);
    List<ResponseCompanyDTO> findCompanies(int limit, int offset);
}

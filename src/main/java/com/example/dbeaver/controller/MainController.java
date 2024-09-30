package com.example.dbeaver.controller;

import com.example.dbeaver.exception.WrongIdException;
import com.example.dbeaver.facade.Facade;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final Facade facade;
    @GetMapping("/lead/{id}")
    public Response leadById(@PathVariable String id) {
        return Response.ok(facade.findLeadById(id)).status(200).build();
    }
    @GetMapping("/lead")
    public Response leads() {
        return Response.ok(facade.findLeads()).status(200).build();
    }

    @GetMapping("/company/{id}")
    public Response companyById(@PathVariable String id) {
        return Response.ok(facade.findCompanyById(id)).status(200).build();
    }
    @GetMapping("/company")
    public Response companies() {
        return Response.ok(facade.findCompanies()).status(200).build();
    }
    @GetMapping("/contact/{id}")
    public Response contactById(@PathVariable String id) {
        return Response.ok(facade.findContactById(id)).status(200).build();
    }
    @GetMapping("/contact")
    public Response contacts() {
        return Response.ok(facade.findContacts()).status(200).build();
    }
    @GetMapping("/opportunity/{id}")
    public Response opportunityById(@PathVariable String id) {
        return Response.ok(facade.findOpportunityById(id)).status(200).build();
    }
    @GetMapping("/opportunity")
    public Response opportunities() {
        return Response.ok(facade.findOpportunities()).status(200).build();
    }

    @ExceptionHandler(WrongIdException.class)
    public Response handleWrongIdException(WrongIdException exception) {
        return Response.ok(exception.getMessage()).status(400).build();
    }
}

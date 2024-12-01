package com.example.dbeaver.controller;

import com.example.dbeaver.dto.response.ResponseCompanyDTO;
import com.example.dbeaver.dto.response.ResponseContactDTO;
import com.example.dbeaver.dto.response.ResponseLeadDTO;
import com.example.dbeaver.dto.response.ResponseOpportunityDTO;
import com.example.dbeaver.exception.WrongIdException;
import com.example.dbeaver.facade.Facade;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final Facade facade;
    private final Class<MainController> controllerClass = MainController.class;

    @GetMapping("/lead/{id}")
    public Response leadById(@PathVariable String id,
                             @RequestParam(name = "date", defaultValue = "01-03-2024", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return Response.ok(facade.findLeadById(id, date)).status(200).build();
    }

    @GetMapping("/lead")
    public Response leads(@RequestParam(name = "limit", defaultValue = "500", required = false) int limit,
                          @RequestParam(name = "offset", defaultValue = "0", required = false) int offset,
                          @RequestParam(name = "date", defaultValue = "01-03-2024", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        List<ResponseLeadDTO> result = facade.findLeads(limit, offset, date);
        if (result.isEmpty()) {
            return Response.noContent().status(204).build();
        }
        List<Link> links = new ArrayList<>();
        links.add(linkTo(methodOn(controllerClass).leads(limit, Math.max(0, offset - limit), date)).withRel("prevOrFirst"));
        if (facade.countAllLeads(date) >= limit + offset) {
            links.add(linkTo(methodOn(controllerClass).leads(limit, offset + limit, date)).withRel("next"));
        }
        CollectionModel<ResponseLeadDTO> model = CollectionModel.of(result, links);
        return Response.ok(model).status(200).build();
    }

    @GetMapping("/company/{id}")
    public Response companyById(@PathVariable String id,
                                @RequestParam(name = "date", defaultValue = "01-03-2024", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return Response.ok(facade.findCompanyById(id, date)).status(200).build();
    }

    @GetMapping("/company")
    public Response companies(@RequestParam(name = "limit", defaultValue = "500", required = false) int limit,
                              @RequestParam(name = "offset", defaultValue = "0", required = false) int offset,
                              @RequestParam(name = "date", defaultValue = "01-03-2024", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        List<ResponseCompanyDTO> result = facade.findCompanies(limit, offset, date);
        log.info(result.toString());
        if (result.isEmpty()) {
            return Response.noContent().status(204).build();
        }
        List<Link> links = new ArrayList<>();
        links.add(linkTo(methodOn(controllerClass).companies(limit, Math.max(0, offset - limit), date)).withRel("prevOrFirst"));
        if (facade.countAllCompanies(date) >= limit + offset) {
            links.add(linkTo(methodOn(controllerClass).companies(limit, offset + limit, date)).withRel("next"));
        }
        CollectionModel<ResponseCompanyDTO> model = CollectionModel.of(result, links);
        return Response.ok(model).status(200).build();
    }

    @GetMapping("/contact/{id}")
    public Response contactById(@PathVariable String id,
                                @RequestParam(name = "date", defaultValue = "01-03-2024", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return Response.ok(facade.findContactById(id, date)).status(200).build();
    }

    @GetMapping("/contact")
    public Response contacts(@RequestParam(name = "limit", defaultValue = "500", required = false) int limit,
                             @RequestParam(name = "offset", defaultValue = "0", required = false) int offset,
                             @RequestParam(name = "date", defaultValue = "01-03-2024", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        List<ResponseContactDTO> result = facade.findContacts(limit, offset, date);
        if (result.isEmpty()) {
            return Response.noContent().status(204).build();
        }
        List<Link> links = new ArrayList<>();
        links.add(linkTo(methodOn(controllerClass).contacts(limit, Math.max(0, offset - limit), date)).withRel("prevOrFirst"));
        if (facade.countAllContacts(date) >= limit + offset) {
            links.add(linkTo(methodOn(controllerClass).contacts(limit, offset + limit, date)).withRel("next"));
        }
        CollectionModel<ResponseContactDTO> model = CollectionModel.of(result, links);
        return Response.ok(model).status(200).build();
    }

    @GetMapping("/opportunity/{id}")
    public Response opportunityById(@PathVariable String id,
                                    @RequestParam(name = "date", defaultValue = "01-03-2024", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return Response.ok(facade.findOpportunityById(id, date)).status(200).build();
    }

    @GetMapping("/opportunity")
    public Response opportunities(@RequestParam(name = "limit", defaultValue = "500", required = false) int limit,
                                  @RequestParam(name = "offset", defaultValue = "0", required = false) int offset,
                                  @RequestParam(name = "date", defaultValue = "01-03-2024", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        List<ResponseOpportunityDTO> result = facade.findOpportunities(limit, offset, date);
        if (result.isEmpty()) {
            return Response.noContent().status(204).build();
        }
        List<Link> links = new ArrayList<>();
        links.add(linkTo(methodOn(controllerClass).opportunities(limit, Math.max(0, offset - limit), date)).withRel("prevOrFirst"));
        if (facade.countAllOpportunities(date) >= limit + offset) {
            links.add(linkTo(methodOn(controllerClass).opportunities(limit, offset + limit, date)).withRel("next"));
        }
        CollectionModel<ResponseOpportunityDTO> model = CollectionModel.of(result, links);
        return Response.ok(model).status(200).build();
    }
    @ExceptionHandler(WrongIdException.class)
    public Response handleWrongIdException(WrongIdException exception) {
        return Response.ok(exception.getMessage()).status(400).build();
    }
}

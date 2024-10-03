package com.example.dbeaver.controller;

import com.example.dbeaver.dto.response.ResponseCompanyDTO;
import com.example.dbeaver.dto.response.ResponseContactDTO;
import com.example.dbeaver.dto.response.ResponseLeadDTO;
import com.example.dbeaver.dto.response.ResponseOpportunityDTO;
import com.example.dbeaver.exception.WrongIdException;
import com.example.dbeaver.facade.Facade;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
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
        CollectionModel<ResponseLeadDTO> model = CollectionModel.of(result,
                linkTo(methodOn(controllerClass).leads(limit, Math.max(0, offset - limit), date)).withRel("prevOrFirst"),
                linkTo(methodOn(controllerClass).leads(limit, offset + limit, date)).withRel("next"));
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
        CollectionModel<ResponseCompanyDTO> model = CollectionModel.of(result,
                linkTo(methodOn(controllerClass).companies(limit, Math.max(0, offset - limit), date)).withRel("prevOrFirst"),
                linkTo(methodOn(controllerClass).companies(limit, offset + limit, date)).withRel("next"));
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
        CollectionModel<ResponseContactDTO> model = CollectionModel.of(result,
                linkTo(methodOn(controllerClass).contacts(limit, Math.max(0, offset - limit), date)).withRel("prevOrFirst"),
                linkTo(methodOn(controllerClass).contacts(limit, offset + limit, date)).withRel("next"));
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
        CollectionModel<ResponseOpportunityDTO> model = CollectionModel.of(result,
                linkTo(methodOn(controllerClass).opportunities(limit, Math.max(0, offset - limit), date)).withRel("prevOrFirst"),
                linkTo(methodOn(controllerClass).opportunities(limit, offset + limit, date)).withRel("next"));
        return Response.ok(model).status(200).build();
    }
//    @GetMapping("/entity")
//    public ResponseEntity<List<ResponseOpportunityDTO>> findOpportunityEntities(@RequestParam(name = "limit", defaultValue = "500", required = false) int limit,
//                                  @RequestParam(name = "offset", defaultValue = "0", required = false) int offset) {
//        return ResponseEntity.ok(facade.findOpportunities(limit, offset));
//    }

    @ExceptionHandler(WrongIdException.class)
    public Response handleWrongIdException(WrongIdException exception) {
        return Response.ok(exception.getMessage()).status(400).build();
    }
}

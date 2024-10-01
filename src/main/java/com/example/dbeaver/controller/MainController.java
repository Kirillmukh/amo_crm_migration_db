package com.example.dbeaver.controller;

import com.example.dbeaver.dto.response.ResponseCompanyDTO;
import com.example.dbeaver.dto.response.ResponseContactDTO;
import com.example.dbeaver.dto.response.ResponseLeadDTO;
import com.example.dbeaver.dto.response.ResponseOpportunityDTO;
import com.example.dbeaver.exception.WrongIdException;
import com.example.dbeaver.facade.Facade;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final Facade facade;
    private final Class<MainController> controllerClass = MainController.class;
    @GetMapping("/lead/{id}")
    public Response leadById(@PathVariable String id) {
        return Response.ok(facade.findLeadById(id)).status(200).build();
    }
    @GetMapping("/lead")
    public Response leads(@RequestParam(name = "limit", defaultValue = "500", required = false) int limit,
                          @RequestParam(name = "offset", defaultValue = "0", required = false) int offset) {
        List<ResponseLeadDTO> result = facade.findLeads(limit, offset);
        CollectionModel<ResponseLeadDTO> model = CollectionModel.of(result,
                linkTo(methodOn(controllerClass).leads(limit, Math.max(0, offset - limit))).withRel("prevOrFirst"),
                linkTo(methodOn(controllerClass).leads(limit, offset + limit)).withRel("next"));
        return Response.ok(model).status(200).build();
    }

    @GetMapping("/company/{id}")
    public Response companyById(@PathVariable String id) {
        return Response.ok(facade.findCompanyById(id)).status(200).build();
    }
    @GetMapping("/company")
    public Response companies(@RequestParam(name = "limit", defaultValue = "500", required = false) int limit,
                              @RequestParam(name = "offset", defaultValue = "0", required = false) int offset) {
        List<ResponseCompanyDTO> result = facade.findCompanies(limit, offset);
        CollectionModel<ResponseCompanyDTO> model = CollectionModel.of(result,
                linkTo(methodOn(controllerClass).companies(limit, Math.max(0, offset - limit))).withRel("prevOrFirst"),
                linkTo(methodOn(controllerClass).companies(limit, offset + limit)).withRel("next"));
        return Response.ok(model).status(200).build();
    }
    @GetMapping("/contact/{id}")
    public Response contactById(@PathVariable String id) {
        return Response.ok(facade.findContactById(id)).status(200).build();
    }
    @GetMapping("/contact")
    public Response contacts(@RequestParam(name = "limit", defaultValue = "500", required = false) int limit,
                             @RequestParam(name = "offset", defaultValue = "0", required = false) int offset) {
        List<ResponseContactDTO> result = facade.findContacts(limit, offset);
        CollectionModel<ResponseContactDTO> model = CollectionModel.of(result,
                linkTo(methodOn(controllerClass).contacts(limit, Math.max(0, offset - limit))).withRel("prevOrFirst"),
                linkTo(methodOn(controllerClass).contacts(limit, offset + limit)).withRel("next"));
        return Response.ok(model).status(200).build();
    }
    @GetMapping("/opportunity/{id}")
    public Response opportunityById(@PathVariable String id) {
        return Response.ok(facade.findOpportunityById(id)).status(200).build();
    }
    @GetMapping("/opportunity")
    public Response opportunities(@RequestParam(name = "limit", defaultValue = "500", required = false) int limit,
                                  @RequestParam(name = "offset", defaultValue = "0", required = false) int offset) {
        List<ResponseOpportunityDTO> result = facade.findOpportunities(limit, offset);
        CollectionModel<ResponseOpportunityDTO> model = CollectionModel.of(result,
                linkTo(methodOn(controllerClass).opportunities(limit, Math.max(0, offset - limit))).withRel("prevOrFirst"),
                linkTo(methodOn(controllerClass).opportunities(limit, offset + limit)).withRel("next"));
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

package com.company.invoicing.controllers;

import com.company.invoicing.intercepters.AuthorityAnnotation;
import com.company.invoicing.models.Company;
import com.company.invoicing.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value ="/company")
public class CompanyController {

    @Autowired
    private CompanyService service;

    @AuthorityAnnotation(method = "getAll",table = "company")
    @RequestMapping(method = RequestMethod.GET)
    public List<Company> getAll(){
        return service.findAll();
    }

    @AuthorityAnnotation(method = "getOne",table = "company")
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Company find(@PathVariable long id){
        return service.findOne(id);
    }

    @AuthorityAnnotation(method = "create",table = "company")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Company company){
        service.create(company);
    }

    @AuthorityAnnotation(method = "update",table = "company")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Company company) {
    service.update(company);
    }

    @AuthorityAnnotation(method = "delete",table = "company")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @AuthorityAnnotation(method = "search",table = "company")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Company> search(@RequestBody Company company) {
        return service.search(company);
    }
}
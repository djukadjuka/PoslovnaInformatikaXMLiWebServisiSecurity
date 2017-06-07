package com.company.invoicing.controllers;

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

    @RequestMapping(method = RequestMethod.GET)
    public List<Company> getAll(){
        return service.findAll();
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Company find(@PathVariable long id){
        return service.findOne(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Company company){
        service.create(company);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Company company) {
    service.update(company);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Company> search(@RequestBody Company company) {
        return service.search(company);
    }
}
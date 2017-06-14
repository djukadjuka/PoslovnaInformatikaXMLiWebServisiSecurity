package com.company.invoicing.controllers;

import com.company.invoicing.intercepters.AuthorityAnnotation;
import com.company.invoicing.models.Fiscal_year;
import com.company.invoicing.services.Fiscal_yearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value ="/fiscal_year")
public class Fiscal_yearController {

    @Autowired
    private Fiscal_yearService service;

    @AuthorityAnnotation(method = "getAll",table = "fiscal_year")
    @RequestMapping(method = RequestMethod.GET)
    public List<Fiscal_year> getAll(){
        return service.findAll();
    }

    @AuthorityAnnotation(method = "getOne",table = "fiscal_year")
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Fiscal_year find(@PathVariable long id){
        return service.findOne(id);
    }

    @AuthorityAnnotation(method = "create",table = "fiscal_year")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Fiscal_year fiscal_year){
        service.create(fiscal_year);
    }

    @AuthorityAnnotation(method = "update",table = "fiscal_year")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Fiscal_year fiscal_year) {
    service.update(fiscal_year);
    }

    @AuthorityAnnotation(method = "delete",table = "fiscal_year")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @AuthorityAnnotation(method = "search",table = "fiscal_year")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Fiscal_year> search(@RequestBody Fiscal_year fiscal_year) {
        return service.search(fiscal_year);
    }
}
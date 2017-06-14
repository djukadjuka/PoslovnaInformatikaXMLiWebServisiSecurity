package com.company.invoicing.controllers;

import com.company.invoicing.intercepters.AuthorityAnnotation;
import com.company.invoicing.models.Vat_type;
import com.company.invoicing.services.Vat_typeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value ="/vat_type")
public class Vat_typeController {

    @Autowired
    private Vat_typeService service;

    @AuthorityAnnotation(method = "getAll",table = "vat_type")
    @RequestMapping(method = RequestMethod.GET)
    public List<Vat_type> getAll(){
        return service.findAll();
    }

    @AuthorityAnnotation(method = "getOne",table = "vat_type")
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Vat_type find(@PathVariable long id){
        return service.findOne(id);
    }

    @AuthorityAnnotation(method = "create",table = "vat_type")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Vat_type vat_type){
        service.create(vat_type);
    }

    @AuthorityAnnotation(method = "update",table = "vat_type")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Vat_type vat_type) {
    service.update(vat_type);
    }

    @AuthorityAnnotation(method = "delete",table = "vat_type")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @AuthorityAnnotation(method = "search",table = "vat_type")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Vat_type> search(@RequestBody Vat_type vat_type) {
        return service.search(vat_type);
    }
}
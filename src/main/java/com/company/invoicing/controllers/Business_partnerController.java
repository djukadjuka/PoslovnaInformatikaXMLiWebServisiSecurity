package com.company.invoicing.controllers;

import com.company.invoicing.intercepters.AuthorityAnnotation;
import com.company.invoicing.models.Business_partner;
import com.company.invoicing.services.Business_partnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value ="/business_partner")
public class Business_partnerController {

    @Autowired
    private Business_partnerService service;

    @AuthorityAnnotation(method = "getAll",table = "business_partner")
    @RequestMapping(method = RequestMethod.GET)
    public List<Business_partner> getAll(){
        return service.findAll();
    }

    @AuthorityAnnotation(method = "getOne",table = "business_partner")
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Business_partner find(@PathVariable long id){
        return service.findOne(id);
    }

    @AuthorityAnnotation(method = "create",table = "business_partner")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Business_partner business_partner){
        service.create(business_partner);
    }

    @AuthorityAnnotation(method = "update",table = "business_partner")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Business_partner business_partner) {
    service.update(business_partner);
    }

    @AuthorityAnnotation(method = "delete",table = "business_partner")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @AuthorityAnnotation(method = "search",table = "business_partner")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Business_partner> search(@RequestBody Business_partner business_partner) {
        return service.search(business_partner);
    }
}
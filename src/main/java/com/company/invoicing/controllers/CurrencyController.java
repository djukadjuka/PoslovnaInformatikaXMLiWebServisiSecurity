package com.company.invoicing.controllers;

import com.company.invoicing.intercepters.AuthorityAnnotation;
import com.company.invoicing.models.Currency;
import com.company.invoicing.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value ="/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService service;

    @AuthorityAnnotation(method = "getAll",table = "currency")
    @RequestMapping(method = RequestMethod.GET)
    public List<Currency> getAll(){
        return service.findAll();
    }

    @AuthorityAnnotation(method = "getOne",table = "currency")
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Currency find(@PathVariable long id){
        return service.findOne(id);
    }

    @AuthorityAnnotation(method = "create",table = "currency")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Currency currency){
        service.create(currency);
    }

    @AuthorityAnnotation(method = "update",table = "currency")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Currency currency) {
    service.update(currency);
    }

    @AuthorityAnnotation(method = "delete",table = "currency")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @AuthorityAnnotation(method = "search",table = "currency")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Currency> search(@RequestBody Currency currency) {
        return service.search(currency);
    }
}
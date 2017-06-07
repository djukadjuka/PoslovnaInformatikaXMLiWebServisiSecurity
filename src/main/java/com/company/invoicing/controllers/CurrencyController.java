package com.company.invoicing.controllers;

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

    @RequestMapping(method = RequestMethod.GET)
    public List<Currency> getAll(){
        return service.findAll();
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Currency find(@PathVariable long id){
        return service.findOne(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Currency currency){
        service.create(currency);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Currency currency) {
    service.update(currency);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Currency> search(@RequestBody Currency currency) {
        return service.search(currency);
    }
}
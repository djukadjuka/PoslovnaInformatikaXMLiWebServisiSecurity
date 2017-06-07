package com.company.invoicing.controllers;

import com.company.invoicing.models.Vat_rate;
import com.company.invoicing.services.Vat_rateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value ="/vat_rate")
public class Vat_rateController {

    @Autowired
    private Vat_rateService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Vat_rate> getAll(){
        return service.findAll();
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Vat_rate find(@PathVariable long id){
        return service.findOne(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Vat_rate vat_rate){
        service.create(vat_rate);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Vat_rate vat_rate) {
    service.update(vat_rate);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Vat_rate> search(@RequestBody Vat_rate vat_rate) {
        return service.search(vat_rate);
    }
}
package com.company.invoicing.controllers;

import com.company.invoicing.models.Invoice_item;
import com.company.invoicing.services.Invoice_itemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value ="/invoice_item")
public class Invoice_itemController {

    @Autowired
    private Invoice_itemService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Invoice_item> getAll(){
        return service.findAll();
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Invoice_item find(@PathVariable long id){
        return service.findOne(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Invoice_item invoice_item){
        service.create(invoice_item);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Invoice_item invoice_item) {
    service.update(invoice_item);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Invoice_item> search(@RequestBody Invoice_item invoice_item) {
        return service.search(invoice_item);
    }
}
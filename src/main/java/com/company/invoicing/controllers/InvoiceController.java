package com.company.invoicing.controllers;

import com.company.invoicing.intercepters.AuthorityAnnotation;
import com.company.invoicing.models.Invoice;
import com.company.invoicing.models.Invoice_item;
import com.company.invoicing.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value ="/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @AuthorityAnnotation(method = "getAll",table = "invoice")
    @RequestMapping(method = RequestMethod.GET)
    public List<Invoice> getAll(){
        return service.findAll();
    }

    @AuthorityAnnotation(method = "getOne",table = "invoice")
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Invoice find(@PathVariable long id){
        return service.findOne(id);
    }

    @AuthorityAnnotation(method = "getAll",table = "invoice")
    @RequestMapping(value="/allIIs/{id}", method = RequestMethod.POST)
    public List<Invoice_item> allPOIs(@PathVariable long id){
        return service.allIIs(id);
    }

    @AuthorityAnnotation(method = "create",table = "invoice")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Invoice invoice){
        service.create(invoice);
    }

    @AuthorityAnnotation(method = "update",table = "invoice")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Invoice invoice) {
    service.update(invoice);
    }

    @AuthorityAnnotation(method = "delete",table = "invoice")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @AuthorityAnnotation(method = "search",table = "invoice")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Invoice> search(@RequestBody Invoice invoice) {
        return service.search(invoice);
    }

    @AuthorityAnnotation(method = "generateInvoice",table = "invoice")
    @RequestMapping(value = "/generateInvoice/{id}", method = RequestMethod.POST)
    public void generateInvoice(@PathVariable long id) {
        service.generateInvoice(id);
    }
}
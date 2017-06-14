package com.company.invoicing.controllers;

import com.company.invoicing.intercepters.AuthorityAnnotation;
import com.company.invoicing.models.Purchase_order;
import com.company.invoicing.models.Purchase_order_item;
import com.company.invoicing.services.Purchase_orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value ="/purchase_order")
public class Purchase_orderController {

    @Autowired
    private Purchase_orderService service;

    @AuthorityAnnotation(method = "getAll",table = "purchase_order")
    @RequestMapping(method = RequestMethod.GET)
    public List<Purchase_order> getAll(){
        return service.findAll();
    }

    @AuthorityAnnotation(method = "getOne",table = "purchase_order")
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Purchase_order find(@PathVariable long id){
        return service.findOne(id);
    }

    @AuthorityAnnotation(method = "getAll",table = "purchase_order")
    @RequestMapping(value="/allPOIs/{id}", method = RequestMethod.POST)
    public List<Purchase_order_item> allPOIs(@PathVariable long id){
        return service.allPOIs(id);
    }

    @AuthorityAnnotation(method = "create",table = "purchase_order")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Purchase_order purchase_order){
        service.create(purchase_order);
    }

    @AuthorityAnnotation(method = "update",table = "purchase_order")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Purchase_order purchase_order) {
    service.update(purchase_order);
    }

    @AuthorityAnnotation(method = "delete",table = "purchase_order")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @AuthorityAnnotation(method = "search",table = "purchase_order")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Purchase_order> search(@RequestBody Purchase_order purchase_order) {
        return service.search(purchase_order);
    }

    @AuthorityAnnotation(method = "exportToXML",table = "purchase_order")
    @RequestMapping(value = "/exportToXML/{id}", method = RequestMethod.POST)
    public void exportToXML(@PathVariable Long id) {
        service.exportToXML(id);
    }
}
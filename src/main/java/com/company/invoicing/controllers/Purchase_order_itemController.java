package com.company.invoicing.controllers;

import com.company.invoicing.intercepters.AuthorityAnnotation;
import com.company.invoicing.models.Purchase_order_item;
import com.company.invoicing.services.Purchase_order_itemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value ="/purchase_order_item")
public class Purchase_order_itemController {

    @Autowired
    private Purchase_order_itemService service;

    @AuthorityAnnotation(method = "getAll",table = "purchase_order_item")
    @RequestMapping(method = RequestMethod.GET)
    public List<Purchase_order_item> getAll(){
        return service.findAll();
    }

    @AuthorityAnnotation(method = "getOne",table = "purchase_order_item")
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Purchase_order_item find(@PathVariable long id){
        return service.findOne(id);
    }

    @AuthorityAnnotation(method = "create",table = "purchase_order_item")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Purchase_order_item purchase_order_item){
        service.create(purchase_order_item);
    }

    @AuthorityAnnotation(method = "update",table = "purchase_order_item")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Purchase_order_item purchase_order_item) {
    service.update(purchase_order_item);
    }

    @AuthorityAnnotation(method = "delete",table = "purchase_order_item")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @AuthorityAnnotation(method = "search",table = "purchase_order_item")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Purchase_order_item> search(@RequestBody Purchase_order_item purchase_order_item) {
        return service.search(purchase_order_item);
    }
}
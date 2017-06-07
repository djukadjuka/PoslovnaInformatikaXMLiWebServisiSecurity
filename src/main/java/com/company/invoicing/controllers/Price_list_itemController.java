package com.company.invoicing.controllers;

import com.company.invoicing.models.Price_list_item;
import com.company.invoicing.services.Price_list_itemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value ="/price_list_item")
public class Price_list_itemController {

    @Autowired
    private Price_list_itemService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Price_list_item> getAll(){
        return service.findAll();
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Price_list_item find(@PathVariable long id){
        return service.findOne(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Price_list_item price_list_item){
        service.create(price_list_item);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Price_list_item price_list_item) {
    service.update(price_list_item);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Price_list_item> search(@RequestBody Price_list_item price_list_item) {
        return service.search(price_list_item);
    }
}
package com.company.invoicing.controllers;

import com.company.invoicing.models.Item;
import com.company.invoicing.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value ="/item")
public class ItemController {

    @Autowired
    private ItemService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Item> getAll(){
        return service.findAll();
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Item find(@PathVariable long id){
        return service.findOne(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Item item){
        service.create(item);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Item item) {
    service.update(item);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Item> search(@RequestBody Item item) {
        return service.search(item);
    }
}
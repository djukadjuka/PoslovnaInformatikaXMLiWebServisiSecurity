package com.company.invoicing.controllers;

import com.company.invoicing.models.Item_group;
import com.company.invoicing.services.Item_groupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value ="/item_group")
public class Item_groupController {

    @Autowired
    private Item_groupService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Item_group> getAll(){
        return service.findAll();
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Item_group find(@PathVariable long id){
        return service.findOne(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Item_group item_group){
        service.create(item_group);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Item_group item_group) {
    service.update(item_group);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Item_group> search(@RequestBody Item_group item_group) {
        return service.search(item_group);
    }
}
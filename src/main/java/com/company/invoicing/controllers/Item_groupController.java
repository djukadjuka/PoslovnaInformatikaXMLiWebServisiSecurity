package com.company.invoicing.controllers;

import com.company.invoicing.intercepters.AuthorityAnnotation;
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

    @AuthorityAnnotation(method = "getAll",table = "item_group")
    @RequestMapping(method = RequestMethod.GET)
    public List<Item_group> getAll(){
        return service.findAll();
    }

    @AuthorityAnnotation(method = "getOne",table = "item_group")
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Item_group find(@PathVariable long id){
        return service.findOne(id);
    }

    @AuthorityAnnotation(method = "create",table = "item_group")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Item_group item_group){
        service.create(item_group);
    }

    @AuthorityAnnotation(method = "update",table = "item_group")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Item_group item_group) {
    service.update(item_group);
    }

    @AuthorityAnnotation(method = "delete",table = "item_group")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @AuthorityAnnotation(method = "search",table = "item_group")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Item_group> search(@RequestBody Item_group item_group) {
        return service.search(item_group);
    }
}
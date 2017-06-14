package com.company.invoicing.controllers;

import com.company.invoicing.intercepters.AuthorityAnnotation;
import com.company.invoicing.models.Price_list;
import com.company.invoicing.services.Price_listService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value ="/price_list")
public class Price_listController {

    @Autowired
    private Price_listService service;

    @AuthorityAnnotation(method = "getAll",table = "price_list")
    @RequestMapping(method = RequestMethod.GET)
    public List<Price_list> getAll(){
        return service.findAll();
    }

    @AuthorityAnnotation(method = "getOne",table = "price_list")
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Price_list find(@PathVariable long id){
        return service.findOne(id);
    }

    @AuthorityAnnotation(method = "create",table = "price_list")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Price_list price_list){
        service.create(price_list);
    }

    @AuthorityAnnotation(method = "update",table = "price_list")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Price_list price_list) {
    service.update(price_list);
    }

    @AuthorityAnnotation(method = "delete",table = "price_list")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @AuthorityAnnotation(method = "search",table = "price_list")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Price_list> search(@RequestBody Price_list price_list) {
        return service.search(price_list);
    }

    @AuthorityAnnotation(method = "copyPriceList",table = "price_list")
    @RequestMapping(value = "/copyPriceList/{id}/{date}/{percent}", method = RequestMethod.POST)
    public void copyPriceList(@PathVariable Long id, @PathVariable Long date, @PathVariable double percent) {
        service.copyPriceList(id,date,percent);
    }
}
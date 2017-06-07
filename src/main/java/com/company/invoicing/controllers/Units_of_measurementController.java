package com.company.invoicing.controllers;

import com.company.invoicing.models.Units_of_measurement;
import com.company.invoicing.services.Units_of_measurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value ="/units_of_measurement")
public class Units_of_measurementController {

    @Autowired
    private Units_of_measurementService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Units_of_measurement> getAll(){
        return service.findAll();
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Units_of_measurement find(@PathVariable long id){
        return service.findOne(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Units_of_measurement units_of_measurement){
        service.create(units_of_measurement);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Units_of_measurement units_of_measurement) {
    service.update(units_of_measurement);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Units_of_measurement> search(@RequestBody Units_of_measurement units_of_measurement) {
        return service.search(units_of_measurement);
    }
}
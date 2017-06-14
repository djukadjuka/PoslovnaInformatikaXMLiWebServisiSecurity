package com.company.invoicing.controllers;

import com.company.invoicing.intercepters.AuthorityAnnotation;
import com.company.invoicing.models.Permission;
import com.company.invoicing.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 6/1/2017.
 */
@RestController
@RequestMapping("/permission")
public class PermissionsController {
    @Autowired
    private PermissionService service;

    @AuthorityAnnotation(method = "getAll",table = "permission")
    @RequestMapping(method = RequestMethod.GET)
    public List<Permission> all(){
        return service.findAll();
    }

    @AuthorityAnnotation(method = "getOne",table = "permission")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Permission findOne(@PathVariable Long id) {
        return service.findOne(id);
    }

    @AuthorityAnnotation(method = "create",table = "permission")
    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public void create(@RequestBody Permission permission){
        service.save(permission);
    }

    @AuthorityAnnotation(method = "delete",table = "permission")
    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @AuthorityAnnotation(method = "update",table = "permission")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Permission permission) {
        service.update(permission);
    }
}

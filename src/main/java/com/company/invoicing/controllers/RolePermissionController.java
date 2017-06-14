package com.company.invoicing.controllers;

import com.company.invoicing.intercepters.AuthorityAnnotation;
import com.company.invoicing.models.RolePermission;
import com.company.invoicing.services.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 6/1/2017.
 */
@RestController
@RequestMapping("/role_permission")
public class RolePermissionController {

    @Autowired
    private RolePermissionService service;

    @AuthorityAnnotation(method = "getAll",table = "role_permission")
    @RequestMapping(method = RequestMethod.GET)
    public List<RolePermission> all(){
        return service.findAll();
    }

    @AuthorityAnnotation(method = "getOne",table = "role_permission")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RolePermission findOne(@PathVariable Long id) {
        return service.findOne(id);
    }

    @AuthorityAnnotation(method = "create",table = "role_permission")
    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public void create(@RequestBody RolePermission rolepermission){
        service.save(rolepermission);
    }

    @AuthorityAnnotation(method = "delete",table = "role_permission")
    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @AuthorityAnnotation(method = "update",table = "role_permission")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody RolePermission rolepermission) {
        service.update(rolepermission);
    }
}

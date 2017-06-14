package com.company.invoicing.controllers;

import com.company.invoicing.intercepters.AuthorityAnnotation;
import com.company.invoicing.models.Role;
import com.company.invoicing.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 6/1/2017.
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService service;

    @AuthorityAnnotation(method = "getAll",table = "role")
    @RequestMapping(method = RequestMethod.GET)
    public List<Role> all(){
        return service.findAll();
    }

    @AuthorityAnnotation(method = "getOne",table = "role")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Role findOne(@PathVariable Long role_id) {
        return service.findOne(role_id);
    }

    @AuthorityAnnotation(method = "create",table = "role")
    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public void create(@RequestBody Role role){
        service.save(role);
    }

    @AuthorityAnnotation(method = "delete",table = "role")
    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @AuthorityAnnotation(method = "update",table = "role")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Role role) {
        service.update(role);
    }

}

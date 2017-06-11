package com.company.invoicing.security;

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

    //@CustomAnnotation("rolespermissions-all")
    @RequestMapping(method = RequestMethod.GET)
    public List<RolePermission> all(){
        return service.findAll();
    }

    //@CustomAnnotation("rolespermissions-one")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RolePermission findOne(@PathVariable Long id) {
        return service.findOne(id);
    }

    //@CustomAnnotation("rolespermissions-create")
    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public void create(@RequestBody RolePermission rolepermission){
        service.save(rolepermission);
    }

    //@CustomAnnotation("rolespermissions-delete")
    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    //@CustomAnnotation("rolespermissions-update")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody RolePermission rolepermission) {
        service.update(rolepermission);
    }
}

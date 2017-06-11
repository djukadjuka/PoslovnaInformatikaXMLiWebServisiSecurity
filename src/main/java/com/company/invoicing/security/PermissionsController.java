package com.company.invoicing.security;

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

    //@CustomAnnotation("permissions-all")
    @RequestMapping(method = RequestMethod.GET)
    public List<Permission> all(){
        return service.findAll();
    }

    //@CustomAnnotation("permissions-one")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Permission findOne(@PathVariable Long id) {
        return service.findOne(id);
    }

    //@CustomAnnotation("permissions-create")
    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public void create(@RequestBody Permission permission){
        service.save(permission);
    }

    //@CustomAnnotation("permissions-delete")
    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    //@CustomAnnotation("permissions-update")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Permission permission) {
        service.update(permission);
    }
}

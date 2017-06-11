package com.company.invoicing.security;

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

    //@CustomAnnotation("roles-all")
    @RequestMapping(method = RequestMethod.GET)
    public List<Role> all(){
        return service.findAll();
    }

    //@CustomAnnotation("roles-one")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Role findOne(@PathVariable Long role_id) {
        return service.findOne(role_id);
    }

    //@CustomAnnotation("roles-create")
    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public void create(@RequestBody Role role){
        service.save(role);
    }

    //@CustomAnnotation("roles-delete")
    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    //@CustomAnnotation("roles-update")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Role role) {
        service.update(role);
    }

}

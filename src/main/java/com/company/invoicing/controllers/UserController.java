package com.company.invoicing.controllers;

import com.company.invoicing.models.User;
import com.company.invoicing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 22.12.2016..
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    //@CustomAnnotation("users-all")
    @RequestMapping(method = RequestMethod.GET)
    public List<User> all(){
        return service.findAll();
    }

    //@CustomAnnotation("users-one")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findOne(@PathVariable Long id) {
        return service.findOne(id);
    }

    //@CustomAnnotation("users-create")
    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public void create(@RequestBody User user){
        service.save(user);
    }

    //@CustomAnnotation("users-delete")
    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    //@CustomAnnotation("users-update")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody User user) {
        service.update(user);
    }


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public User authenticate (@RequestBody User user) {
        return service.authenticate(user);
    }

    @RequestMapping(value="/change_password/{username}/{password}", method = RequestMethod.POST)
    public void change_password(@PathVariable String username, @PathVariable String password){
        service.change_password(username,password);
    }


}

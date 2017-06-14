package com.company.invoicing.controllers;

import com.company.invoicing.intercepters.AuthorityAnnotation;
import com.company.invoicing.models.User;
import com.company.invoicing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by User on 22.12.2016..
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private UserService service;

    @AuthorityAnnotation(method = "getAll",table = "user")
    @RequestMapping(method = RequestMethod.GET)
    public List<User> all(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader(tokenHeader);
        System.out.println("OVO JE JEBENI DOBAVLJENI TOKEN: "+token);
        return service.findAll();
    }

    @AuthorityAnnotation(method = "getOne",table = "user")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findOne(@PathVariable Long id) {
        return service.findOne(id);
    }

    @AuthorityAnnotation(method = "create",table = "user")
    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public void create(@RequestBody User user){
        service.save(user);
    }

    @AuthorityAnnotation(method = "delete",table = "user")
    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @AuthorityAnnotation(method = "update",table = "user")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody User user) {
        service.update(user);
    }


    /*@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public User authenticate (@RequestBody User user) {
        return service.authenticate(user);
    }*/

    @AuthorityAnnotation(method = "change_password",table = "user")
    @RequestMapping(value="/change_password/{username}/{password}", method = RequestMethod.POST)
    public void change_password(@PathVariable String username, @PathVariable String password){
        service.change_password(username,password);
    }


}

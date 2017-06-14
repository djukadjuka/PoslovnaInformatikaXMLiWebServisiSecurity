package com.company.invoicing.controllers;

import com.company.invoicing.intercepters.AuthorityAnnotation;
import com.company.invoicing.models.Invoice_item;
import com.company.invoicing.security.JwtTokenUtil;
import com.company.invoicing.security.JwtUser;
import com.company.invoicing.security.JwtUserDetailsServiceImpl;
import com.company.invoicing.services.Invoice_itemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping(value ="/invoice_item")
public class Invoice_itemController {

    @Autowired
    private Invoice_itemService service;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtUserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @AuthorityAnnotation(method = "getAll",table = "invoice_item")
    @RequestMapping(method = RequestMethod.GET)
    public List<Invoice_item> getAll(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if(user.getRole().getName().equals("superuser"))
            return service.findAll();
        else
            return service.findAllForUser(user.getCompany().getCompany_id());
    }

    @AuthorityAnnotation(method = "getOne",table = "invoice_item")
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Invoice_item find(@PathVariable long id){
        return service.findOne(id);
    }

    @AuthorityAnnotation(method = "create",table = "invoice_item")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Invoice_item invoice_item){
        service.create(invoice_item);
    }

    @AuthorityAnnotation(method = "update",table = "invoice_item")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Invoice_item invoice_item) {
    service.update(invoice_item);
    }

    @AuthorityAnnotation(method = "delete",table = "invoice_item")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @AuthorityAnnotation(method = "search",table = "invoice_item")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Invoice_item> search(@RequestBody Invoice_item invoice_item) {
        return service.search(invoice_item);
    }
}
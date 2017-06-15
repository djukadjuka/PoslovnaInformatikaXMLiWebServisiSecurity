package com.company.invoicing.controllers;

import com.company.invoicing.intercepters.AuthorityAnnotation;
import com.company.invoicing.models.Invoice;
import com.company.invoicing.models.Invoice_item;
import com.company.invoicing.security.JwtTokenUtil;
import com.company.invoicing.security.JwtUser;
import com.company.invoicing.security.JwtUserDetailsServiceImpl;
import com.company.invoicing.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping(value ="/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtUserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @AuthorityAnnotation(method = "getAll",table = "invoice")
    @RequestMapping(method = RequestMethod.GET)
    public List<Invoice> getAll(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if(user.getRole().getName().equals("superuser"))
            return service.findAll();
        else
            return service.findAllForUser(user.getCompany().getCompany_id());
    }

    @AuthorityAnnotation(method = "getOne",table = "invoice")
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Invoice find(@PathVariable long id){
        return service.findOne(id);
    }

    @AuthorityAnnotation(method = "getAll",table = "invoice")
    @RequestMapping(value="/allIIs/{id}", method = RequestMethod.POST)
    public List<Invoice_item> allPOIs(@PathVariable long id){
        return service.allIIs(id);
    }

    @AuthorityAnnotation(method = "create",table = "invoice")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Invoice invoice,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        //service.create(invoice);
        service.create(invoice,username);
    }

    @AuthorityAnnotation(method = "update",table = "invoice")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Invoice invoice,HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        //service.update(invoice);
        service.update(invoice,username);
    }

    @AuthorityAnnotation(method = "delete",table = "invoice")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id,HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        //service.remove(id);
        service.remove(id,username);
    }

    @AuthorityAnnotation(method = "search",table = "invoice")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Invoice> search(@RequestBody Invoice invoice) {
        return service.search(invoice);
    }

    @AuthorityAnnotation(method = "generateInvoice",table = "invoice")
    @RequestMapping(value = "/generateInvoice/{id}", method = RequestMethod.POST)
    public void generateInvoice(@PathVariable long id,HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        //service.generateInvoice(id);
        service.generateInvoice(id,username);
    }
}
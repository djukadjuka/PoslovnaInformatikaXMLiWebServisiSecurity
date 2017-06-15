package com.company.invoicing.controllers;

import com.company.invoicing.intercepters.AuthorityAnnotation;
import com.company.invoicing.models.Purchase_order;
import com.company.invoicing.models.Purchase_order_item;
import com.company.invoicing.security.JwtTokenUtil;
import com.company.invoicing.security.JwtUser;
import com.company.invoicing.security.JwtUserDetailsServiceImpl;
import com.company.invoicing.services.Purchase_orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping(value ="/purchase_order")
public class Purchase_orderController {

    @Autowired
    private Purchase_orderService service;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtUserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @AuthorityAnnotation(method = "getAll",table = "purchase_order")
    @RequestMapping(method = RequestMethod.GET)
    public List<Purchase_order> getAll(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if(user.getRole().getName().equals("superuser"))
            return service.findAll();
        else
            return service.findAllForUser(user.getCompany().getCompany_id());
    }

    @AuthorityAnnotation(method = "getOne",table = "purchase_order")
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Purchase_order find(@PathVariable long id){
        return service.findOne(id);
    }

    @AuthorityAnnotation(method = "getAll",table = "purchase_order")
    @RequestMapping(value="/allPOIs/{id}", method = RequestMethod.POST)
    public List<Purchase_order_item> allPOIs(@PathVariable long id){
        return service.allPOIs(id);
    }

    @AuthorityAnnotation(method = "create",table = "purchase_order")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Purchase_order purchase_order,HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        //service.create(purchase_order);
        service.create(purchase_order,username);
    }

    @AuthorityAnnotation(method = "update",table = "purchase_order")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Purchase_order purchase_order,HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        //service.update(purchase_order);
        service.update(purchase_order,username);
    }

    @AuthorityAnnotation(method = "delete",table = "purchase_order")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id,HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        //service.remove(id);
        service.remove(id,username);
    }

    @AuthorityAnnotation(method = "search",table = "purchase_order")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Purchase_order> search(@RequestBody Purchase_order purchase_order) {
        return service.search(purchase_order);
    }

    @AuthorityAnnotation(method = "exportToXML",table = "purchase_order")
    @RequestMapping(value = "/exportToXML/{id}", method = RequestMethod.POST)
    public void exportToXML(@PathVariable Long id) {
        service.exportToXML(id);
    }


}
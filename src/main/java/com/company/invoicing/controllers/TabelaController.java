package com.company.invoicing.controllers;

import com.company.invoicing.intercepters.AuthorityAnnotation;
import com.company.invoicing.models.Tabela;
import com.company.invoicing.services.TabelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 6/14/2017.
 */
@RestController
@RequestMapping("/tabela")
public class TabelaController {

    @Autowired
    private TabelaService service;

    @AuthorityAnnotation(method = "getAll",table = "tabela")
    @RequestMapping(method = RequestMethod.GET)
    public List<Tabela> all(){
        return service.findAll();
    }

    @AuthorityAnnotation(method = "getOne",table = "tabela")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Tabela findOne(@PathVariable Long role_id) {
        return service.findOne(role_id);
    }

    @AuthorityAnnotation(method = "create",table = "tabela")
    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public void create(@RequestBody Tabela tabela){
        service.save(tabela);
    }

    @AuthorityAnnotation(method = "delete",table = "tabela")
    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @AuthorityAnnotation(method = "update",table = "tabela")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Tabela tabela) {
        service.update(tabela);
    }

}

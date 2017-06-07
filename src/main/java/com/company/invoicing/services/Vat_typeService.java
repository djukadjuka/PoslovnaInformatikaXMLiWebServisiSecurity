package com.company.invoicing.services;

import com.company.invoicing.models.Vat_type;
import com.company.invoicing.repositoriums.Vat_typeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Vat_typeService {

    @Autowired
    private Vat_typeRepository repository;

    public List<Vat_type> findAll(){
        return repository.findAll();
    }

    public Vat_type findOne(long id){
        return repository.findOne(id);
    }

    public void create(Vat_type vat_type){
        repository.save(vat_type);
    }

    public void update(Vat_type vat_type){
        repository.save(vat_type);
    }

    public void remove(Long id){
        repository.delete(id);
    }

    public List<Vat_type> search(Vat_type vat_type){
        List<Vat_type> vatTypes=new ArrayList<>();
        for(Vat_type vt : repository.findAll()){
            if(vat_type.getName()==null || vat_type.getName().equals("") || vt.getName().toLowerCase().contains(vat_type.getName().toLowerCase()))
                vatTypes.add(vt);
        }
        return vatTypes;
    }

}

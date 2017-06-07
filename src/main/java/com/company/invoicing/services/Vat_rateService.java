package com.company.invoicing.services;

import com.company.invoicing.models.Vat_rate;
import com.company.invoicing.repositoriums.Vat_rateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Vat_rateService {

    @Autowired
    private Vat_rateRepository repository;

    public List<Vat_rate> findAll(){
        return repository.findAll();
    }

    public Vat_rate findOne(long id){
        return repository.findOne(id);
    }

    public void create(Vat_rate vat_rate){
        repository.save(vat_rate);
    }

    public void update(Vat_rate vat_rate){
        repository.save(vat_rate);
    }

    public void remove(Long id){
        repository.delete(id);
    }

    public List<Vat_rate> search(Vat_rate vat_rate){
        List<Vat_rate> vat_rates=new ArrayList<>();
        for(Vat_rate vr : repository.findAll()){
            if(vat_rate.getPercentage_of_vatr()==0 || vat_rate.getPercentage_of_vatr()==vr.getPercentage_of_vatr()){
                if(vat_rate.getDate()==null || vat_rate.getDate().equals("") || vat_rate.getDate().getTime()>=vr.getDate().getTime()){
                    if(vat_rate.getVat_type()==null || vat_rate.getVat_type().getVat_type_id()==vr.getVat_type().getVat_type_id()){
                        vat_rates.add(vr);
                    }
                }
            }
        }
        return vat_rates;
    }
}

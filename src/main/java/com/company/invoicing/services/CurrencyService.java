package com.company.invoicing.services;

import com.company.invoicing.models.Currency;
import com.company.invoicing.repositoriums.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository repository;

    public List<Currency> findAll(){
        return repository.findAll();
    }

    public Currency findOne(long id){
        return repository.findOne(id);
    }

    public void create(Currency currency){
        repository.save(currency);
    }

    public void update(Currency currency){
        repository.save(currency);
    }

    public void remove(Long id){
        repository.delete(id);
    }

    public List<Currency> search(Currency currency){
        List<Currency> currencies=new ArrayList<>();
        for(Currency c : repository.findAll()){
            if(currency.getName()==null || currency.getName().equals("") || c.getName().toLowerCase().contains(currency.getName().toLowerCase())){
                if(currency.getAbbreviation()==null || currency.getAbbreviation().equals("") || c.getAbbreviation().toLowerCase().contains(currency.getAbbreviation().toLowerCase())){
                    currencies.add(c);
                }
            }
        }
        return currencies;
    }

}

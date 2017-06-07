package com.company.invoicing.services;

import com.company.invoicing.models.Price_list;
import com.company.invoicing.models.Price_list_item;
import com.company.invoicing.repositoriums.Price_listRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Price_listService {

    @Autowired
    private Price_listRepository repository;

    @Autowired
    private Price_list_itemService service;

    public List<Price_list> findAll(){
        return repository.findAll();
    }

    public Price_list findOne(long id){
        return repository.findOne(id);
    }

    public void create(Price_list price_list){
        repository.save(price_list);
    }

    public void update(Price_list price_list){
        repository.save(price_list);
    }

    public void remove(Long id){
        repository.delete(id);
    }

    public List<Price_list> search(Price_list price_list){
        List<Price_list> price_lists=new ArrayList<>();
        for(Price_list pl : repository.findAll()){
            if(price_list.getValid_from()==null || price_list.getValid_from().equals("") || price_list.getValid_from().getTime()>=pl.getValid_from().getTime()){
                    price_lists.add(pl);

            }
        }
        return price_lists;
    }

    public void copyPriceList(Long id, Long date, double percent){
        Date data=new Date(date);
        Price_list price_list=new Price_list();
        price_list.setValid_from(data);
        repository.save(price_list);
        price_list=repository.findAll().get(repository.findAll().size()-1);
        System.out.println(price_list.getPrice_list_id());
        service.copyPriceListItems(id,price_list,percent);
    }

}

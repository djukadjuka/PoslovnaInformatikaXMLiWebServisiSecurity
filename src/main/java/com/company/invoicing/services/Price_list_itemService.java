package com.company.invoicing.services;

import com.company.invoicing.models.Price_list;
import com.company.invoicing.models.Price_list_item;
import com.company.invoicing.repositoriums.Price_list_itemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Price_list_itemService {

    @Autowired
    private Price_list_itemRepository repository;

    public List<Price_list_item> findAll(){
        return repository.findAll();
    }

    public Price_list_item findOne(long id){
        return repository.findOne(id);
    }

    public void create(Price_list_item price_list_item){
        repository.save(price_list_item);
    }

    public void update(Price_list_item price_list_item){
        repository.save(price_list_item);
    }

    public void remove(Long id){
        repository.delete(id);
    }

    public List<Price_list_item> search(Price_list_item price_list_item){
        List<Price_list_item> price_list_items=new ArrayList<>();
        for(Price_list_item pli : repository.findAll()){
            if(price_list_item.getPrice()==0 || price_list_item.getPrice()==pli.getPrice()){
                if(price_list_item.getItem()==null || price_list_item.getItem().getItem_id()==pli.getItem().getItem_id()){
                    if(price_list_item.getPrice_list()==null || price_list_item.getPrice_list().getPrice_list_id()==pli.getPrice_list().getPrice_list_id()){
                        if(price_list_item.getCurrency()==null || price_list_item.getCurrency().getCurrency_id()==pli.getCurrency().getCurrency_id()){
                            price_list_items.add(pli);
                        }
                    }
                }
            }
        }
        return price_list_items;
    }

    public void copyPriceListItems(Long oldId, Price_list price_list, double percent){
        for(Price_list_item pli :repository.findAll()){
            if(pli.getPrice_list().getPrice_list_id()==oldId){
                Price_list_item p=new Price_list_item();
                p.setPrice_list(price_list);
                p.setPrice(pli.getPrice()*(percent/100));
                p.setCurrency(pli.getCurrency());
                p.setItem(pli.getItem());
                repository.save(p);
            }
        }
    }
}
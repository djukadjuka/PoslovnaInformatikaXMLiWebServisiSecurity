package com.company.invoicing.services;

import com.company.invoicing.models.Purchase_order_item;
import com.company.invoicing.repositoriums.Purchase_order_itemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Purchase_order_itemService {

    @Autowired
    private Purchase_order_itemRepository repository;

    public List<Purchase_order_item> findAll(){
        return repository.findAll();
    }

    public Purchase_order_item findOne(long id){
        return repository.findOne(id);
    }

    public void create(Purchase_order_item purchase_order_item){
        repository.save(purchase_order_item);
    }

    public void update(Purchase_order_item purchase_order_item){
        repository.save(purchase_order_item);
    }

    public void remove(Long id){
        repository.delete(id);
    }

    public List<Purchase_order_item> search(Purchase_order_item purchase_order_item){
        List<Purchase_order_item> purchase_order_items=new ArrayList<>();
        for(Purchase_order_item poi : repository.findAll()){
            if(purchase_order_item.getTotal_amount()==0 || purchase_order_item.getTotal_amount()==poi.getTotal_amount()){
                if(purchase_order_item.getPurchase_order()==null || purchase_order_item.getPurchase_order().getPurchase_order_id()==poi.getPurchase_order().getPurchase_order_id()){
                    if(purchase_order_item.getItem()==null || purchase_order_item.getItem().getItem_id()==poi.getItem().getItem_id()){
                        purchase_order_items.add(poi);
                    }
                }
            }
        }

        return purchase_order_items;
    }
}

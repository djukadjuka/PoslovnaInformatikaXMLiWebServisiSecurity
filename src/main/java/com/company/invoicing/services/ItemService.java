package com.company.invoicing.services;

import com.company.invoicing.models.Item;
import com.company.invoicing.models.Price_list_item;
import com.company.invoicing.models.Vat_rate;
import com.company.invoicing.repositoriums.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public List<Item> findAll(){
        return repository.findAll();
    }

    public List<Item> findAllValid(){
        List<Item> items=new ArrayList<>();
        for(Item item: repository.findAll()){
            boolean prosloPrvo=false;
            boolean prosloDrugo=false;
            for(Price_list_item pli : item.getPrice_list_items()){
                if(pli.getPrice_list().getValid_from().getTime()<=new Date().getTime()){
                    prosloPrvo=true;
                    break;
                }
            }
            for(Vat_rate vr:item.getItem_group().getVat_type().getVat_rates()){
                if(vr.getDate().getTime()<=new Date().getTime()){
                    prosloDrugo=true;
                    break;
                }
            }
            if(prosloDrugo && prosloPrvo) {
                items.add(item);
            }

        }
        return items;
    }

    public Item findOne(long id){
        return repository.findOne(id);
    }

    public void create(Item item){
        repository.save(item);
    }

    public void update(Item item){
        repository.save(item);
    }

    public void remove(Long id){
        repository.delete(id);
    }

    public List<Item> search(Item item){
        List<Item> invoices=new ArrayList<>();
        for(Item i : repository.findAll()){
            if(item.getName()==null || item.getName().equals("") || i.getName().toLowerCase().contains(item.getName().toLowerCase())){
                if(item.getIs_service()==i.getIs_service()){
                    if(item.getDescription()==null || item.getDescription().equals("") || i.getDescription().toLowerCase().contains(item.getDescription().toLowerCase())){
                        if(item.getItem_group()==null || item.getItem_group().getItem_group_id()==i.getItem_group().getItem_group_id()){
                            if(item.getUnits_of_measurement()==null || item.getUnits_of_measurement().getUnits_of_measurement_id()==i.getUnits_of_measurement().getUnits_of_measurement_id()){
                                invoices.add(i);
                            }
                        }
                    }
                }
            }
        }
        return invoices;
    }

}

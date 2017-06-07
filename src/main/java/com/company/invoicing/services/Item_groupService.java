package com.company.invoicing.services;

import com.company.invoicing.models.Item_group;
import com.company.invoicing.repositoriums.Item_groupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Item_groupService {

    @Autowired
    private Item_groupRepository repository;

    public List<Item_group> findAll(){
        return repository.findAll();
    }

    public Item_group findOne(long id){
        return repository.findOne(id);
    }

    public void create(Item_group item_group){
        repository.save(item_group);
    }

    public void update(Item_group item_group){
        repository.save(item_group);
    }

    public void remove(Long id){
        repository.delete(id);
    }

    public List<Item_group> search(Item_group item_group){
        List<Item_group> item_groups=new ArrayList<>();
        for(Item_group ig : repository.findAll()){
            if(item_group.getName()==null || item_group.getName().equals("") || ig.getName().toLowerCase().contains(item_group.getName().toLowerCase())){
                if(item_group.getVat_type()==null || item_group.getVat_type().getVat_type_id()==ig.getVat_type().getVat_type_id()) {
                    item_groups.add(ig);
                }
            }
        }
        return item_groups;
    }

}

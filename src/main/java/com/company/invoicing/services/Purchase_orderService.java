package com.company.invoicing.services;

import com.company.invoicing.models.Purchase_order;
import com.company.invoicing.repositoriums.Purchase_orderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Purchase_orderService {

    @Autowired
    private Purchase_orderRepository repository;

    public List<Purchase_order> findAll(){
        return repository.findAll();
    }

    public Purchase_order findOne(long id){
        return repository.findOne(id);
    }

    public void create(Purchase_order purchase_order){
        repository.save(purchase_order);
    }

    public void update(Purchase_order purchase_order){
        repository.save(purchase_order);
    }

    public void remove(Long id){
        repository.delete(id);
    }

    public List<Purchase_order> search(Purchase_order purchase_order){
        List<Purchase_order> purchase_orders=new ArrayList<>();
        for(Purchase_order po : repository.findAll()){
            if(purchase_order.getPurchase_order_number()==0 || purchase_order.getPurchase_order_number()==po.getPurchase_order_number()){
                if(purchase_order.getDate()==null || purchase_order.getDate().equals("") || purchase_order.getDate().getTime()>=po.getDate().getTime()){
                    if(purchase_order.getCompany()==null || purchase_order.getCompany().getCompany_id()==po.getCompany().getCompany_id()){
                        if(purchase_order.getBusiness_partner()==null || purchase_order.getBusiness_partner().getBusiness_partner_id()==po.getBusiness_partner().getBusiness_partner_id()){
                            if(purchase_order.getFiscal_year()==null || purchase_order.getFiscal_year().getFiscal_year_id()==po.getFiscal_year().getFiscal_year_id()){
                                purchase_orders.add(po);
                            }
                        }
                    }
                }
            }
        }
        return purchase_orders;
    }

}
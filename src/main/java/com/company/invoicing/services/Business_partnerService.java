package com.company.invoicing.services;

import com.company.invoicing.models.Business_partner;
import com.company.invoicing.repositoriums.Business_partnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Business_partnerService {

    @Autowired
    private Business_partnerRepository repository;

    public List<Business_partner> findAll(){
        return repository.findAll();
    }

    public Business_partner findOne(long id){
        return repository.findOne(id);
    }

    public void create(Business_partner business_partner){
        repository.save(business_partner);
    }

    public void update(Business_partner business_partner){
        repository.save(business_partner);
    }

    public void remove(Long id){
        repository.delete(id);
    }

    public List<Business_partner> search(Business_partner business_partner){
        List<Business_partner> business_partners=new ArrayList<>();
        for(Business_partner bp : repository.findAll()){
            if(business_partner.getName()==null || business_partner.getName().equals("") || bp.getName().toLowerCase().contains(business_partner.getName().toLowerCase())){
                if(business_partner.getTin()==0 || business_partner.getTin()==bp.getTin()){
                    if(business_partner.getCity()==null || business_partner.getCity().equals("") || bp.getCity().toLowerCase().contains(business_partner.getCity().toLowerCase())){
                        if(business_partner.getAdress()==null || business_partner.getAdress().equals("") || bp.getAdress().toLowerCase().contains(business_partner.getAdress().toLowerCase())){
                            if(business_partner.getTelephone()==null || business_partner.getTelephone().equals("") || bp.getTelephone().toLowerCase().contains(business_partner.getTelephone().toLowerCase())){
                                if(business_partner.getPersonal_number()==0 || business_partner.getPersonal_number()==bp.getPersonal_number()){
                                    if(business_partner.getCurrent_account()==null || business_partner.getCurrent_account().equals("") || bp.getCurrent_account().toLowerCase().contains(business_partner.getCurrent_account().toLowerCase())){
                                        if(business_partner.getCompany()==null || business_partner.getCompany().getCompany_id()==bp.getCompany().getCompany_id()) {
                                            business_partners.add(bp);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return business_partners;
    }

}

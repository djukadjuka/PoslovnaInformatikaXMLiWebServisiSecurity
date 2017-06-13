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
        if(business_partner.getCompany().getCompany_id()!=business_partner.getCompany_partner().getCompany_id()) {
            boolean moze = true;
            for (Business_partner bp : repository.findAll()) {
                if (bp.getCompany().getCompany_id() == business_partner.getCompany().getCompany_id()) {
                    if (bp.getCompany_partner().getCompany_id() == business_partner.getCompany_partner().getCompany_id()) {
                        moze = false;
                        break;
                    }
                }
            }
            if (moze)
                repository.save(business_partner);
        }
    }

    public void update(Business_partner business_partner){
        if(business_partner.getCompany_partner().getCompany_id()!=business_partner.getCompany().getCompany_id()) {
            Business_partner bp = repository.findOne(business_partner.getBusiness_partner_id());
            List<Business_partner> bps = repository.findAll();
            bps.remove(bp);
            boolean moze = true;
            for (Business_partner bpa : bps) {
                if (bpa.getCompany().getCompany_id() == business_partner.getCompany().getCompany_id()) {
                    if (bpa.getCompany_partner().getCompany_id() == business_partner.getCompany_partner().getCompany_id()) {
                        moze = false;
                        break;
                    }
                }
            }
            if (moze)
                repository.save(business_partner);
        }
    }

    public void remove(Long id){
        repository.delete(id);
    }

    public List<Business_partner> search(Business_partner business_partner){
        List<Business_partner> business_partners=new ArrayList<>();
        for(Business_partner bp : repository.findAll()){
            if(business_partner.getCompany()==null || business_partner.getCompany().getCompany_id()==bp.getCompany().getCompany_id()) {
                if(business_partner.getCompany_partner()==null || business_partner.getCompany_partner().getCompany_id()==bp.getCompany_partner().getCompany_id())
                    business_partners.add(bp);
            }
        }
        return business_partners;
    }

}

package com.company.invoicing.services;

import com.company.invoicing.models.Business_partner;
import com.company.invoicing.models.Fiscal_year;
import com.company.invoicing.repositoriums.Fiscal_yearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Fiscal_yearService {

    @Autowired
    private Fiscal_yearRepository repository;

    public List<Fiscal_year> findAll(){
        return repository.findAll();
    }

    public Fiscal_year findOne(long id){
        return repository.findOne(id);
    }

    public void create(Fiscal_year fiscal_year){
        fiscal_year.setNumber_of_fy(repository.findAll().size()+1);
        repository.save(fiscal_year);
    }

    public void update(Fiscal_year fiscal_year){
        repository.save(fiscal_year);
    }

    public void remove(Long id){
        repository.delete(id);
    }

    public List<Fiscal_year> search(Fiscal_year fiscal_year){
        List<Fiscal_year> fiscal_years=new ArrayList<>();
        for(Fiscal_year fy : repository.findAll()){
            if(fiscal_year.getNumber_of_fy()==0 || fiscal_year.getNumber_of_fy()==fy.getNumber_of_fy()){
                if(fiscal_year.getActive()==fy.getActive()){
                    fiscal_years.add(fy);
                }
            }
        }
        return fiscal_years;
    }


    public List<Fiscal_year> findAllForUser(long company_id) {
        List<Fiscal_year> fiscal_years=new ArrayList<>();
        for(Fiscal_year fy: findAll()){
            if(fy.getCompany().getCompany_id()==company_id){
                fiscal_years.add(fy);
            }
        }
        return fiscal_years;
    }
}

package com.company.invoicing.services;

import com.company.invoicing.models.Company;
import com.company.invoicing.repositoriums.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;

    public List<Company> findAll(){
        return repository.findAll();
    }

    public Company findOne(long id){
        return repository.findOne(id);
    }

    public void create(Company company){
        repository.save(company);
    }

    public void update(Company company){
        repository.save(company);
    }

    public void remove(Long id){
        repository.delete(id);
    }

    public List<Company> search(Company company){
        List<Company> companies=new ArrayList<>();
        for(Company c : repository.findAll()){
            if(company.getName()==null || company.getName().equals("") || c.getName().toLowerCase().contains(company.getName().toLowerCase())){
                if(company.getTin()==0 || company.getTin()==c.getTin()){
                    if(company.getCity()==null || company.getCity().equals("") || c.getCity().toLowerCase().contains(company.getCity().toLowerCase())){
                        if(company.getAdress()==null || company.getAdress().equals("") || c.getAdress().toLowerCase().contains(company.getAdress().toLowerCase())){
                            if(company.getTelephone()==null || company.getTelephone().equals("") || c.getTelephone().toLowerCase().contains(company.getTelephone().toLowerCase())){
                                if(company.getCompany_number()==0 || company.getCompany_number()==c.getCompany_number()){
                                    if(company.getCurrent_account()==null || company.getCurrent_account().equals("") || c.getCurrent_account().toLowerCase().contains(company.getCurrent_account().toLowerCase())){
                                        companies.add(c);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return companies;
    }

}

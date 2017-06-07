package com.company.invoicing.services;

import com.company.invoicing.models.Invoice;
import com.company.invoicing.models.Invoice_item;
import com.company.invoicing.repositoriums.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    public List<Invoice> findAll(){
        return repository.findAll();
    }

    public Invoice findOne(long id){
        return repository.findOne(id);
    }

    public void create(Invoice invoice){
        repository.save(invoice);
    }

    public void update(Invoice invoice){
        repository.save(invoice);
    }

    public void remove(Long id){
        repository.delete(id);
    }

    public List<Invoice> search(Invoice invoice){
        List<Invoice> purchase_orders=new ArrayList<>();
        for(Invoice i : repository.findAll()){
            if(invoice.getInvoice_number()==0 || invoice.getInvoice_number()==i.getInvoice_number()){
                if(invoice.getDate()==null || invoice.getDate().equals("") || invoice.getDate().getTime()>=i.getDate().getTime()){
                    if(invoice.getDate_of_currency()==null || invoice.getDate_of_currency().equals("") || invoice.getDate_of_currency().getTime()>=i.getDate_of_currency().getTime()) {
                        if(invoice.getTotal_tax_basis()==0 || invoice.getTotal_tax_basis()==i.getTotal_tax_basis()) {
                            if(invoice.getTotal_vat()==0 || invoice.getTotal_vat()==i.getTotal_vat()) {
                                if(invoice.getTotal_price()==0 || invoice.getTotal_price()==i.getTotal_price()) {
                                    if (invoice.getCompany() == null || invoice.getCompany().getCompany_id() == i.getCompany().getCompany_id()) {
                                        if (invoice.getBusiness_partner() == null || invoice.getBusiness_partner().getBusiness_partner_id() == i.getBusiness_partner().getBusiness_partner_id()) {
                                            if (invoice.getFiscal_year() == null || invoice.getFiscal_year().getFiscal_year_id() == i.getFiscal_year().getFiscal_year_id()) {
                                                purchase_orders.add(i);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return purchase_orders;
    }

}

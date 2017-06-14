package com.company.invoicing.services;

import com.company.invoicing.models.Invoice;
import com.company.invoicing.models.Invoice_item;
import com.company.invoicing.models.Purchase_order;
import com.company.invoicing.models.Purchase_order_item;
import com.company.invoicing.repositoriums.InvoiceRepository;
import com.company.invoicing.repositoriums.Invoice_itemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    @Autowired
    private Purchase_orderService purchase_order_service;

    @Autowired
    private Purchase_order_itemService purchase_order_item_service;

    @Autowired
    private Invoice_itemService invoice_item_service;

    public List<Invoice> findAll(){
        return repository.findAll();
    }

    public Invoice findOne(long id){
        return repository.findOne(id);
    }

    public void create(Invoice invoice){
        if(invoice.getFiscal_year().getActive() && !invoice.getBusiness_partner().getType_of_bp().equals("supplier")){
            if(invoice.getDate().getTime()>new Date().getTime())
                invoice.setDate(new Date());
            if(invoice.getDate_of_currency().getTime()<invoice.getDate().getTime())
                invoice.setDate(new Date());
            invoice.setInvoice_number(invoice.getFiscal_year().getInvoices().size() + 1);
            invoice.setBilling_account(invoice.getBusiness_partner().getCompany().getCurrent_account());
            invoice.setReference_number("97");
            repository.save(invoice);
        }
    }

    public void update(Invoice invoice){
        if(invoice.getFiscal_year().getActive() && !invoice.getBusiness_partner().getType_of_bp().equals("supplier")){
            if(invoice.getDate().getTime()>new Date().getTime())
                invoice.setDate(new Date());
            if(invoice.getDate_of_currency().getTime()<invoice.getDate().getTime())
                invoice.setDate(new Date());
            invoice.setBilling_account(invoice.getBusiness_partner().getCompany().getCurrent_account());
            repository.save(invoice);
        }

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

    public void generateInvoice(Long id){
        System.out.println("dobavljen id je: "+id);

        Purchase_order purchase_order=purchase_order_service.findOne(id);

        Invoice invoice=new Invoice();
        invoice.setCompany(purchase_order.getCompany());
        invoice.setBusiness_partner(purchase_order.getBusiness_partner());
        invoice.setFiscal_year(purchase_order.getFiscal_year());
        invoice.setBilling_account(purchase_order.getCompany().getCurrent_account());
        invoice.setInvoice_number(invoice.getFiscal_year().getInvoices().size() + 1);
        invoice.setReference_number("97");
        invoice.setDate(new Date());
        invoice.setDate_of_currency(new Date());
        System.out.println("prosao datume");
        List<Purchase_order> lista=invoice.getPurchase_orders();
        lista.add(purchase_order);
        System.out.println("prosao");
        invoice.setPurchase_orders(lista);
        System.out.println("prosao i ");
        //invoice=repository.save(invoice);
        repository.save(invoice);
        System.out.println("prosao i ostalo");

        invoice_item_service.generateInvoiceItems(invoice,purchase_order.getPurchase_order_items());

    }

    public List<Invoice_item> allIIs(Long id) {
        Invoice invoice=repository.findOne(id);
        return invoice.getInvoice_items();
    }

    public List<Invoice> findAllForUser(long company_id) {
        List<Invoice> invoices=new ArrayList<>();
        for(Invoice i: findAll()){
            if(i.getCompany().getCompany_id()==company_id){
                invoices.add(i);
            }
        }
        return invoices;
    }
}

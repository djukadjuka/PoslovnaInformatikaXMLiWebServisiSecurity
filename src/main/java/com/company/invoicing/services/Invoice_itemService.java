package com.company.invoicing.services;

import com.company.invoicing.models.Invoice_item;
import com.company.invoicing.repositoriums.InvoiceRepository;
import com.company.invoicing.repositoriums.Invoice_itemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Invoice_itemService {

    @Autowired
    private Invoice_itemRepository repository;

    public List<Invoice_item> findAll(){
        return repository.findAll();
    }

    public Invoice_item findOne(long id){
        return repository.findOne(id);
    }

    public void create(Invoice_item invoice_item){
        repository.save(invoice_item);
    }

    public void update(Invoice_item invoice_item){
        repository.save(invoice_item);
    }

    public void remove(Long id){
        repository.delete(id);
    }

    public List<Invoice_item> search(Invoice_item invoice_item){
        List<Invoice_item> invoice_items=new ArrayList<>();
        for(Invoice_item ii : repository.findAll()){
            if(invoice_item.getTotal_amount()==0 || invoice_item.getTotal_amount()==ii.getTotal_amount()){
                if(invoice_item.getPrice()==0 || invoice_item.getPrice()==ii.getPrice()) {
                    if(invoice_item.getDiscount()==0 || invoice_item.getDiscount()==ii.getDiscount()) {
                        if(invoice_item.getVat_basis()==0 || invoice_item.getVat_basis()==ii.getVat_basis()) {
                            if(invoice_item.getVat_rate()==0 || invoice_item.getVat_rate()==ii.getVat_rate()) {
                                if(invoice_item.getVat_amount()==0 || invoice_item.getVat_amount()==ii.getVat_amount()) {
                                    if(invoice_item.getTotal_price()==0 || invoice_item.getTotal_price()==ii.getTotal_price()) {
                                        if (invoice_item.getInvoice() == null || invoice_item.getInvoice().getInvoice_id() == ii.getInvoice().getInvoice_id()) {
                                            if (invoice_item.getItem() == null || invoice_item.getItem().getItem_id() == ii.getItem().getItem_id()) {
                                                invoice_items.add(ii);
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

        return invoice_items;
    }

}

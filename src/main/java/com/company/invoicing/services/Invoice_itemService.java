package com.company.invoicing.services;

import com.company.invoicing.models.*;
import com.company.invoicing.repositoriums.InvoiceRepository;
import com.company.invoicing.repositoriums.Invoice_itemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Invoice_itemService {

    @Autowired
    private Invoice_itemRepository repository;

    @Autowired
    private InvoiceService invoiceService;

    public List<Invoice_item> findAll(){
        return repository.findAll();
    }

    public Invoice_item findOne(long id){
        return repository.findOne(id);
    }

    public void create(Invoice_item invoice_item){

        //invoice_item=repository.save(invoice_item);
        //invoice_item=generatePrice(invoice_item);
        updateInvoice(invoice_item,"create");
        repository.save(invoice_item);
    }

    public void update(Invoice_item invoice_item){
        //invoice_item=generatePrice(invoice_item);
        updateInvoice(invoice_item,"update");
        repository.save(invoice_item);
    }

    public void remove(Long id){
        Invoice_item invoice_item=findOne(id);
        updateInvoice(invoice_item,"remove");
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

    public void generateInvoiceItems(Invoice invoice, List<Purchase_order_item> purchase_order_items) {
        System.out.println("usao");
        for(Purchase_order_item poi : purchase_order_items){
            Invoice_item invoice_item=new Invoice_item();
            invoice_item.setInvoice(invoice);
            invoice_item.setItem(poi.getItem());
            invoice_item.setTotal_amount(poi.getTotal_amount());
            invoice_item.setDiscount(0);

            Date invoiceDate=invoice.getDate();
            double price=0;
            Price_list_item tempPLI=null;
            for(Price_list_item pli: poi.getItem().getPrice_list_items()){
                if(invoiceDate.getTime()-pli.getPrice_list().getValid_from().getTime()>0){
                    if(tempPLI==null){
                        tempPLI=pli;
                    }else if(invoiceDate.getTime()-pli.getPrice_list().getValid_from().getTime()<invoiceDate.getTime()-tempPLI.getPrice_list().getValid_from().getTime()){
                        tempPLI=pli;
                    }
                }
            }

            if(tempPLI!=null){
                price=tempPLI.getPrice();
            }

            double vat_rate=0;

            Vat_rate tempVR=null;
            for(Vat_rate vr:poi.getItem().getItem_group().getVat_type().getVat_rates()){
                if(invoiceDate.getTime()-vr.getDate().getTime()>0){
                    if(tempVR==null){
                        tempVR=vr;
                    }else if(invoiceDate.getTime()-vr.getDate().getTime()<invoiceDate.getTime()-tempVR.getDate().getTime()){
                        tempVR=vr;
                    }
                }
            }

            if(tempVR!=null){
                vat_rate=tempVR.getPercentage_of_vatr();
            }

            invoice_item.setPrice(price);
            invoice_item.setVat_basis(price-invoice_item.getDiscount());
            invoice_item.setVat_rate(vat_rate);
            invoice_item.setVat_amount(invoice_item.getVat_basis()*(invoice_item.getVat_rate()/100));
            invoice_item.setTotal_price((invoice_item.getTotal_amount()*invoice_item.getPrice())+invoice_item.getVat_amount()-invoice_item.getDiscount());

            //postavljanje u invoice objektu ukupne pare
            invoice.setTotal_price(invoice.getTotal_price()+invoice_item.getTotal_price());
            invoice.setTotal_tax_basis(invoice.getTotal_tax_basis()+(invoice_item.getVat_basis()*invoice_item.getTotal_amount()));
            invoice.setTotal_vat(invoice.getTotal_vat()+(invoice_item.getVat_amount()*invoice_item.getTotal_amount()));

            repository.save(invoice_item);
        }
        invoiceService.update(invoice);
    }


    public Invoice_item generatePrice(Invoice_item invoice_item){
        Date invoiceDate=invoice_item.getInvoice().getDate();
        double price=0;
        Price_list_item tempPLI=null;
        for(Price_list_item pli: invoice_item.getItem().getPrice_list_items()){
            if(invoiceDate.getTime()-pli.getPrice_list().getValid_from().getTime()>0){
                if(tempPLI==null){
                    tempPLI=pli;
                }else if(invoiceDate.getTime()-pli.getPrice_list().getValid_from().getTime()<invoiceDate.getTime()-tempPLI.getPrice_list().getValid_from().getTime()){
                    tempPLI=pli;
                }
            }
        }

        if(tempPLI!=null){
            price=tempPLI.getPrice();
        }

        double vat_rate=0;

        Vat_rate tempVR=null;
        for(Vat_rate vr:invoice_item.getItem().getItem_group().getVat_type().getVat_rates()){
            if(invoiceDate.getTime()-vr.getDate().getTime()>0){
                if(tempVR==null){
                    tempVR=vr;
                }else if(invoiceDate.getTime()-vr.getDate().getTime()<invoiceDate.getTime()-tempVR.getDate().getTime()){
                    tempVR=vr;
                }
            }
        }

        if(tempVR!=null){
            vat_rate=tempVR.getPercentage_of_vatr();
        }

        invoice_item.setPrice(price);
        invoice_item.setVat_basis(price-invoice_item.getDiscount());
        invoice_item.setVat_rate(vat_rate);
        invoice_item.setVat_amount(invoice_item.getVat_basis()*(invoice_item.getVat_rate()/100));
        invoice_item.setTotal_price((invoice_item.getTotal_amount()*invoice_item.getPrice())+invoice_item.getVat_amount()-invoice_item.getDiscount());
        return invoice_item;
    }

    public void updateInvoice(Invoice_item invoice_item, String type){
        if(type=="create"){
            invoice_item.getInvoice().setTotal_price(invoice_item.getInvoice().getTotal_price()+invoice_item.getTotal_price());
            invoice_item.getInvoice().setTotal_tax_basis(invoice_item.getInvoice().getTotal_tax_basis()+(invoice_item.getVat_basis()*invoice_item.getTotal_amount()));
            invoice_item.getInvoice().setTotal_vat(invoice_item.getInvoice().getTotal_vat()+(invoice_item.getVat_amount()*invoice_item.getTotal_amount()));
        }else if(type=="remove"){
            invoice_item.getInvoice().setTotal_price(invoice_item.getInvoice().getTotal_price()-invoice_item.getTotal_price());
            invoice_item.getInvoice().setTotal_tax_basis(invoice_item.getInvoice().getTotal_tax_basis()-(invoice_item.getVat_basis()*invoice_item.getTotal_amount()));
            invoice_item.getInvoice().setTotal_vat(invoice_item.getInvoice().getTotal_vat()-(invoice_item.getVat_amount()*invoice_item.getTotal_amount()));
        }else if(type=="update"){
            Invoice_item oldII=repository.findOne(invoice_item.getInvoice_item_id());

            invoice_item.getInvoice().setTotal_price(invoice_item.getInvoice().getTotal_price()-oldII.getTotal_price()+invoice_item.getTotal_price());
            invoice_item.getInvoice().setTotal_tax_basis(invoice_item.getInvoice().getTotal_tax_basis()-(oldII.getVat_basis()*oldII.getTotal_amount())+(invoice_item.getVat_basis()*invoice_item.getTotal_amount()));
            invoice_item.getInvoice().setTotal_vat(invoice_item.getInvoice().getTotal_vat()-(oldII.getVat_amount()*oldII.getTotal_amount())+(invoice_item.getVat_amount()*invoice_item.getTotal_amount()));
        }

        invoiceService.update(invoice_item.getInvoice());
    }

}

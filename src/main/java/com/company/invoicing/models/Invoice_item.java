package com.company.invoicing.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Invoice_item{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long invoice_item_id;

    @ManyToOne
    private Invoice invoice;

    @ManyToOne
    private Item item;

    private int total_amount;

    private double price;

    private double discount;

    private double vat_basis;

    private double vat_rate;

    private double vat_amount;

    private double total_price;

    public Invoice_item(Invoice invoice, Item item, int total_amount, double price, double discount, double vat_basis, double vat_rate, double vat_amount, double total_price){
        super();
        this.invoice = invoice;
        this.item = item;
        this.total_amount = total_amount;
        this.price = price;
        this.discount = discount;
        this.vat_basis = vat_basis;
        this.vat_rate = vat_rate;
        this.vat_amount = vat_amount;
        this.total_price = total_price;

    }

    public Invoice_item(long invoice_item_id, Invoice invoice, Item item, int total_amount, double price, double discount, double vat_basis, double vat_rate, double vat_amount, double total_price){
        super();
        this.invoice_item_id = invoice_item_id;
        this.invoice = invoice;
        this.item = item;
        this.total_amount = total_amount;
        this.price = price;
        this.discount = discount;
        this.vat_basis = vat_basis;
        this.vat_rate = vat_rate;
        this.vat_amount = vat_amount;
        this.total_price = total_price;
    }

    public Invoice_item(){}

    public long getInvoice_item_id() {
        return invoice_item_id;
    }

    public void setInvoice_item_id(long invoice_item_id) {
        this.invoice_item_id = invoice_item_id;
    }

    public Invoice getInvoice(){
        return invoice;
    }

    public void setInvoice(Invoice invoice){
        this.invoice = invoice;
    }

    public Item getItem(){
        return item;
    }

    public void setItem(Item item){
        this.item = item;
    }

    public int getTotal_amount(){
        return total_amount;
    }

    public void setTotal_amount(int total_amount){
        this.total_amount = total_amount;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public double getDiscount(){
        return discount;
    }

    public void setDiscount(double discount){
        this.discount = discount;
    }

    public double getVat_basis(){
        return vat_basis;
    }

    public void setVat_basis(double vat_basis){
        this.vat_basis = vat_basis;
    }

    public double getVat_rate(){
        return vat_rate;
    }

    public void setVat_rate(double vat_rate){
        this.vat_rate = vat_rate;
    }

    public double getVat_amount(){
        return vat_amount;
    }

    public void setVat_amount(double vat_amount){
        this.vat_amount = vat_amount;
    }

    public double getTotal_price(){
        return total_price;
    }

    public void setTotal_price(double total_price){
        this.total_price = total_price;
    }

}

package com.company.invoicing.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Entity
public class  Invoice{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long invoice_id;

    @ManyToOne
    @NotNull
    private Fiscal_year fiscal_year;

    @ManyToOne
    @NotNull
    private Company company;

    @ManyToOne
    @NotNull
    private Business_partner business_partner;

    @NotNull
    private Date date;

    @NotNull
    private int invoice_number;

    @NotNull
    private Date date_of_currency;

    @NotNull
    private double total_tax_basis;

    @NotNull
    private double total_vat;

    @NotNull
    private double total_price;

    @NotNull
    @Column(length = 10,unique = true)
    @Size(min=10, max = 10)
    private String billing_account;

    @NotNull
    @Column(length = 2)
    @Size(min=2, max = 2)
    private String reference_number;

    @JsonIgnore
    @OneToMany(mappedBy="invoice", cascade = CascadeType.ALL)
    private List<Invoice_item> invoice_items;

    //@JsonIgnore
    @ManyToMany
    private List<Purchase_order> purchase_orders;

    public Invoice(Fiscal_year fiscal_year, Company company, Business_partner business_partner, Date date, int invoice_number, Date date_of_currency, double total_tax_basis, double total_vat, double total_price, String billing_account, String reference_number, List<Invoice_item> invoice_items, List<Purchase_order> purchase_orders){
        super();
        this.fiscal_year = fiscal_year;
        this.company = company;
        this.business_partner = business_partner;
        this.date = date;
        this.invoice_number = invoice_number;
        this.date_of_currency = date_of_currency;
        this.total_tax_basis = total_tax_basis;
        this.total_vat = total_vat;
        this.total_price = total_price;
        this.billing_account = billing_account;
        this.reference_number = reference_number;
        this.invoice_items = invoice_items;
        this.purchase_orders = purchase_orders;
    }

    public Invoice(long invoice_id, Fiscal_year fiscal_year, Company company, Business_partner business_partner, Date date, int invoice_number, Date date_of_currency, double total_tax_basis, double total_vat, double total_price, String billing_account, String reference_number, List<Invoice_item> invoice_items, List<Purchase_order> purchase_orders){
        super();
        this.invoice_id = invoice_id;
        this.fiscal_year = fiscal_year;
        this.company = company;
        this.business_partner = business_partner;
        this.date = date;
        this.invoice_number = invoice_number;
        this.date_of_currency = date_of_currency;
        this.total_tax_basis = total_tax_basis;
        this.total_vat = total_vat;
        this.total_price = total_price;
        this.billing_account = billing_account;
        this.reference_number = reference_number;
        this.invoice_items = invoice_items;
        this.purchase_orders = purchase_orders;
    }

    public Invoice(){}

    public long getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(long invoice_id) {
        this.invoice_id = invoice_id;
    }

    public Fiscal_year getFiscal_year(){
        return fiscal_year;
    }

    public void setFiscal_year(Fiscal_year fiscal_year){
        this.fiscal_year = fiscal_year;
    }

    public Company getCompany(){
        return company;
    }

    public void setCompany(Company company){
        this.company = company;
    }

    public Business_partner getBusiness_partner(){
        return business_partner;
    }

    public void setBusiness_partner(Business_partner business_partner){
        this.business_partner = business_partner;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public int getInvoice_number(){
        return invoice_number;
    }

    public void setInvoice_number(int invoice_number){
        this.invoice_number = invoice_number;
    }

    public Date getDate_of_currency(){
        return date_of_currency;
    }

    public void setDate_of_currency(Date date_of_currency){
        this.date_of_currency = date_of_currency;
    }

    public double getTotal_tax_basis(){
        return total_tax_basis;
    }

    public void setTotal_tax_basis(double total_tax_basis){
        this.total_tax_basis = total_tax_basis;
    }

    public double getTotal_vat(){
        return total_vat;
    }

    public void setTotal_vat(double total_vat){
        this.total_vat = total_vat;
    }

    public double getTotal_price(){
        return total_price;
    }

    public void setTotal_price(double total_price){
        this.total_price = total_price;
    }

    public String getBilling_account(){
        return billing_account;
    }

    public void setBilling_account(String billing_account){
        this.billing_account = billing_account;
    }

    public String getReference_number(){
        return reference_number;
    }

    public void setReference_number(String reference_number){
        this.reference_number = reference_number;
    }

    public List<Invoice_item> getInvoice_items(){
        return invoice_items;
    }

    public void setInvoice_items(List<Invoice_item> invoice_items){
        this.invoice_items = invoice_items;
    }

    public List<Purchase_order> getPurchase_orders() {
        if(purchase_orders==null)
            purchase_orders=new ArrayList<>();
        return purchase_orders;
    }

    public void setPurchase_orders(List<Purchase_order> purchase_orders) {
        this.purchase_orders = purchase_orders;
    }
}

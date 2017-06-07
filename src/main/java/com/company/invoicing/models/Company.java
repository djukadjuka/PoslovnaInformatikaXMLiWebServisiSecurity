package com.company.invoicing.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.OneToMany;

@Entity
public class Company{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long company_id;

    private String name;

    private int tin;

    private String city;

    private String adress;

    private String telephone;

    private int company_number;

    private String current_account;

    @JsonIgnore
    @OneToMany(mappedBy="company")
    private List<Invoice> invoices;

    @JsonIgnore
    @OneToMany(mappedBy="company")
    private List<Business_partner> business_partners;

    @JsonIgnore
    @OneToMany(mappedBy="company")
    private List<Fiscal_year> fiscal_years;

    @JsonIgnore
    @OneToMany(mappedBy="company")
    private List<Purchase_order> purchase_orders;

    public Company(String name, int tin, String city, String adress, String telephone, int company_number, String current_account, List<Invoice> invoices, List<Business_partner> business_partners, List<Fiscal_year> fiscal_years, List<Purchase_order> purchase_orders){
        super();
        this.name = name;
        this.tin = tin;
        this.city = city;
        this.adress = adress;
        this.telephone = telephone;
        this.company_number = company_number;
        this.current_account = current_account;
        this.invoices = invoices;
        this.business_partners = business_partners;
        this.fiscal_years = fiscal_years;
        this.purchase_orders = purchase_orders;

    }

    public Company(long company_id, String name, int tin, String city, String adress, String telephone, int company_number, String current_account, List<Invoice> invoices, List<Business_partner> business_partners, List<Fiscal_year> fiscal_years, List<Purchase_order> purchase_orders){
        super();
        this.company_id = company_id;
        this.name = name;
        this.tin = tin;
        this.city = city;
        this.adress = adress;
        this.telephone = telephone;
        this.company_number = company_number;
        this.current_account = current_account;
        this.invoices = invoices;
        this.business_partners = business_partners;
        this.fiscal_years = fiscal_years;
        this.purchase_orders = purchase_orders;
    }

    public Company(){}

    public long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(long company_id) {
        this.company_id = company_id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getTin(){
        return tin;
    }

    public void setTin(int tin){
        this.tin = tin;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getAdress(){
        return adress;
    }

    public void setAdress(String adress){
        this.adress = adress;
    }

    public String getTelephone(){
        return telephone;
    }

    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    public int getCompany_number(){
        return company_number;
    }

    public void setCompany_number(int company_number){
        this.company_number = company_number;
    }

    public String getCurrent_account(){
        return current_account;
    }

    public void setCurrent_account(String current_account){
        this.current_account = current_account;
    }

    public List<Invoice> getInvoices(){
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices){
        this.invoices = invoices;
    }

    public List<Business_partner> getBusiness_partners(){
        return business_partners;
    }

    public void setBusiness_partners(List<Business_partner> business_partners){
        this.business_partners = business_partners;
    }

    public List<Fiscal_year> getFiscal_years(){
        return fiscal_years;
    }

    public void setFiscal_years(List<Fiscal_year> fiscal_years){
        this.fiscal_years = fiscal_years;
    }

    public List<Purchase_order> getPurchase_orders(){
        return purchase_orders;
    }

    public void setPurchase_orders(List<Purchase_order> purchase_orders){
        this.purchase_orders = purchase_orders;
    }

}

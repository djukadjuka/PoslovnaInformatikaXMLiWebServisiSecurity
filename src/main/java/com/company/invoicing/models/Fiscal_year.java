package com.company.invoicing.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class Fiscal_year{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long fiscal_year_id;

    @JsonIgnore
    @OneToMany(mappedBy="fiscal_year")
    private List<Invoice> invoices;

    @ManyToOne
    private Company company;

    private int number_of_fy;

    private boolean active;

    @JsonIgnore
    @OneToMany(mappedBy="fiscal_year")
    private List<Purchase_order> purchase_orders;

    public Fiscal_year(List<Invoice> invoices, Company company, int number_of_fy, boolean active, List<Purchase_order> purchase_orders){
        super();
        this.invoices = invoices;
        this.company = company;
        this.number_of_fy = number_of_fy;
        this.active = active;
        this.purchase_orders = purchase_orders;

    }

    public Fiscal_year(long fiscal_year_id, List<Invoice> invoices, Company company, int number_of_fy, boolean active, List<Purchase_order> purchase_orders){
        super();
        this.fiscal_year_id = fiscal_year_id;
        this.invoices = invoices;
        this.company = company;
        this.number_of_fy = number_of_fy;
        this.active = active;
        this.purchase_orders = purchase_orders;
    }

    public Fiscal_year(){}

    public long getFiscal_year_id() {
        return fiscal_year_id;
    }

    public void setFiscal_year_id(long fiscal_year_id) {
        this.fiscal_year_id = fiscal_year_id;
    }

    public List<Invoice> getInvoices(){
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices){
        this.invoices = invoices;
    }

    public Company getCompany(){
        return company;
    }

    public void setCompany(Company company){
        this.company = company;
    }

    public int getNumber_of_fy(){
        return number_of_fy;
    }

    public void setNumber_of_fy(int number_of_fy){
        this.number_of_fy = number_of_fy;
    }

    public boolean getActive(){
        return active;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public List<Purchase_order> getPurchase_orders(){
        return purchase_orders;
    }

    public void setPurchase_orders(List<Purchase_order> purchase_orders){
        this.purchase_orders = purchase_orders;
    }

}

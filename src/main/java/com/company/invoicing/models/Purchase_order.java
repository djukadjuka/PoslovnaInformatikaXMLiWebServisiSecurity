package com.company.invoicing.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Date;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Purchase_order{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @XmlAttribute
    private long purchase_order_id;

    @ManyToOne
    @XmlElement
    @NotNull
    private Fiscal_year fiscal_year;

    @ManyToOne
    @XmlElement
    @NotNull
    private Business_partner business_partner;

    @ManyToOne
    @XmlElement
    @NotNull
    private Company company;

    @XmlElement
    @NotNull
    private Date date;

    @XmlElement
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int purchase_order_number;

    @JsonIgnore
    @OneToMany(mappedBy="purchase_order", cascade = CascadeType.ALL)
    @XmlElement(name="purchase_order_item")
    @XmlElementWrapper(name="purchase_order_items")
    private List<Purchase_order_item> purchase_order_items;

    @JsonIgnore
    @ManyToMany
    private List<Invoice> invoices;

    public Purchase_order(Fiscal_year fiscal_year, Business_partner business_partner, Company company, Date date, int purchase_order_number, List<Purchase_order_item> purchase_order_items, List<Invoice> invoices){
        super();
        this.fiscal_year = fiscal_year;
        this.business_partner = business_partner;
        this.company = company;
        this.date = date;
        this.purchase_order_number = purchase_order_number;
        this.purchase_order_items = purchase_order_items;
        this.invoices = invoices;

    }

    public Purchase_order(long purchase_order_id, Fiscal_year fiscal_year, Business_partner business_partner, Company company, Date date, int purchase_order_number, List<Purchase_order_item> purchase_order_items, List<Invoice> invoices){
        super();
        this.purchase_order_id = purchase_order_id;
        this.fiscal_year = fiscal_year;
        this.business_partner = business_partner;
        this.company = company;
        this.date = date;
        this.purchase_order_number = purchase_order_number;
        this.purchase_order_items = purchase_order_items;
        this.invoices = invoices;
    }

    public Purchase_order(){}

    public long getPurchase_order_id() {
        return purchase_order_id;
    }

    public void setPurchase_order_id(long purchase_order_id) {
        this.purchase_order_id = purchase_order_id;
    }

    public Fiscal_year getFiscal_year(){
        return fiscal_year;
    }

    public void setFiscal_year(Fiscal_year fiscal_year){
        this.fiscal_year = fiscal_year;
    }

    public Business_partner getBusiness_partner(){
        return business_partner;
    }

    public void setBusiness_partner(Business_partner business_partner){
        this.business_partner = business_partner;
    }

    public Company getCompany(){
        return company;
    }

    public void setCompany(Company company){
        this.company = company;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public int getPurchase_order_number(){
        return purchase_order_number;
    }

    public void setPurchase_order_number(int purchase_order_number){
        this.purchase_order_number = purchase_order_number;
    }

    public List<Purchase_order_item> getPurchase_order_items(){
        return purchase_order_items;
    }

    public void setPurchase_order_items(List<Purchase_order_item> purchase_order_items){
        this.purchase_order_items = purchase_order_items;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}

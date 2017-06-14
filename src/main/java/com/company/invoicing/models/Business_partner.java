package com.company.invoicing.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Entity
@XmlAccessorType(XmlAccessType.NONE)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "company", "company_partner" }) })
public class Business_partner{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @XmlAttribute
    private long business_partner_id;

    @ManyToOne
    @NotNull
    @JoinColumn(name="company")
    private Company company;

    @ManyToOne
    @NotNull
    @JoinColumn(name="company_partner")
    private Company company_partner;

    @XmlElement
    @NotNull
    private String type_of_bp;

    @JsonIgnore
    @OneToMany(mappedBy="business_partner")
    private List<Purchase_order> purchase_orders;

    @JsonIgnore
    @OneToMany(mappedBy="business_partner")
    private List<Invoice> invoices;

    public Business_partner(long business_partner_id, Company company, Company company_partner, String type_of_bp, List<Purchase_order> purchase_orders, List<Invoice> invoices) {
        this.business_partner_id = business_partner_id;
        this.company = company;
        this.company_partner = company_partner;
        this.type_of_bp = type_of_bp;
        this.purchase_orders = purchase_orders;
        this.invoices = invoices;
    }

    public Business_partner(Company company, Company company_partner, String type_of_bp, List<Purchase_order> purchase_orders, List<Invoice> invoices) {
        this.company = company;
        this.company_partner = company_partner;
        this.type_of_bp = type_of_bp;
        this.purchase_orders = purchase_orders;
        this.invoices = invoices;
    }

    public Business_partner() {
    }

    public long getBusiness_partner_id() {
        return business_partner_id;
    }

    public void setBusiness_partner_id(long business_partner_id) {
        this.business_partner_id = business_partner_id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getCompany_partner() {
        return company_partner;
    }

    public void setCompany_partner(Company company_partner) {
        this.company_partner = company_partner;
    }

    public String getType_of_bp() {
        return type_of_bp;
    }

    public void setType_of_bp(String type_of_bp) {
        this.type_of_bp = type_of_bp;
    }

    public List<Purchase_order> getPurchase_orders() {
        return purchase_orders;
    }

    public void setPurchase_orders(List<Purchase_order> purchase_orders) {
        this.purchase_orders = purchase_orders;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}

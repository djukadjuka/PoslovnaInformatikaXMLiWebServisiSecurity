package com.company.invoicing.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Entity
@XmlAccessorType(XmlAccessType.NONE)
public class Business_partner{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @XmlAttribute
    private long business_partner_id;

    @JsonIgnore
    @OneToMany(mappedBy="business_partner")
    private List<Invoice> invoices;

    @ManyToOne
    private Company company;

    @XmlElement
    private String name;

    @XmlElement
    private int tin;

    @XmlElement
    private String current_account;

    @XmlElement
    private String city;

    @XmlElement
    private String adress;

    @XmlElement
    private String telephone;

    @XmlElement
    private int personal_number;

    @XmlElement
    private String type_of_bp;

    @JsonIgnore
    @OneToMany(mappedBy="business_partner")
    private List<Purchase_order> purchase_orders;

    public Business_partner(List<Invoice> invoices, Company company, String name, int tin, String current_account, String city, String adress, String telephone, int personal_number, String type_of_bp, List<Purchase_order> purchase_orders){
        super();
        this.invoices = invoices;
        this.company = company;
        this.name = name;
        this.tin = tin;
        this.current_account = current_account;
        this.city = city;
        this.adress = adress;
        this.telephone = telephone;
        this.personal_number = personal_number;
        this.type_of_bp = type_of_bp;
        this.purchase_orders = purchase_orders;

    }

    public Business_partner(long business_partner_id, List<Invoice> invoices, Company company, String name, int tin, String current_account, String city, String adress, String telephone, int personal_number, String type_of_bp, List<Purchase_order> purchase_orders){
        super();
        this.business_partner_id = business_partner_id;
        this.invoices = invoices;
        this.company = company;
        this.name = name;
        this.tin = tin;
        this.current_account = current_account;
        this.city = city;
        this.adress = adress;
        this.telephone = telephone;
        this.personal_number = personal_number;
        this.type_of_bp = type_of_bp;
        this.purchase_orders = purchase_orders;
    }

    public Business_partner(){}

    public long getBusiness_partner_id() {
        return business_partner_id;
    }

    public void setBusiness_partner_id(long business_partner_id) {
        this.business_partner_id = business_partner_id;
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

    public String getCurrent_account(){
        return current_account;
    }

    public void setCurrent_account(String current_account){
        this.current_account = current_account;
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

    public int getPersonal_number(){
        return personal_number;
    }

    public void setPersonal_number(int personal_number){
        this.personal_number = personal_number;
    }

    public String getType_of_bp(){
        return type_of_bp;
    }

    public void setType_of_bp(String type_of_bp){
        this.type_of_bp = type_of_bp;
    }

    public List<Purchase_order> getPurchase_orders(){
        return purchase_orders;
    }

    public void setPurchase_orders(List<Purchase_order> purchase_orders){
        this.purchase_orders = purchase_orders;
    }

}

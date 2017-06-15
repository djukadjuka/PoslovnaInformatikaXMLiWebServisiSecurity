package com.company.invoicing.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Entity
@XmlAccessorType(XmlAccessType.NONE)
public class Item{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @XmlAttribute
    private long item_id;

    @ManyToOne
    @NotNull
    private Item_group item_group;

    @ManyToOne
    @XmlElement
    @NotNull
    private Units_of_measurement units_of_measurement;

    @XmlElement
    @Column(length = 50,unique = true)
    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @XmlElement
    @Column(length = 50)
    @Size( max = 50)
    private String description;

    @XmlElement
    @NotNull
    private boolean is_service;

    @JsonIgnore
    @OneToMany(mappedBy="item")
    private List<Price_list_item> price_list_items;

    @JsonIgnore
    @OneToMany(mappedBy="item")
    private List<Invoice_item> invoice_items;

    @JsonIgnore
    @OneToMany(mappedBy="item")
    private List<Purchase_order_item> purchase_order_items;

    public Item(Item_group item_group, Units_of_measurement units_of_measurement, String name, String description, boolean is_service, List<Price_list_item> price_list_items, List<Invoice_item> invoice_items, List<Purchase_order_item> purchase_order_items){
        super();
        this.item_group = item_group;
        this.units_of_measurement = units_of_measurement;
        this.name = name;
        this.description = description;
        this.is_service = is_service;
        this.price_list_items = price_list_items;
        this.invoice_items = invoice_items;
        this.purchase_order_items = purchase_order_items;

    }

    public Item(long item_id, Item_group item_group, Units_of_measurement units_of_measurement, String name, String description, boolean is_service, List<Price_list_item> price_list_items, List<Invoice_item> invoice_items, List<Purchase_order_item> purchase_order_items){
        super();
        this.item_id = item_id;
        this.item_group = item_group;
        this.units_of_measurement = units_of_measurement;
        this.name = name;
        this.description = description;
        this.is_service = is_service;
        this.price_list_items = price_list_items;
        this.invoice_items = invoice_items;
        this.purchase_order_items = purchase_order_items;
    }

    public Item(){}

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public Item_group getItem_group(){
        return item_group;
    }

    public void setItem_group(Item_group item_group){
        this.item_group = item_group;
    }

    public Units_of_measurement getUnits_of_measurement(){
        return units_of_measurement;
    }

    public void setUnits_of_measurement(Units_of_measurement units_of_measurement){
        this.units_of_measurement = units_of_measurement;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public boolean getIs_service(){
        return is_service;
    }

    public void setIs_service(boolean is_service){
        this.is_service = is_service;
    }

    public List<Price_list_item> getPrice_list_items(){
        return price_list_items;
    }

    public void setPrice_list_items(List<Price_list_item> price_list_items){
        this.price_list_items = price_list_items;
    }

    public List<Invoice_item> getInvoice_items(){
        return invoice_items;
    }

    public void setInvoice_items(List<Invoice_item> invoice_items){
        this.invoice_items = invoice_items;
    }

    public List<Purchase_order_item> getPurchase_order_items(){
        return purchase_order_items;
    }

    public void setPurchase_order_items(List<Purchase_order_item> purchase_order_items){
        this.purchase_order_items = purchase_order_items;
    }

    @Override
    public String toString() {
        return "Item{" +
                "item_id=" + item_id +
                ", item_group=" + item_group +
                ", units_of_measurement=" + units_of_measurement +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", is_service=" + is_service +
                '}';
    }
}

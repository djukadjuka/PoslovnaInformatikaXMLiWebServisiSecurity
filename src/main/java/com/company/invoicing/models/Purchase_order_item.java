package com.company.invoicing.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Entity
@XmlAccessorType(XmlAccessType.NONE)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "purchase_order", "item" }) })
public class Purchase_order_item{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @XmlAttribute
    private long purchase_order_item_id;

    private int price_list_item_id;

    @XmlElement
    @NotNull
    private int total_amount;

    @XmlElement
    @NotNull
    private double total_price;

    @ManyToOne
    @NotNull
    @JoinColumn(name="purchase_order")
    private Purchase_order purchase_order;

    @ManyToOne
    @XmlElement
    @NotNull
    @JoinColumn(name="item")
    private Item item;

    public Purchase_order_item(int price_list_item_id, int total_amount, double total_price, Purchase_order purchase_order, Item item){
        super();
        this.price_list_item_id = price_list_item_id;
        this.total_amount = total_amount;
        this.total_price = total_price;
        this.purchase_order = purchase_order;
        this.item = item;

    }

    public Purchase_order_item(long purchase_order_item_id, int price_list_item_id, int total_amount, double total_price, Purchase_order purchase_order, Item item){
        super();
        this.purchase_order_item_id = purchase_order_item_id;
        this.price_list_item_id = price_list_item_id;
        this.total_amount = total_amount;
        this.total_price = total_price;
        this.purchase_order = purchase_order;
        this.item = item;
    }

    public Purchase_order_item(){}

    public long getPurchase_order_item_id() {
        return purchase_order_item_id;
    }

    public void setPurchase_order_item_id(long purchase_order_item_id) {
        this.purchase_order_item_id = purchase_order_item_id;
    }

    public int getPrice_list_item_id(){
        return price_list_item_id;
    }

    public void setPrice_list_item_id(int price_list_item_id){
        this.price_list_item_id = price_list_item_id;
    }

    public int getTotal_amount(){
        return total_amount;
    }

    public void setTotal_amount(int total_amount){
        this.total_amount = total_amount;
    }

    public double getTotal_price(){
        return total_price;
    }

    public void setTotal_price(double total_price){
        this.total_price = total_price;
    }

    public Purchase_order getPurchase_order(){
        return purchase_order;
    }

    public void setPurchase_order(Purchase_order purchase_order){
        this.purchase_order = purchase_order;
    }

    public Item getItem(){
        return item;
    }

    public void setItem(Item item){
        this.item = item;
    }

}

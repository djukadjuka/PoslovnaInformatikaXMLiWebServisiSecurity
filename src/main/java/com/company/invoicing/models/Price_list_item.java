package com.company.invoicing.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "price_list", "item" }) })
public class Price_list_item{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long price_list_item_id;

    @ManyToOne
    @NotNull
    @JoinColumn(name="price_list")
    private Price_list price_list;

    @ManyToOne
    @NotNull
    @JoinColumn(name="item")
    private Item item;

    @ManyToOne
    @NotNull
    private Currency currency;

    @NotNull
    private double price;

    public Price_list_item(long price_list_item_id, Price_list price_list, Item item, Currency currency, double price) {
        this.price_list_item_id = price_list_item_id;
        this.price_list = price_list;
        this.item = item;
        this.currency = currency;
        this.price = price;
    }

    public Price_list_item(Price_list price_list, Item item, Currency currency, double price) {
        this.price_list = price_list;
        this.item = item;
        this.currency = currency;
        this.price = price;
    }

    public Price_list_item() {
    }

    public long getPrice_list_item_id() {
        return price_list_item_id;
    }

    public void setPrice_list_item_id(long price_list_item_id) {
        this.price_list_item_id = price_list_item_id;
    }

    public Price_list getPrice_list() {
        return price_list;
    }

    public void setPrice_list(Price_list price_list) {
        this.price_list = price_list;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

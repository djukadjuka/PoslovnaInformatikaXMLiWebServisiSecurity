package com.company.invoicing.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class Price_list_item{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long price_list_item_id;

    @ManyToOne
    private Price_list price_list;

    @ManyToOne
    private Item item;

    @ManyToOne
    private Currency currency;

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

    /*@OneToMany(mappedBy="price_list_item")
    private List<Currency> currencies;

    public Price_list_item(Price_list price_list, Item item, double price, List<Currency> currencies){
        super();
        this.price_list = price_list;
        this.item = item;
        this.price = price;
        this.currencies = currencies;

    }

    public Price_list_item(long price_list_item_id, Price_list price_list, Item item, double price, List<Currency> currencies){
        super();
        this.price_list_item_id = price_list_item_id;
        this.price_list = price_list;
        this.item = item;
        this.price = price;
        this.currencies = currencies;
    }

    public Price_list_item(){}

    public long getPrice_list_item_id() {
        return price_list_item_id;
    }

    public void setPrice_list_item_id(long price_list_item_id) {
        this.price_list_item_id = price_list_item_id;
    }

    public Price_list getPrice_list(){
        return price_list;
    }

    public void setPrice_list(Price_list price_list){
        this.price_list = price_list;
    }

    public Item getItem(){
        return item;
    }

    public void setItem(Item item){
        this.item = item;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public List<Currency> getCurrencies(){
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies){
        this.currencies = currencies;
    }*/

}

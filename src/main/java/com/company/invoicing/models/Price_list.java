package com.company.invoicing.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Price_list{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long price_list_id;

    @NotNull
    @Column(unique=true)
    private Date valid_from;

    @JsonIgnore
    @OneToMany(mappedBy="price_list")
    private List<Price_list_item> price_list_items;

    public Price_list(Date valid_from, List<Price_list_item> price_list_items){
        super();
        this.valid_from = valid_from;
        this.price_list_items = price_list_items;

    }

    public Price_list(long price_list_id, Date valid_from, List<Price_list_item> price_list_items){
        super();
        this.price_list_id = price_list_id;
        this.valid_from = valid_from;
        this.price_list_items = price_list_items;
    }

    public Price_list(){}

    public long getPrice_list_id() {
        return price_list_id;
    }

    public void setPrice_list_id(long price_list_id) {
        this.price_list_id = price_list_id;
    }

    public Date getValid_from(){
        return valid_from;
    }

    public void setValid_from(Date valid_from){
        this.valid_from = valid_from;
    }

    public List<Price_list_item> getPrice_list_items(){
        return price_list_items;
    }

    public void setPrice_list_items(List<Price_list_item> price_list_items){
        this.price_list_items = price_list_items;
    }

}

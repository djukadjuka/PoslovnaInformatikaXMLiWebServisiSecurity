package com.company.invoicing.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.OneToMany;

@Entity
public class Vat_type{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long vat_type_id;

    @JsonIgnore
    @OneToMany(mappedBy="vat_type")
    private List<Item_group> item_groups;

    @JsonIgnore
    @OneToMany(mappedBy="vat_type")
    private List<Vat_rate> vat_rates;

    private String name;

    public Vat_type(List<Item_group> item_groups, List<Vat_rate> vat_rates, String name){
        super();
        this.item_groups = item_groups;
        this.vat_rates = vat_rates;
        this.name = name;

    }

    public Vat_type(long vat_type_id, List<Item_group> item_groups, List<Vat_rate> vat_rates, String name){
        super();
        this.vat_type_id = vat_type_id;
        this.item_groups = item_groups;
        this.vat_rates = vat_rates;
        this.name = name;
    }

    public Vat_type(){}

    public long getVat_type_id() {
        return vat_type_id;
    }

    public void setVat_type_id(long vat_type_id) {
        this.vat_type_id = vat_type_id;
    }

    public List<Item_group> getItem_groups(){
        return item_groups;
    }

    public void setItem_groups(List<Item_group> item_groups){
        this.item_groups = item_groups;
    }

    public List<Vat_rate> getVat_rates(){
        return vat_rates;
    }

    public void setVat_rates(List<Vat_rate> vat_rates){
        this.vat_rates = vat_rates;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

}

package com.company.invoicing.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Item_group{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long item_group_id;

    @JsonIgnore
    @OneToMany(mappedBy="item_group")
    private List<Item> items;

    @ManyToOne
    @NotNull
    private Vat_type vat_type;

    @SQLInjectionSafe
    @SafeHtml
    @Column(length = 50, unique = true)
    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    public Item_group(List<Item> items, Vat_type vat_type, String name){
        super();
        this.items = items;
        this.vat_type = vat_type;
        this.name = name;

    }

    public Item_group(long item_group_id, List<Item> items, Vat_type vat_type, String name){
        super();
        this.item_group_id = item_group_id;
        this.items = items;
        this.vat_type = vat_type;
        this.name = name;
    }

    public Item_group(){}

    public long getItem_group_id() {
        return item_group_id;
    }

    public void setItem_group_id(long item_group_id) {
        this.item_group_id = item_group_id;
    }

    public List<Item> getItems(){
        return items;
    }

    public void setItems(List<Item> items){
        this.items = items;
    }

    public Vat_type getVat_type(){
        return vat_type;
    }

    public void setVat_type(Vat_type vat_type){
        this.vat_type = vat_type;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item_group{" +
                "item_group_id=" + item_group_id +
                ", vat_type=" + vat_type +
                ", name='" + name + '\'' +
                '}';
    }
}

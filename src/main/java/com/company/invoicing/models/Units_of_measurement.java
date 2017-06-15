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
public class Units_of_measurement{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @XmlAttribute
    private long units_of_measurement_id;

    @XmlElement
    @Column(length = 3, unique = true)
    @NotNull
    @Size(min = 3, max = 3)
    private String abbreviation;

    @XmlElement
    @Column(length = 50, unique = true)
    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy="units_of_measurement")
    private List<Item> items;

    public Units_of_measurement(String abbreviation, String name, List<Item> items){
        super();
        this.abbreviation = abbreviation;
        this.name = name;
        this.items = items;

    }

    public Units_of_measurement(long units_of_measurement_id, String abbreviation, String name, List<Item> items){
        super();
        this.units_of_measurement_id = units_of_measurement_id;
        this.abbreviation = abbreviation;
        this.name = name;
        this.items = items;
    }

    public Units_of_measurement(){}

    public long getUnits_of_measurement_id() {
        return units_of_measurement_id;
    }

    public void setUnits_of_measurement_id(long units_of_measurement_id) {
        this.units_of_measurement_id = units_of_measurement_id;
    }

    public String getAbbreviation(){
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation){
        this.abbreviation = abbreviation;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<Item> getItems(){
        return items;
    }

    public void setItems(List<Item> items){
        this.items = items;
    }

    @Override
    public String toString() {
        return "Units_of_measurement{" +
                "units_of_measurement_id=" + units_of_measurement_id +
                ", abbreviation='" + abbreviation + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

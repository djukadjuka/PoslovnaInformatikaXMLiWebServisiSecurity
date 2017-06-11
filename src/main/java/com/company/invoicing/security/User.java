package com.company.invoicing.security;

import com.company.invoicing.models.Business_partner;
import com.company.invoicing.models.Company;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by User on 6/10/2017.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long user_id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    @ManyToOne
    private Role role;

    @NotNull
    @ManyToOne
    private Company company;

    @ManyToOne
    private Business_partner business_partner;

    public User(long user_id, String username, String password, Role role, Company company, Business_partner business_partner) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.company = company;
        this.business_partner = business_partner;
    }

    public User(String username, String password, Role role, Company company, Business_partner business_partner) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.company = company;
        this.business_partner = business_partner;
    }

    public User() {
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Business_partner getBusiness_partner() {
        return business_partner;
    }

    public void setBusiness_partner(Business_partner business_partner) {
        this.business_partner = business_partner;
    }
}

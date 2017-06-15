package com.company.invoicing.models;

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
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @NotNull
    @ManyToOne
    private Role role;

    @NotNull
    @ManyToOne
    private Company company;

    public User(long user_id, String username, String password, Role role, Company company) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.company = company;
    }

    public User(String username, String password, Role role, Company company) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.company = company;
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

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", company=" + company +
                '}';
    }
}

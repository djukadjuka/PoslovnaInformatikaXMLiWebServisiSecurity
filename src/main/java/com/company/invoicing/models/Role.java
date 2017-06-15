package com.company.invoicing.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 6/1/2017.
 */
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long role_id;

    @NotNull
    @Column(unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy="role")
    private List<User> users;

    @JsonIgnore
    @OneToMany(mappedBy="role")
    private List<RolePermission> roles_permissions;


    public Role(long role_id, String name,List<User> users,List<RolePermission> roles_permissions) {
        this.role_id = role_id;
        this.name = name;
        this.users=users;
        this.roles_permissions=roles_permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<RolePermission> getRoles_permissions() {
        if(roles_permissions==null){
            roles_permissions=new ArrayList<>();
        }
        return roles_permissions;
    }

    public void setRoles_permissions(List<RolePermission> roles_permissions) {
        this.roles_permissions = roles_permissions;
    }

    public Role(String name, List<User> users, List<RolePermission> roles_permissions) {
        this.name = name;
        this.users=users;
        this.roles_permissions=roles_permissions;

    }

    public Role() {
    }

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", name='" + name + '\'' +
                '}';
    }
}

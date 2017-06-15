package com.company.invoicing.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by User on 6/14/2017.
 */
@Entity
public class Tabela {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long tabela_id;

    @NotNull
    @Column(unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy="tabela")
    private List<RolePermission> roles_permissions;

    public Tabela(long tabela_id, String name, List<RolePermission> roles_permissions) {
        this.tabela_id = tabela_id;
        this.name = name;
        this.roles_permissions = roles_permissions;
    }

    public Tabela(String name, List<RolePermission> roles_permissions) {
        this.name = name;
        this.roles_permissions = roles_permissions;
    }

    public Tabela() {
    }

    public long getTabela_id() {
        return tabela_id;
    }

    public void setTabela_id(long tabela_id) {
        this.tabela_id = tabela_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RolePermission> getRoles_permissions() {
        return roles_permissions;
    }

    public void setRoles_permissions(List<RolePermission> roles_permissions) {
        this.roles_permissions = roles_permissions;
    }

    @Override
    public String toString() {
        return "Tabela{" +
                "tabela_id=" + tabela_id +
                ", name='" + name + '\'' +
                '}';
    }
}

package com.company.invoicing.security;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by User on 6/1/2017.
 */
@Entity
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long permission_id;

    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy="permission")
    private List<RolePermission> roles_permissions;

    public Permission(long permission_id, String name, List<RolePermission> roles_permissions) {
        this.permission_id = permission_id;
        this.name = name;
        this.roles_permissions=roles_permissions;
    }

    public Permission(String name, List<RolePermission> roles_permissions) {
        this.name = name;
        this.roles_permissions=roles_permissions;
    }

    public Permission() {
    }

    public List<RolePermission> getRoles_permissions() {
        return roles_permissions;
    }

    public void setRoles_permissions(List<RolePermission> roles_permissions) {
        this.roles_permissions = roles_permissions;
    }

    public long getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(long permission_id) {
        this.permission_id = permission_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

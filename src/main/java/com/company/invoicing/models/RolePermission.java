package com.company.invoicing.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by User on 6/1/2017.
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "permission", "role" }) })
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long rolepermission_id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="permission")
    private Permission permission;

    @NotNull
    @ManyToOne
    @JoinColumn(name="role")
    private Role role;

    public RolePermission(long rolepermission_id, Permission permission, Role role) {
        this.rolepermission_id = rolepermission_id;
        this.permission = permission;
        this.role = role;
    }

    public RolePermission(Permission permission, Role role) {
        this.permission = permission;
        this.role = role;
    }

    public RolePermission() {
    }

    public long getRolepermission_id() {
        return rolepermission_id;
    }

    public void setRolepermission_id(long rolepermission_id) {
        this.rolepermission_id = rolepermission_id;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

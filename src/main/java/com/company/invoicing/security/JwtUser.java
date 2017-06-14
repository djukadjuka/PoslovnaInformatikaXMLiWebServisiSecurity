package com.company.invoicing.security;

import com.company.invoicing.models.Company;
import com.company.invoicing.models.Role;
import com.company.invoicing.models.RolePermission;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtUser implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final Company company;
    private final Role role;
    //private final List<RolePermission> rolesPermissions;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(
          Long id,
          String username,
          String password,
          Company company,
          Role role,
          /*List<RolePermission> rolePermissions,*/
          Collection<? extends GrantedAuthority> authorities
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.company = company;
        this.role = role;
        //this.rolesPermissions = rolePermissions;
        this.authorities = authorities;
    }

    //@JsonIgnore
    public Long getId() {
        return id;
    }

    public Company getCompany() {
        return company;
    }

    public Role getRole() {
        return role;
    }

    /*public List<RolePermission> getRolesPermissions() {
        return rolesPermissions;
    }*/

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}

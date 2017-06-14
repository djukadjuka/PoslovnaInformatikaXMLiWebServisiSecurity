package com.company.invoicing.security;

import com.company.invoicing.models.Role;
import com.company.invoicing.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getUser_id(),
                user.getUsername(),
                user.getPassword(),
                user.getCompany(),
                user.getRole(),
                /*user.getRole().getRoles_permissions(),*/
                mapToGrantedAuthorities(user.getRole())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Role role) {
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        return grantedAuthorities;
    }
}

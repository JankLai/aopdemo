package com.jqq.aopdemo.security_config;

import com.jqq.aopdemo.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class UserPrincipal implements UserDetails {

    private Admin admin;

    public UserPrincipal(Admin admin){
        super();
        this.admin=admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> roles=new ArrayList<>();

        if(admin.getRoles()==null){
            return Collections.singleton(new SimpleGrantedAuthority("Normal"));
        }

        String[] ss=admin.getRoles().split(",");
        for(String s:ss){
            roles.add(new SimpleGrantedAuthority(s));

        }
        return roles;
        //return Collections.singleton(new SimpleGrantedAuthority("Read"));
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return String.valueOf(admin.getId());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

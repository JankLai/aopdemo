package com.jqq.aopdemo.security_config;

import com.jqq.aopdemo.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserPrincipal implements UserDetails {

    private Admin admin;

    public UserPrincipal(Admin admin){
        super();
        this.admin=admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //Collection<? extends  GrantedAuthority> collection=new Collection<>();
        Collection<? extends GrantedAuthority> roles=new ArrayList<>();
        String[] ss=admin.getRoles().split(",");
        for(String s:ss){
            GrantedAuthority g=new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return s;
                }
            };
            roles.add(g);
        }
        //return Collections.singleton(new SimpleGrantedAuthority("Read"));

        return roles;
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

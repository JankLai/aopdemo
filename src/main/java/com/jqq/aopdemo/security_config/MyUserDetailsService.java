package com.jqq.aopdemo.security_config;

import com.jqq.aopdemo.dao.AdminDao;
import com.jqq.aopdemo.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    AdminDao adminDao;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Admin admin = adminDao.selectAdminById(Integer.valueOf(s));
        if(admin==null)
            throw new UsernameNotFoundException(s+"用户不存在");

        return new UserPrincipal(admin);
    }
}

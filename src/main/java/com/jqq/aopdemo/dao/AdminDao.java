package com.jqq.aopdemo.dao;


import com.jqq.aopdemo.entity.Admin;
import com.jqq.aopdemo.mapper.AdminMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDao {

    @Autowired
    private AdminMapper adminMapper;

    public Integer initAdmins(List adminList){
        return adminMapper.initAdmins(adminList);
    }

    public List<Admin> selectAllAdmin(){
        return adminMapper.selectAllAdmin();
    }

    public Admin selectAdminById(int adminId){
        return adminMapper.selectAdminById(adminId);
    }

    public Admin selectAdmin(int adminId,String password){
        return adminMapper.selectAdmin(adminId,password);
    }

    public Integer deleteAllAdmin(){
        return adminMapper.deleteAllAdmin();
    }
}

package com.jqq.aopdemo.controller;

import com.jqq.aopdemo.dao.AdminDao;
import com.jqq.aopdemo.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author JQQ
 * @date 2019/4/10
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminDao adminDao;

    @PostMapping("/init")
    public Integer initAdmins(){
        List<Admin> admins=adminDao.selectAllAdmin();
        if(admins.size()>=20){//数据库已生成录入员
            return 1;
        }
        else{
            List<Admin> adminList=new ArrayList<>();
            for(int j=0;j<20;j++){
                Admin admin=new Admin();
                Random random = new Random();
                StringBuffer sb = new StringBuffer();
                String WORDS;
                WORDS = "1234567890";
                for(int i=0;i<9;i++){
                    sb.append(WORDS.charAt(random.nextInt(WORDS.length())));
                }
                admin.setPassword(sb.toString());
                adminList.add(admin);
            }
            return adminDao.initAdmins(adminList);
        }


    }

    @GetMapping("/all")
    public List<Admin> selectAllAdmin(){
        return adminDao.selectAllAdmin();
    }

    @GetMapping("/{adminId}")
    public Admin selectAdminById(@PathVariable("adminId") int adminId){
        return adminDao.selectAdminById(adminId);
    }

    @GetMapping("/{adminId}/{password}")
    public Admin selectAdmin(@PathVariable("adminId") int adminId,@PathVariable("password") String password){
        return adminDao.selectAdmin(adminId,password);
    }

    @GetMapping("/logout/{adminId}")
    public Integer logout(@PathVariable("adminId") int adminId){
        return adminId;
    }

    @PutMapping("/delete/all")
    public Integer deleteAllAdmin(){
        return adminDao.deleteAllAdmin();
    }


}

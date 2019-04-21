//package com.jqq.aopdemo.controller;
//
//import com.jqq.aopdemo.dao.AdminDao;
//import com.jqq.aopdemo.entity.Admin;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
///**
// * @author JQQ
// * @date 2019/4/14
// * @question jersey restful 与 spring controller的比较
// */
//@Component
//@Path("/jersey")
//public class JerseyController {
//
//
//
//    @Autowired
//    private AdminDao adminDao;
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON) //返回类型须声明
//    public Admin getSomething(){
//        Admin admin=new Admin();
//        admin.setId(34);
//        admin.setPassword("3rf");
//        return admin;
//    }
//
//    @GET
//    @Path("/all")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Admin> selectAllAdmin(){
//        return adminDao.selectAllAdmin();
//    }
//
//    @GET
//    @Path("/{adminId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Admin selectAdminById(@PathParam("adminId") int adminId){
//        return adminDao.selectAdminById(adminId);
//    }
//
//    @GET
//    @Path("/{adminId}/{password}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Admin selectAdmin(@PathParam("adminId") int adminId,@PathParam("password") String password){
//        return adminDao.selectAdmin(adminId,password);
//    }
//
//    @GET
//    @Path("/logout/{adminId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Integer logout(@PathParam("adminId") int adminId){
//        return adminId;
//    }
//
//    @PUT
//    @Path(("/delete/all"))
//    @Produces(MediaType.APPLICATION_JSON)
//    public Integer deleteAllAdmin(){
//        return adminDao.deleteAllAdmin();
//    }
//
//    @POST
//    @Path("/init")
//    public Integer initAdmins(){
//        List<Admin> admins=adminDao.selectAllAdmin();
//        if(admins.size()>=20){
//            return 1;
//        }
//        else{
//            List<Admin> adminList=new ArrayList<>();
//            for(int j=0;j<20;j++){
//                Admin admin=new Admin();
//                Random random = new Random();
//                StringBuffer sb = new StringBuffer();
//                String WORDS;
//                WORDS = "1234567890";
//                for(int i=0;i<9;i++){
//                    sb.append(WORDS.charAt(random.nextInt(WORDS.length())));
//                }
//                admin.setPassword(sb.toString());
//                adminList.add(admin);
//            }
//            return adminDao.initAdmins(adminList);
//        }
//
//
//    }
//}

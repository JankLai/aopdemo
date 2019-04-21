package com.jqq.aopdemo.controller;

import com.jqq.aopdemo.dao.AlumniDao;
import com.jqq.aopdemo.entity.Alumni;
import com.jqq.aopdemo.service.AlumniService;
import com.jqq.aopdemo.utils.DownloadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alumni")
public class AlumniController {

    @Autowired
    private AlumniDao alumniDao;
    @Autowired
    private AlumniService alumniService;
    @Autowired
    private DownloadUtil downloadUtil;

//    @PostMapping("/init")
//    public Integer initAlumni(){
//        alumniDao.deleteAllAlumni();
//
//        return alumniService.initAlumni();
//    }

    @GetMapping("/all")
    public List<Alumni> selectAllAlumni(){
        List<Alumni> alumniList;
        alumniList=alumniDao.selectAllAlumni();
        if(alumniList.size()<1){
            alumniService.initAlumni();
            return  alumniDao.selectAllAlumni();
        }
        return alumniDao.selectAllAlumni();
    }
    @GetMapping("/{alumniId}")
    public Alumni selectAllAlumni(@PathVariable("alumniId") int alumniId){
        return alumniDao.selectAlumniByID(alumniId);
    }

    @PostMapping("/insert")
    public Integer insertAlumni(@RequestBody Alumni alumni){
        return alumniDao.insertAlumni(alumni);
    }
    @PutMapping("/update/{alumniId}")
    public Integer updateAlumniByID(@PathVariable("alumniId") int alumniID,@RequestBody Alumni alumni){
        return alumniDao.updateAlumniByID(alumniID,alumni);
    }

    @PutMapping("/update/all")
    public Integer updateAllAlumni(){
        alumniDao.deleteAllAlumni();
        return alumniService.initAlumni();
    }

    @DeleteMapping("/delete/{alumniId}")
    public Integer deleteAlumniById(@PathVariable("alumniId") int alumniId){
        return alumniDao.deleteAlumniById(alumniId);
    }

    @DeleteMapping("/delete")
    public Integer deleteAllAlumni(){
        return alumniDao.deleteAllAlumni();
    }

    @PostMapping("/download")
    public void download(){
        downloadUtil.download(alumniDao.selectAllAlumni());
    }
}

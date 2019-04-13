package com.jqq.aopdemo.dao;

import com.jqq.aopdemo.entity.Alumni;
import com.jqq.aopdemo.mapper.AlumniMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlumniDao {

    @Autowired
    AlumniMapper alumniMapper;

    public Integer initAlumni(List<Alumni> alumniList){
        return alumniMapper.initAlumni(alumniList);
    }

    public Integer insertAlumni(Alumni alumni){
        return alumniMapper.insertAlumni(alumni);
    }

    public List<Alumni> selectAllAlumni(){
        return alumniMapper.selectAllAlumni();
    }

    public Alumni selectAlumniByID(int alumniId){
        return  alumniMapper.selectAlumniByID(alumniId);
    }

    public Integer updateAlumniByID(int alumniID,Alumni alumni){
        return alumniMapper.updateAlumniByID(alumniID,alumni);
    }

    public Integer deleteAlumniById(int alumniId){
        return alumniMapper.deleteAlumniById(alumniId);
    }

    public Integer deleteAllAlumni(){
        return alumniMapper.deleteAllAlumni();
    }
}

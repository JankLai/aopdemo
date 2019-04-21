package com.jqq.aopdemo.mapper;

import com.jqq.aopdemo.entity.Alumni;
import org.apache.ibatis.annotations.Param;

import java.util.List;



public interface AlumniMapper {

    Integer initAlumni(@Param("alumniList") List<Alumni> alumniList);

    Integer insertAlumni(@Param("alumni") Alumni alumni);

    List<Alumni> selectAllAlumni();

    Alumni selectAlumniByID(@Param("alumniId") int alumniId);

    Integer updateAlumniByID(@Param("alumniId") int alumniId,@Param("alumni") Alumni alumni);

    Integer deleteAlumniById(@Param("alumniId") int alumniId);

    Integer deleteAllAlumni();
}

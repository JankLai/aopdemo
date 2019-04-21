package com.jqq.aopdemo.aspect;

import com.jqq.aopdemo.entity.Alumni;
import com.jqq.aopdemo.utils.InsertlogUtil;
import com.jqq.aopdemo.utils.UserlogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.assertj.core.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Aspect
@Component
public class ServiceMonitor {

    @Autowired
    private UserlogUtil userlogUtil;
    @Autowired
    private InsertlogUtil insertlogUtil;

    private Logger logger=LoggerFactory.getLogger(ServiceMonitor.class);

    @Around("execution(* com.jqq.aopdemo.controller.AdminController.selectAdmin(..))")
    public Object login(ProceedingJoinPoint joinPoint) throws Throwable
    {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //获取用户权限
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        logger.info("用户权限有："+authentication.toString());
        Object[] ss=joinPoint.getArgs();
        List<String> logInfo=new ArrayList<>();
        logInfo.add(ss[0].toString());
        SimpleDateFormat sdf=new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        String time=sdf.format(date);
        logInfo.add(time);

        userlogUtil.userlog(logInfo);


        Object result=joinPoint.proceed();
        return  result;
    }
    @Around("execution(* com.jqq.aopdemo.controller.AdminController.logout(..))")
    public void logAroundAllMethods(ProceedingJoinPoint joinPoint) throws Throwable
    {
        Object[] ss=joinPoint.getArgs();
        List<String> logInfo=new ArrayList<>();
        logInfo.add(ss[0].toString());
        SimpleDateFormat sdf=new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        String time=sdf.format(date);
        logInfo.add("");
        logInfo.add(time);

        userlogUtil.userlog(logInfo);
        try {
            joinPoint.proceed();

        } finally {
            //Do Something useful, If you have
        }
    }

    @Around("execution(* com.jqq.aopdemo.controller.AlumniController.insertAlumni(..))")
    public void logAroundInsert(ProceedingJoinPoint joinPoint) throws Throwable
    {
        List<String> logInfo=new ArrayList<>();
        Alumni alumni=(Alumni)joinPoint.getArgs()[0];

        logInfo.add(alumni.getName());
        logInfo.add(alumni.getSex());
        logInfo.add(alumni.getBirthday());
        logInfo.add(alumni.getInSchoolDate());
        logInfo.add(alumni.getOutSchoolDate());
        logInfo.add(alumni.getWorkingCity());
        logInfo.add(alumni.getWorkingUnit());
        logInfo.add(alumni.getJob());
        logInfo.add(alumni.getPhoneNum());
        logInfo.add(alumni.getEmail());
        logInfo.add(alumni.getWechat());
        insertlogUtil.insertlog(logInfo);
        try {
            joinPoint.proceed();

        } finally {
            //Do Something useful, If you have
        }
    }

    @Around("execution(* com.jqq.aopdemo.controller.AlumniController.updateAlumniByID(..))")
    public void logAroundUpdate(ProceedingJoinPoint joinPoint) throws Throwable
    {
        List<String> logInfo=new ArrayList<>();
        Alumni alumni=(Alumni)joinPoint.getArgs()[1];
        //获取用户权限
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Object[] authorities= authentication.getAuthorities().toArray();
        for(int i=0;i<authorities.length;i++){
            logger.info("用户权限有："+String.valueOf(authorities[i]));
        }

        logInfo.add(alumni.getName());
        logInfo.add(alumni.getSex());
        logInfo.add(alumni.getBirthday());
        logInfo.add(alumni.getInSchoolDate());
        logInfo.add(alumni.getOutSchoolDate());
        logInfo.add(alumni.getWorkingCity());
        logInfo.add(alumni.getWorkingUnit());
        logInfo.add(alumni.getJob());
        logInfo.add(alumni.getPhoneNum());
        logInfo.add(alumni.getEmail());
        logInfo.add(alumni.getWechat());
        insertlogUtil.insertlog(logInfo);
        try {
            joinPoint.proceed();

        } finally {
            //Do Something useful, If you have
        }
    }

}

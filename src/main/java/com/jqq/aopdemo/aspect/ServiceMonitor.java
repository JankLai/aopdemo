/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jqq.aopdemo.aspect;

import com.jqq.aopdemo.entity.Alumni;
import com.jqq.aopdemo.utils.InsertlogUtil;
import com.jqq.aopdemo.utils.UserlogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
//	@AfterReturning("execution(* com.jqq.aopdemo.*.*.*(..))")
//	public void logServiceAccess(JoinPoint joinPoint) {
//		System.out.println("Completed: " + joinPoint);
//	}

    @Around("execution(* com.jqq.aopdemo.controller.AdminController.selectAdmin(..))")
    public Object login(ProceedingJoinPoint joinPoint) throws Throwable
    {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

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

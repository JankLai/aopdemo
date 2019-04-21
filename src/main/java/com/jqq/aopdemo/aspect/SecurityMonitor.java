//package com.jqq.aopdemo.aspect;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import sun.reflect.annotation.AnnotationParser;
//
//import java.lang.reflect.Method;
//import java.security.Permission;
//
//@Aspect
//@Component
//public class SecurityMonitor {
//
//    @Around("execution(* com.jqq.aopdemo.controller.AdminController.selectAdmin(..))")
//    public Object secure(ProceedingJoinPoint joinPoint){
//
//
//        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//
//    }
//
//}

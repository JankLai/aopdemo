package com.jqq.aopdemo.config;

import com.jqq.aopdemo.controller.JerseyController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * @author JQQ
 * @date 2019/4/14
 * @question ResourceConfig的作用
 */
@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig(){
        register(JerseyController.class); //注册类
    }
}

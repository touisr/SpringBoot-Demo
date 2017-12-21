package com.laika.demoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: 会跳舞的机器人
 * @date: 2017/7/5 11:44
 * @description: 启动主类
 */
@Slf4j
@SpringBootApplication
@ImportResource({"classpath:spring-config.xml"})
@EnableAspectJAutoProxy
public class Application {
    public static void main(String[] args) {
        log.info("start execute Application...\n");
        SpringApplication.run(Application.class, args);
        log.info("end execute Application...\n");
    }
}


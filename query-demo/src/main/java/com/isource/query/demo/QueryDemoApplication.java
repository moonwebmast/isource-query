package com.isource.query.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.isource")
@MapperScan("com.isource.*.*.dao")
public class QueryDemoApplication {

    public static void main(String[] args){
        SpringApplication.run(QueryDemoApplication.class,args);
    }
}

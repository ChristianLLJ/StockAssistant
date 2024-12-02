package com.bupt.stockassistant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan("com.bupt.stockassistant.mapper")
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class StockAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockAssistantApplication.class, args);
    }

}

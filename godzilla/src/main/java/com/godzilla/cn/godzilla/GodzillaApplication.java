package com.godzilla.cn.godzilla;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.godzilla.cn.godzilla.mapper")
public class GodzillaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GodzillaApplication.class, args);
    }
}

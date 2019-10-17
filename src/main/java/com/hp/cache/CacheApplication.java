package com.hp.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan("com.hp.cache.mapper")
public class CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }

}

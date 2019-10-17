package com.hp.cache.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author xiaodong
 * @title
 * @date 2019/10/16 10:38
 * @desc
 */
@Configuration
public class MyConfiguration {

    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator(){
        return new KeyGenerator(){
            @Override
            public Object generate(Object o, Method method, Object... objects) {

                return method.getName() + "["+ Arrays.asList(objects).toString()+"]";
            }
        };
    }
}

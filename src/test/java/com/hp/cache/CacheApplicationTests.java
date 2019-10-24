package com.hp.cache;

import com.hp.cache.bean.Employee;
import com.hp.cache.mapper.EmployeeMapper;
import com.hp.cache.service.WeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate; //操作k v 读书字符串的

    @Autowired
    RedisTemplate redisTemplate; //操作k v都是对象

    @Autowired
    WeatherService weatherService;

    @Test
    public void getWeatherData(){
         weatherService.getWeatherInfo("371000");
    }

    @Test
    public void contextLoads() {
        Employee emp = employeeMapper.getEmpById(1);
        System.out.println(emp);
    }

    /**
     *  Redis常见的五大数据类型
     *  String List Set Hash ZSet(有序集合)
     *
     */
    @Test
    public void testRedis(){
        //stringRedisTemplate.opsForValue().append("msg","hello");
        //String msg = stringRedisTemplate.opsForValue().get("msg");
        //System.out.println(msg);

        stringRedisTemplate.opsForList().leftPush("mylist","1");
    }

    /**
     * 保存对象，使用jdk序列化机制保存数据
     */
    @Test
    public void test2(){
        Employee emp = employeeMapper.getEmpById(1);
        redisTemplate.opsForValue().set("emp1",emp);
    }

    /**
     * 保存对象为json
     */
    @Test
    public void test3(){
        Employee emp = employeeMapper.getEmpById(1);
        redisTemplate.opsForValue().set("emp2",emp);
    }
}

package com.hp.cache.service;

import com.hp.cache.vo.WeatherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaodong
 * @title
 * @date 10/24/2019 09:19
 * @desc
 */
@Service
public class WeatherService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    static final String uri = "https://restapi.amap.com/v3/weather/weatherInfo?key=ff0457697e42d60b33effb7967dd91e7&city=";

    /**
     * 1.先查询redis中是否有该城市的天气数据，如果有返回
     * 2.如果没有，则去查询天气服务API查询，并将返回数据保存到redis中
     * 3.redis中设置每个城市天气数据的过期时间，实现定期刷新天气数据
     * @param cityCode
     * @return
     */
    public String getWeatherInfo(String cityCode){
        String response = "";
        response = stringRedisTemplate.opsForValue().get(cityCode);
        System.out.println(redisTemplate.opsForZSet().range("peer",0,-1));
        if(StringUtils.isEmpty(response)){
            System.out.println("redis中没有"+cityCode+"的数据，我去查天气网...");
            response = restTemplate.getForObject(uri+cityCode,String.class);
            stringRedisTemplate.opsForValue().set(cityCode,response,10, TimeUnit.SECONDS);
            redisTemplate.opsForZSet().add("peer","a",1.0);
            redisTemplate.opsForZSet().add("peer","b",1.0);
            redisTemplate.opsForZSet().add("peer","b",1.0);
        }
        System.out.println(response);
        return response;
    }

    /**
     * redis map基本操作
     * @return
     */
    public String testMap() {
        String result="";
        //1.map
        redisTemplate.opsForHash().put("hmap","name","peter");
        redisTemplate.opsForHash().put("hmap","age","18");
        redisTemplate.opsForHash().put("hmap","gender","boy");
        //result = redisTemplate.opsForHash().values("hmap").toString();
        //result = redisTemplate.opsForHash().keys("hmap").toString();
        //result = redisTemplate.opsForHash().entries("hmap").toString();
        //result = redisTemplate.opsForHash().get("hmap","age").toString();
        //result = redisTemplate.opsForHash().hasKey("hmap","age").toString();
        //result = redisTemplate.opsForHash().size("hmap").toString();
        /*List<Object> list = new ArrayList<Object>();
        list.add("name");
        list.add("age");
        list.add("gender");
        result = redisTemplate.opsForHash().multiGet("hmap",list).toString();*/
        /*Map newMap = new HashMap();
        newMap.put("size","3");
        newMap.put("count","5");
        redisTemplate.opsForHash().putAll("mymap",newMap);
        result = redisTemplate.opsForHash().entries("mymap").toString();*/
        /*redisTemplate.opsForHash().delete("mymap","size");
        result = redisTemplate.opsForHash().entries("mymap").toString();*/

        return result;
    }

    /**
     * redis list基本操作
     * @return
     */
    public String testList() {
        String result="";
        //list
        redisTemplate.opsForList().leftPush("mylist","v1");
        redisTemplate.opsForList().leftPushAll("mylist","v2","v3");
        redisTemplate.opsForList().rightPopAndLeftPush("mylist","mylist");
        result = redisTemplate.opsForList().range("mylist",0,-1).toString();

        redisTemplate.delete("mylist");
        return result;
    }

    /**
     * 集合
     * @return
     */
    public String testSet(){
        String result = "";
        redisTemplate.opsForSet().add("myset","apple");
        redisTemplate.opsForSet().add("myset","apple");
        redisTemplate.opsForSet().add("myset","banana","orange","egg");

        result= redisTemplate.opsForSet().members("myset").toString();
        return result;
    }

    /**
     * 有序集合
     * @return
     */
    public String testZSet(){
        String result = "";
        redisTemplate.opsForZSet().add("myZSet","aa",1.0);
        redisTemplate.opsForZSet().add("myZSet","apple",1.1);
        redisTemplate.opsForZSet().add("myZSet","banana",0.5);

        //result= redisTemplate.opsForZSet().range("myZSet",0,2).toString();
        //result= redisTemplate.opsForZSet().rangeWithScores("myZSet",0,-1).toString();//获取全部集合元素
        result= redisTemplate.opsForZSet().reverseRange("myZSet",0,-1).toString();//获取全部集合元素
        return result;
    }
}

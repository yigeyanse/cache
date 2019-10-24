package com.hp.cache.service;

import com.hp.cache.vo.WeatherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

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
}

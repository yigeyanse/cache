package com.hp.cache.controller;

import com.hp.cache.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaodong
 * @title
 * @date 10/24/2019 09:56
 * @desc
 */
@RestController
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("/getWeather/{cityCode}")
    public String getWeather(@PathVariable("cityCode") String cityCode){
        return weatherService.getWeatherInfo(cityCode);
    }

    @GetMapping("/testMap")
    public String testMap(){
        return weatherService.testMap();
    }

    @GetMapping("/testList")
    public String testList(){
        return weatherService.testList();
    }

    @GetMapping("/testSet")
    public String testSet(){
        return weatherService.testSet();
    }

    @GetMapping("/testZSet")
    public String testZSet(){
        return weatherService.testZSet();
    }
}

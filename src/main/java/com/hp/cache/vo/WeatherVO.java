package com.hp.cache.vo;

import lombok.Data;

import java.util.List;

/**
 * @author xiaodong
 * @title
 * @date 10/24/2019 09:17
 * @desc
 */
@Data
public class WeatherVO {

    private String status;
    private Integer count;
    private String infocode;
    private List<CityInfo> lives;
}

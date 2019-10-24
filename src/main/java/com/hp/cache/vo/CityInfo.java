package com.hp.cache.vo;

import lombok.Data;

/**
 * @author xiaodong
 * @title
 * @date 10/24/2019 10:20
 * @desc
 */
@Data
public class CityInfo {

    private String province;
    private String city;
    private String adcode;
    private String weather;
    private String temperature;
    private String winddirection;
    private String windpower;
    private String humidity;
    private String reporttime;
}

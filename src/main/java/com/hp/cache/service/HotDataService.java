package com.hp.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaodong
 * @title
 * @date 2019/12/19 08:59
 * @desc
 */
@Service
public class HotDataService {

    @Autowired
    RedisTemplate redisTemplate;
    //热数据长度
    private static final int MAX_COUNT = 3;

    /**
     * 保存新数据
     * @param Key
     * @param value
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveData(String Key, String value) {
        redisTemplate.opsForList().leftPush(Key,value);
        redisTemplate.opsForList().trim(Key,0, MAX_COUNT -1);
    }

    /**
     * 查询热数据
     * @param key
     * @return
     */
    public List<String> queryData(String key) {
        List<String> list = redisTemplate.opsForList().range(key,0,-1);
        if (list == null || list.size() == 0) {
            list = new ArrayList<String>();
        }
        return list;
    }
}

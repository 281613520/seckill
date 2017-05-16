package com.xw.seckill.cache;

import com.sun.org.apache.regexp.internal.RE;
import com.xw.seckill.dao.SeckillDao;
import com.xw.seckill.entity.SecKill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Ankh on 2017/5/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {

    @Autowired
    RedisDao redisDao;
    @Autowired
    SeckillDao seckillDao;

    @Test
    public void getSeckill() throws Exception {
        //String key = "seckillId" + 1001;
        SecKill secKill = redisDao.getSeckill(1002);
        System.out.println(secKill);
    }

    @Test
    public void putSeckill() throws Exception {
        SecKill secKill1 = seckillDao.queryById(1002);
        String result = redisDao.putSeckill(secKill1);
        System.out.println(result);
    }

}
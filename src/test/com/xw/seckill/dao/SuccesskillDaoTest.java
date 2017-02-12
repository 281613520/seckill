package com.xw.seckill.dao;

import com.xw.seckill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by AnKh on 2017/2/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccesskillDaoTest {
    @Autowired
    SuccesskillDao successkillDao;
    @Test
    public void insertSuccessKilled() throws Exception {
        long seckillId = 1000;
        long userPhone = 15198763370L;
        int insertCount = successkillDao.insertSuccessKilled(seckillId,userPhone);
        System.out.println(insertCount);
    }

    @Test
    public void queryByIdWithSuccessKill() throws Exception {
        long seckillId = 1000L;
        long userPhone = 15198763370L;
        SuccessKilled successKilled = successkillDao.queryByIdWithSuccessKilled(seckillId,userPhone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckillId());


    }

}
package com.xw.seckill.dao;

import com.xw.seckill.entity.SecKill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by AnKh on 2017/2/5.
 */
public interface SeckillDao {
    /*
    * 减库存
    * return 如果影响行数> 1 表示更新记录的行数*/
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);
    /*
    * 查找   */
    SecKill queryById(long seckillId);
    /*列表
    * 根据偏移量查找秒杀商品列表*/
    List<SecKill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}

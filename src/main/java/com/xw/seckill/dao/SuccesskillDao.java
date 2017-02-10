package com.xw.seckill.dao;

import com.xw.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by AnKh on 2017/2/5.
 */
public interface SuccesskillDao {
    /*插入购买明细，可过滤重复
    * return插入行数*/
    int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);
    /*
    * 根据秒杀商品的ID查询明细的SuccessKilled对象（该对象携带了SecKill对象）*/
    SuccessKilled queryByIdWithSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);

}

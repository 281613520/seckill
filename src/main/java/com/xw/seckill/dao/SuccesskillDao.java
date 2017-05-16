package com.xw.seckill.dao;

import com.xw.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by AnKh on 2017/2/5.
 */
@Repository
public interface SuccesskillDao {
    /**
     * 插入购买明细，可过滤重复
     * @param seckillId
     * @param userPhone
     * @return 插入行数 为什么会返回一个int？ 因为在使用MyBatis做持久层时，insert语句默认是不返回记录的主键值，
     *                                         而是返回插入的记录条数；如果业务层需要得到记录的主键时，
     *                                         可以通过配置的方式来完成这个功能
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);

    /**
     * 根据秒杀商品的ID查询明细的SuccessKilled对象（该对象携带了SecKill对象）
     * @param seckillId
     * @param userPhone
     * @return
     */
    SuccessKilled queryByIdWithSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);

}

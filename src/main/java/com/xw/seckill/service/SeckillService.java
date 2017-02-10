package com.xw.seckill.service;

import com.xw.seckill.dto.Exposer;
import com.xw.seckill.dto.SeckillExecution;
import com.xw.seckill.entity.SecKill;
import com.xw.seckill.exception.RepeatKillException;
import com.xw.seckill.exception.SeckillCloseException;
import com.xw.seckill.exception.SeckillException;

import java.util.List;

/**业务接口:站在“使用者“的角度
 * 三个方面
 * 1.方法定义粒度
 * 2.参数，简练，直接
 * 3.返回类型（return 类型友好/）
 * Created by AnKh on 2017/2/8.
 */
public interface SeckillService {
    /*
    * 查询所有秒杀记录
    * */
    List<SecKill> getSeckillList();
    /*查询单个秒杀记录
    * */
    SecKill getById(long seckillId);
    /*秒杀开始是否输出秒杀接口地址，
    否则输出系统时间和秒杀时间
    * */
    Exposer exportSeckillUrl(long seckillId);
    /*
    * 执行秒杀操作*/
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)throws SeckillException,SeckillCloseException,RepeatKillException;
}

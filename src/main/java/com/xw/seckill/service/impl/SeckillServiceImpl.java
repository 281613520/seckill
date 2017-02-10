package com.xw.seckill.service.impl;

import com.xw.seckill.dao.SeckillDao;
import com.xw.seckill.dao.SuccesskillDao;
import com.xw.seckill.dto.Exposer;
import com.xw.seckill.dto.SeckillExecution;
import com.xw.seckill.entity.SecKill;
import com.xw.seckill.entity.SuccessKilled;
import com.xw.seckill.enums.SeckillStateEnum;
import com.xw.seckill.exception.RepeatKillException;
import com.xw.seckill.exception.SeckillCloseException;
import com.xw.seckill.exception.SeckillException;
import com.xw.seckill.service.SeckillService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import java.util.Date;
import java.util.List;


/**
 * Created by AnKh on 2017/2/8.
 */
@Service
public class SeckillServiceImpl implements SeckillService{

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccesskillDao successkillDao;
    //盐值
    private final String salt = "ssssssss";

    public List<SecKill> getSeckillList() {
        return seckillDao.queryAll(0,4);
    }

    public SecKill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        SecKill secKill = seckillDao.queryById(seckillId);

        if(secKill == null) return new Exposer(false,seckillId);
        Date startTime = secKill.getStartTime();
        Date endTime = secKill.getEndtTime();
        Date nowTime  = new Date();

        if(nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()){
            return new Exposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }
        //转化特定字符串的过程，不可逆~
        String md5 = getMd5(seckillId);
        return new Exposer(true,md5,seckillId);
    }

    private String getMd5(long seckillId){
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, SeckillCloseException, RepeatKillException {
        if(md5 == null || md5.equals(getMd5(seckillId))){
            throw new SeckillException("seckill data rewrite");
        }
        //执行秒杀逻辑
        //减库存，记录秒杀行为
        Date nowTime = new Date();
        try {
            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                //没有更新记录,秒杀结束
                throw new SeckillCloseException("seckill is closed");
            } else {
                //记录购买行为
                int insertCount = successkillDao.insertSuccessKilled(seckillId, userPhone);
                if (insertCount <= 0) {
                    //重复秒杀
                    throw new RepeatKillException("seckill repeat");
                } else {
                    SuccessKilled successKilled = successkillDao.queryByIdWithSuccessKilled(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
                }
            }
        }catch(SeckillCloseException e1){
            throw e1;
        }catch (RepeatKillException e2){
            throw e2;
        } catch (Exception e){
            logger.error(e.getMessage(),e);
            //将编译期异常转化为运行期异常
            throw new SeckillException("seckill inner error"+e.getMessage());
        }

    }
}

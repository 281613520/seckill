package com.xw.seckill.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.xw.seckill.entity.SecKill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Ankh on 2017/5/16.
 */
public class RedisDao {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JedisPool jedisPool;

    private RuntimeSchema<SecKill> runtimeSchema = RuntimeSchema.createFrom(SecKill.class);

    public RedisDao(String ip ,int port) {
        jedisPool = new JedisPool(ip,port);
    }

    //getSecKill
    public SecKill getSeckill(long seckillId){
        try{
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckillId" + seckillId;
                byte[] bytes = jedis.get(key.getBytes());
                if(bytes != null) {
                    SecKill secKill = runtimeSchema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, secKill, runtimeSchema);
                    return secKill;
                }
            }finally {
                jedis.close();
            }
        }catch (Exception e){

        }
        return null;
    }

    //putSecKill
    public String putSeckill(SecKill secKill){
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckillId" + secKill.getSeckillId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(secKill, runtimeSchema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeout = 60*60;
                String result = jedis.setex(key.getBytes(),timeout,bytes);
                return result;
            }finally {
                jedis.close();
            }
        }catch (Exception e){

        }
        return null;
    }

    public static void main(String[] args) {

    }
}

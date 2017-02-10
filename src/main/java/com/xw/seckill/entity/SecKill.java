package com.xw.seckill.entity;

import java.util.Date;

/**
 * Created by AnKh on 2017/2/5.
 */
public class SecKill {
    private long seckillId;
    private String name;
    private int number;
    private Date startTime;
    private Date endtTime;
    private Date createTime;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillid) {
        this.seckillId = seckillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndtTime() {
        return endtTime;
    }

    public void setEndtTime(Date endtTime) {
        this.endtTime = endtTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SecKill{" +
                "seckillid=" + seckillId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", startTime=" + startTime +
                ", endtTime=" + endtTime +
                ", createTime=" + createTime +
                '}';
    }
}

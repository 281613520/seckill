package com.xw.seckill.dto;

/**
 * Created by AnKh on 2017/2/12.
 */
//将所有
public class SeckillResult<T> {
    private boolean success;
    private T data;
    private String message;

    //success is true
    /**
     *
     * @param success
     * @param data
     */
    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }
    //success is false
    /**
     *
     * @param success
     * @param message
     */
    public SeckillResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

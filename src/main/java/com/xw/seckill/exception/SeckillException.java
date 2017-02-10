package com.xw.seckill.exception;

/**通用异常
 * Created by AnKh on 2017/2/8.
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeckillException(String message) {
        super(message);
    }
}

package com.xw.seckill.exception;

/**具体异常
 * Created by AnKh on 2017/2/8.
 */
public class SeckillCloseException extends SeckillException{
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}

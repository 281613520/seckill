package com.xw.seckill.exception;

/**具体异常
 * Created by AnKh on 2017/2/8.
 */
public class RepeatKillException extends SeckillException {
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}

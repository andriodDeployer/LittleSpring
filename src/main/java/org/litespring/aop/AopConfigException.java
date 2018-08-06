package org.litespring.aop;/**
 * Created by DELL on 2018/8/6.
 */

/**
 * user is
 **/


public class AopConfigException extends RuntimeException {
    public AopConfigException(String msg){
        super(msg);
    }

    public AopConfigException(String msg, Throwable cause){
        super(msg,cause);
    }

}

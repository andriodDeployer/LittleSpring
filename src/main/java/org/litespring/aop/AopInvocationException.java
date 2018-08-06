package org.litespring.aop;/**
 * Created by DELL on 2018/8/6.
 */

/**
 * user is lwb
 **/


public class AopInvocationException extends RuntimeException {

    public AopInvocationException(String msg){
        super(msg);
    }

    public AopInvocationException(String msg, Throwable cause){
        super(msg,cause);
    }
}

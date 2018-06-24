package org.litespring.beans;
/**
 * Created by DELL on 2018/6/20.
 */

/**
 * user is lwb
 **/


public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg,Throwable cause) {
        super(msg, cause);

    }
}

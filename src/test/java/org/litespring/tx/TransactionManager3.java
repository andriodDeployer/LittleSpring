package org.litespring.tx;
/**
 * Created by DELL on 2018/7/31.
 */

import org.litespring.util.MessageTracker;

/**
 * user is lwb
 **/


public class TransactionManager3 {

    public void start(){
        String msg = "transaction start3";
        System.out.println(msg);
        MessageTracker.addMsg(msg);
    }

    public void commit(){
        String msg = "transaction commit3";
        System.out.println(msg);
        MessageTracker.addMsg(msg);
    }

    public void rollback(){
        String msg = "transaction rallback4";
        System.out.println(msg);
        MessageTracker.addMsg(msg);
    }
}

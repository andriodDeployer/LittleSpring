package org.litespring.tx;
/**
 * Created by DELL on 2018/7/31.
 */

import org.litespring.util.MessageTracker;

/**
 * user is lwb
 **/


public class TransactionManager {

    public void start(){
        String msg = "transaction start";
        System.out.println("transaction start");
        MessageTracker.addMsg(msg);
    }

    public void commit(){
        String msg = "transaction commit";
        System.out.println(msg);
        MessageTracker.addMsg(msg);
    }

    public void rollback(){
        String msg = "transaction rallback";
        System.out.println(msg);
        MessageTracker.addMsg(msg);
    }
}

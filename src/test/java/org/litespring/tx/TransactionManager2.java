package org.litespring.tx;
/**
 * Created by DELL on 2018/7/31.
 */

import org.litespring.util.MessageTracker;

/**
 * user is lwb
 **/


public class TransactionManager2 {

    public void start(){
        String msg = "transaction start2";
        System.out.println(msg);
        MessageTracker.addMsg(msg);
    }

    public void commit(){
        String msg = "transaction commit2";
        System.out.println(msg);
        MessageTracker.addMsg(msg);
    }

    public void rollback(){
        String msg = "transaction rallback2";
        System.out.println(msg);
        MessageTracker.addMsg(msg);
    }
}

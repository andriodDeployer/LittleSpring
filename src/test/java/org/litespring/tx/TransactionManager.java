package org.litespring.tx;
/**
 * Created by DELL on 2018/7/31.
 */

/**
 * user is lwb
 **/


public class TransactionManager {

    public void start(){
        System.out.println("transaction start");
    }

    public void commit(){
        System.out.println("transaction commit");
    }

    public void rollback(){
        System.out.println("transaction rallback");
    }
}

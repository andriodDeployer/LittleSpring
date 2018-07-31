package org.litespring.service.v5;/**
 * Created by DELL on 2018/7/31.
 */

import org.litespring.beans.factory.annotation.Autowired;
import org.litespring.dao.v5.AccountDao;
import org.litespring.dao.v5.ItemDao;
import org.litespring.stereotype.Component;
import org.litespring.util.MessageTracker;

/**
 * user is lwb
 **/

@Component(value = "petStore")
public class PetStoreService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private ItemDao itemDao;

    public PetStoreService(){

    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }


    public void placeOrder(){
        String msg = "place order";
        System.out.println(msg);
        MessageTracker.addMsg(msg);
    }
}

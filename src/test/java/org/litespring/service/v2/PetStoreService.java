package org.litespring.service.v2;
/**
 * Created by DELL on 2018/6/19.
 */

import org.litespring.dao.v2.AccountDao;
import org.litespring.dao.v2.ItemDao;

/**
 * user is
 **/


public class PetStoreService {
    private AccountDao accountDao;
    private ItemDao itemDao;
    private String owner;

    public AccountDao getAccountDao(){
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao){
        this.accountDao = accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}

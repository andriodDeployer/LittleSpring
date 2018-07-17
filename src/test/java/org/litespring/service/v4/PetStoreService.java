package org.litespring.service.v4;/**
 * Created by DELL on 2018/7/16.
 */

import org.litespring.beans.factory.annotation.Autowired;
import org.litespring.dao.v2.AccountDao;
import org.litespring.dao.v4.ItemDao;
import org.litespring.stereotype.Component;

/**
 * user is lwb
 **/

@Component
public class PetStoreService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private AccountDao accountDao;

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}

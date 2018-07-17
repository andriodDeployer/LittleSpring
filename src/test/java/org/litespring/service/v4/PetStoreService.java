package org.litespring.service.v4;/**
 * Created by DELL on 2018/7/16.
 */

import org.litespring.beans.factory.annotation.Autowired;
import org.litespring.dao.v2.AccountDao;
import org.litespring.dao.v4.ItemDao;

/**
 * user is lwb
 **/


public class PetStoreService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private AccountDao accountDao;

}

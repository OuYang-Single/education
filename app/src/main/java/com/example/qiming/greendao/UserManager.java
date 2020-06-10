package com.example.qiming.greendao;

import com.example.qiming.mvp.model.entity.User;
import org.greenrobot.greendao.AbstractDao;

public class UserManager extends BaseBeanManager<User, Long> {

    public UserManager(AbstractDao dao) {
        super(dao);
    }
}

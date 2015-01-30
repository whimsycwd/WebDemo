package demo.whimsy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.whimsy.dao.UserDao;
import demo.whimsy.entity.User;
import demo.whimsy.service.RegisterMgr;

/**
 * Created by whimsy on 1/30/15.
 */

@Service
public class RegisterMgrImpl implements RegisterMgr {

    @Autowired
    UserDao userDao;


    public Long save(User user) {
        return userDao.save(user);
    }

    @Override
    public List<User> list() {
        return userDao.list();
    }
}

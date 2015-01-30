package demo.whimsy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import demo.whimsy.entity.User;

/**
 * Created by whimsy on 1/30/15.
 */

public interface RegisterMgr {
    Long save(User user);

    List<User> list();
}

package demo.whimsy.dao;

import java.util.List;

import demo.whimsy.entity.User;

/**
 * Created by whimsy on 1/30/15.
 */
public interface UserDao {

    Long save(User user);

    List<User> list();
}

package demo.whimsy.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import demo.whimsy.dao.UserDao;
import demo.whimsy.entity.User;
import demo.whimsy.utils.HibernateDao;

/**
 * Created by whimsy on 1/30/15.
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserDaoImpl extends HibernateDao implements UserDao {

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Long save(User user) {
        Session session = getSession();
        Long id = (Long) session.save(user);
        return id;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> list() {
        Session session = getSession();
        Criteria crit = session.createCriteria(User.class);
        return crit.list();
    }
}

package com.qst.chapter10.ssh;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class UserDao extends HibernateDaoSupport {

    public User login(final String name, final String password) {
        final String hql = "from User where name = ? and password = ?";
        List<User> users = (List<User>) getHibernateTemplate().find(hql, name,
                password);
//        List<User> users = getHibernateTemplate().execute(
//                new HibernateCallback<List<User>>() {
//                    @Override
//                    public List<User> doInHibernate(Session session)
//                            throws HibernateException {
//                        Query query = session.createQuery(hql);
//                        query.setParameter(0, name);
//                        query.setParameter(1, password);
//                        return query.list();
//                    }
//                });

        if (users == null || users.isEmpty())
            return null;
        return users.get(0);
    }
}

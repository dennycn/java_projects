package com.qst.chapter10.hibernate;

public class UserService {

    private UserDao userDao;

    public User login(String name, String password) {
        return userDao.login(name, password);
    }
    
    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}

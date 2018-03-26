package com.qst.chapter10.struts;

public class UserService {

    public boolean login(String name, String password) {
        if ("admin".equals(name) && "1234".equals(password))
            return true;
        return false;
    }
}

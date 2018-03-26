package com.qst.chapter10.hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "com/qst/chapter10/hibernate/applicationContext.xml");
        UserService userService = (UserService) context.getBean("userService");
        User user = userService.login("admin", "1234");
        System.out.println(user.getId());
    }
}

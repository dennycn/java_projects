package com.qst.chapter10.struts;

public class LoginAction {

    private String name;
    private String password;
    private String message;

    private UserService userService;

    public String execute() {
        if (userService.login(name, password))
            message = name + "，欢迎登录。";
        else
            message = "您输入的用户名和密码不正确。";
        return "index";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

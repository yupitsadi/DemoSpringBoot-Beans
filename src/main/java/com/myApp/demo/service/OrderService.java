package com.myApp.demo.service;

import com.myApp.demo.bean.UserConfig;

public class OrderService {
    private UserConfig userConfig;
    private UserService userService;

    public OrderService(UserService userService, UserConfig userConfig) {
        this.userService = userService;
        this.userConfig = userConfig;
    }

    public OrderService createInstance(UserService userService, UserConfig userConfig){
        return null;
    }
}

package com.myApp.demo.service;

import com.myApp.demo.bean.UserConfig;

public class UserService {
   private UserConfig userConfig;

    public UserService(UserConfig userConfig){
        this.userConfig = userConfig;
    }

    public void printUserDetails() {
        System.out.println("Name: " + userConfig.getName());
        System.out.println("Class: " + userConfig.getClassName());
    }
}

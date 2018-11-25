package com.tf.provider.impl;

import com.tf.api.UserService;

/**
 * Created by tingfang
 * 2018-11-22
 */
public class UserServiceImpl implements UserService {
    @Override
    public String sayHello(String name) {
        String x = "Hello " + name;
        System.out.println(x);
        return x;
    }
}
package com.tf.provider.impl;

import com.tf.api.UserService;

import java.util.Random;

/**
 * Created by tingfang
 * 2018-11-22
 */
public class UserServiceImpl implements UserService {
    private String serverName;
    private int delay;

    public UserServiceImpl() {}

    public UserServiceImpl(String serverName, int delay) {
        this.serverName = serverName;
        this.delay = delay;
    }

    @Override
    public String sayHello(String name) throws InterruptedException {
        Thread.sleep(delay);
        String x = "Hello " + name;
        System.out.println(x);
        return x;
//        throw new IllegalStateException();
    }

    @Override
    public String serverName() throws InterruptedException {
        Thread.sleep(delay);
        return serverName;
    }
}
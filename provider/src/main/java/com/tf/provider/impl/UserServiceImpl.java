package com.tf.provider.impl;

import com.tf.api.UserService;

/**
 * Created by tingfang
 * 2018-11-22
 */
public class UserServiceImpl implements UserService {
    private final String serverName;
    private final int delay;

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
    }

    @Override
    public String serverName() throws InterruptedException {
        Thread.sleep(delay);
        return serverName;
    }
}
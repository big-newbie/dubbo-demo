package com.tf.consumer;

import com.tf.api.UserService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.UUID;

/**
 * Created by tingfang
 * 2018-11-27
 */
public class XmlConsumer {
    public static void main(String[] args) throws Exception {
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("app.xml");
        UserService userService;
        applicationContext.start();
//         userService = (UserService) applicationContext.getBean("userService");
        userService = applicationContext.getBean(UserService.class);
        while (true) {
            Thread.sleep(500);
            System.out.println(userService.sayHello(UUID.randomUUID().toString()));
        }
    }
}
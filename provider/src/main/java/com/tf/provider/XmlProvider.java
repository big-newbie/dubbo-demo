package com.tf.provider;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by tingfang
 * 2018-11-27
 */
public class XmlProvider {
    public static void main(String[] args) throws Exception {
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:app.xml");
        applicationContext.start();
        System.in.read();
    }
}
package com.tf.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.tf.api.UserService;

import java.util.Properties;
import java.util.UUID;

/**
 * Created by tingfang
 * 2018-11-25
 */
public class ApiConsumer {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        String name = "connection.properties";
//        properties.load(ApiConsumer.class.getResourceAsStream("/connection.properties"));
        properties.load(ApiConsumer.class.getClassLoader().getResourceAsStream(name));
        String registryAddr = properties.getProperty("dubbo.registry.address");

        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("api-consumer");
        applicationConfig.setQosPort(22222);

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(registryAddr);

        ReferenceConfig<UserService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(UserService.class);
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setVersion("1.0.0");//FIXME 版本号要相同
        UserService userService = referenceConfig.get();

        while (true) {
            Thread.sleep(1000);
            System.out.println(userService.sayHello(UUID.randomUUID().toString()));
        }
    }
}

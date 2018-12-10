package com.tf.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.tf.api.UserService;
import com.tf.utils.PropertyLoader;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by keefe
 * 2018-12-10
 */
public class HessianConsumer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ApplicationConfig ac = new ApplicationConfig();
        ac.setName("hessian-consumer");

        RegistryConfig rc = new RegistryConfig();
        rc.setAddress(PropertyLoader.getRegistry());

        ReferenceConfig<UserService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(ac);
        referenceConfig.setRegistry(rc);
        referenceConfig.setInterface(UserService.class);
        referenceConfig.setProtocol("hessian");

        UserService userService = referenceConfig.get();
        while (true) {
            Thread.sleep(500);
            userService.sayHello(UUID.randomUUID().toString());
        }
    }
}

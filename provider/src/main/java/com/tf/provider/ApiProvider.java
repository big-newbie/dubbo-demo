package com.tf.provider;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.tf.api.UserService;
import com.tf.provider.impl.UserServiceImpl;
import com.tf.utils.PropertyLoader;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by tingfang
 * 2018-11-22
 */
public class ApiProvider {
    public static void main(String[] args) throws IOException {
//        export(33333, 23124, "server 33333", 1000, 0);
//        export(44444, 23125, "server 44444",100,5);
        export(55555, 23126, "server 55555", 1, 10);
    }

    private static void export(int qosPort, int protocolPort, String serverName, int weight, int delay) throws IOException {

        UserService userService = new UserServiceImpl(serverName, delay);
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("A-dubbo-demo");
        applicationConfig.setQosPort(qosPort);

        RegistryConfig registry = new RegistryConfig();//注册中心
        registry.setAddress(PropertyLoader.getRegistry());

        ProtocolConfig protocolConfig = new ProtocolConfig();//暴露服务
        protocolConfig.setCharset("utf-8");
        protocolConfig.setPort(protocolPort);
        protocolConfig.setName("dubbo");

        ServiceConfig<UserService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(UserService.class);
        serviceConfig.setRef(userService);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registry);
//        serviceConfig.setVersion("1.0.0");
        serviceConfig.setWeight(weight);//权重
        serviceConfig.export();

        System.in.read(); // press any key to exit
    }
}
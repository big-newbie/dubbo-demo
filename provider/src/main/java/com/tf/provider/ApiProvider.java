package com.tf.provider;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.tf.api.UserService;
import com.tf.provider.impl.UserServiceImpl;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by tingfang
 * 2018-11-22
 */
public class ApiProvider {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        String name = "connection.properties";
//        properties.load(ApiProvider.class.getResourceAsStream("/connection.properties"));
        properties.load(ApiProvider.class.getClassLoader().getResourceAsStream(name));
        String registryAddr = properties.getProperty("dubbo.registry.address");

        UserService userService = new UserServiceImpl();
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("A-dubbo-demo");
        applicationConfig.setQosPort(33333);

        RegistryConfig registry = new RegistryConfig();//注册中心
        registry.setAddress(registryAddr);

        ProtocolConfig protocolConfig = new ProtocolConfig();//暴露服务
        protocolConfig.setCharset("utf-8");
        protocolConfig.setPort(23124);
        protocolConfig.setName("dubbo");

        ServiceConfig<UserService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(UserService.class);
        serviceConfig.setRef(userService);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registry);
        serviceConfig.setVersion("1.0.0");

        serviceConfig.export();

        System.in.read(); // press any key to exit
    }
}
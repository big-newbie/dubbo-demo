package com.tf.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.cluster.loadbalance.LeastActiveLoadBalance;
import com.tf.api.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tingfang
 * 2018-11-25
 */
public class ApiConsumer {
    /**
     * 负载均衡
     * 1 random 按权重随机，默认，可动态调整权重
     * 2 roundrobin 按权重轮询，如果有服务提供者较慢，会导致请求堆积
     * 3 leastactive 使慢的服务收到更少请求
     * 4 consistenthash 相同参数请求总是发到同一台机器，默认只有第一个参数参与hash
     */
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
//        referenceConfig.setLoadbalance( RandomLoadBalance.NAME);
        referenceConfig.setLoadbalance(LeastActiveLoadBalance.NAME);
        referenceConfig.setVersion("1.0.0");//FIXME 版本号要相同
        UserService userService = referenceConfig.get();

        int count = 50000;
        Map<String, AtomicInteger> counter = new HashMap<>();
        while (count-- > 0) {
//            Thread.sleep(500);
            String serverName = userService.serverName();
            counter.putIfAbsent(serverName, new AtomicInteger());
            counter.get(serverName).incrementAndGet();
        }
        System.out.println("counter = " + counter);
    }
}
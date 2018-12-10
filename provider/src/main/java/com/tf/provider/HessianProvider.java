package com.tf.provider;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.tf.api.UserService;
import com.tf.provider.impl.UserServiceImpl;
import com.tf.utils.PropertyLoader;

import java.io.IOException;

/**
 * Created by keefe
 * 2018-12-10
 */
public class HessianProvider {
    public static void main(String[] args) throws IOException {
        ApplicationConfig ac = new ApplicationConfig();
        ac.setName("hessian-provider");

        RegistryConfig rc = new RegistryConfig();
        rc.setAddress(PropertyLoader.getRegistry());

        ProtocolConfig pc = new ProtocolConfig();
        pc.setName("hessian");
        pc.setPort(80);

        ServiceConfig<UserService> sc = new ServiceConfig<>();

        sc.setApplication(ac);
        sc.setRegistry(rc);
        sc.setProtocol(pc);

        sc.setInterface(UserService.class);
        sc.setRef(new UserServiceImpl());

        sc.export();

        System.in.read();
    }
}

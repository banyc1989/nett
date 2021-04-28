package com.nett.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 对内提供微服务----微服务后台服务
 * @author linkage
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class NettworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettworkApplication.class, args);
    }

}

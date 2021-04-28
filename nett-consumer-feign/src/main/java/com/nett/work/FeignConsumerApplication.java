package com.nett.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * 微服务前台服务----对用户提供入口的入口服务
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages="com.nett.work.feignservice")
@EnableSwagger2
public class FeignConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignConsumerApplication.class, args);
    }

}

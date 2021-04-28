package com.nett.work.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * Swagger 配置
 * 
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "mconfig", name = "swagger-ui-open", havingValue = "true")
public class SwaggerConfig {

	  @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .pathMapping("/")
	                .select() // 选择那些路径和api会生成document
//	                .apis(RequestHandlerSelectors.any())// 对所有api进行监控
	                .apis(RequestHandlerSelectors.basePackage("com.nett.work.controller"))
	                //不显示错误的接口地址
//	                .paths(Predicates.not(PathSelectors.regex("/error.*")))//错误路径不监控
//	                .paths(PathSelectors.regex("/.*"))// 对根下所有路径进行监控
	                .build();
	    }

	  private ApiInfo apiInfo() {
	        return new ApiInfoBuilder().title("这是我的接口文档")
	                .contact(new Contact("rongrong", "", "emai@qq.com"))
	                .description("这是SWAGGER_2生成的接口文档")
	                .termsOfServiceUrl("NO terms of service")
	                .license("The Apache License, Version 2.0")
	                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
	                .version("v1.0")
	                .build();
	    }
}

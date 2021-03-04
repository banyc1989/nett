package com.nett.work.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author 蔡高情
 * @Description  响应加密自定义注解
 * @Date 12:28 2018/7/9 0009
 * @Param 
 * @return 
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseBodyEncrypt {
}

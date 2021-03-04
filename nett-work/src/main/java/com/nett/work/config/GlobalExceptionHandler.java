package com.nett.work.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nett.work.common.entity.R;

@ControllerAdvice
@ResponseBody  //表示返回的对象，Spring会自动把该对象进行json转化，最后写入到Response中。
public class GlobalExceptionHandler {
	
    @ExceptionHandler(Exception.class) //表示让Spring捕获到所有抛出的SignException异常，并交由这个被注解的方法处理。
    @ResponseStatus(HttpStatus.BAD_REQUEST)  //表示设置状态码。
    R handleException(Exception ex){
    	System.out.println(ex.getMessage());
    	if(ex instanceof NullPointerException){
    		return new R().error(500, "NullPointerException");
        }
        return new R().error(500, "系统异常");
    }
}
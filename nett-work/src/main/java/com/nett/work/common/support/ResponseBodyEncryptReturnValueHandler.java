package com.nett.work.common.support;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.nett.work.common.annotation.ResponseBodyEncrypt;
import com.nett.work.common.entity.R;
import com.nett.work.utils.encrypt.AesCBC;

/**
 * @Author 蔡高情
 * @Description  加密响应
 * @Date 12:34 2018/7/9 0009
 * @Param 
 * @return 
 **/
public class ResponseBodyEncryptReturnValueHandler implements HandlerMethodReturnValueHandler {

    private final HandlerMethodReturnValueHandler delegate;


    //通过构造注入
    public ResponseBodyEncryptReturnValueHandler(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
    }

    /**
     * 判断方法上的注解是不是ResponseBodyEncrypt
     * @param methodParameter
     * @return
     */
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        Method method=methodParameter.getMethod();
        //父类是否含有RestController
        Annotation[] classAnnotation =method.getDeclaringClass().getAnnotations();
        boolean flag=false;
        for (int i = 0; i < classAnnotation.length; i++) {
            if(classAnnotation[i].annotationType().equals(RestController.class)){
                flag=true;
                break;
            }
        }
        return methodParameter.getMethodAnnotation
                (ResponseBodyEncrypt.class) != null || methodParameter.getMethodAnnotation(ResponseBody.class) != null ||flag ;
    }

    @Override
    public void handleReturnValue(Object returnValue,
                                  MethodParameter returnType,
                                  ModelAndViewContainer mavContainer,
                                      NativeWebRequest webRequest) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        //获得返回值类型
        Class<?> returnParaType = returnType.getParameterType();
        if (!void.class.isAssignableFrom(returnParaType)) {
           boolean flag= R.class.isAssignableFrom(returnParaType);
            if(flag){
                R r=(R)returnValue;
                Object data=r.get("data");
                String result= AesCBC.encrypt(data.toString(),"1234567812345678","1234567812345678");
                r.put("data",result);
                returnValue=r;
            }
            // 不是Response、Model等类型的返回值，需要包裹为Response类型
            System.out.println(!flag);
            System.out.println(returnParaType);
            System.out.println(!HttpServletResponse.class.isAssignableFrom(returnParaType));
            System.out.println(!Model.class.isAssignableFrom(returnParaType));
            System.out.println(returnType.getMethodAnnotation(ResponseBodyEncrypt.class) != null);
            
            if (!flag && !HttpServletResponse.class.isAssignableFrom(returnParaType) && !Model.class.isAssignableFrom(returnParaType)
            		&& returnType.getMethodAnnotation(ResponseBodyEncrypt.class) != null) {
               String result= AesCBC.encrypt(returnValue.toString(),"1234567812345678","1234567812345678");

                returnValue=new R(result);
            }
            delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
        }
    }


}

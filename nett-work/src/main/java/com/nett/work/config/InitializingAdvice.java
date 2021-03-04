package com.nett.work.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.nett.work.common.support.ResponseBodyEncryptReturnValueHandler;

/**
 * 初始化切面
 *
 * Created by 12614 on 2018/5/11.
 */
@Configuration
public class InitializingAdvice implements InitializingBean {

	@Autowired
	private RequestMappingHandlerAdapter adapter;

	@Override
	public void afterPropertiesSet() throws Exception {
		List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
		List<HandlerMethodReturnValueHandler> handlers = new ArrayList(returnValueHandlers);
		this.decorateHandlers(handlers);
		adapter.setReturnValueHandlers(handlers);
	}

	private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {
		for (HandlerMethodReturnValueHandler handler : handlers) {
			if (handler instanceof RequestResponseBodyMethodProcessor) {
				ResponseBodyEncryptReturnValueHandler decorator = new ResponseBodyEncryptReturnValueHandler(
						(RequestResponseBodyMethodProcessor) handler);
				int index = handlers.indexOf(handler);
				handlers.set(index, decorator);
				break;
			}
		}
	}
}
package com.nett.work.interceptor;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class InParamInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map<String, String[]> map = request.getParameterMap();
		for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
			Map.Entry element = (Map.Entry) iter.next();
			Object strKey = element.getKey();
			String[] strArr = (String[]) element.getValue();
			System.out.println(strKey);
			for (String str : strArr) {
				System.err.print(str); // yanggb
			}
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}

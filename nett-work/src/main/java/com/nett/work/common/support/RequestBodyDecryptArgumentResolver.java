package com.nett.work.common.support;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nett.work.common.annotation.RequestBodyDecrypt;
import com.nett.work.common.entity.JSONObjectWrapper;
import com.nett.work.common.entity.MapWrapper;
import com.nett.work.common.exception.SharechargerOpenException;
import com.nett.work.utils.encrypt.AesCBC;
import com.nett.work.utils.encrypt.GetRequestDataUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 蔡高情
 * @Description 自定义HandlerMethodArgumentResolver解析
 * @Date 12:36 2018/7/9 0009
 * @Param
 * @return
 **/
@Slf4j
public class RequestBodyDecryptArgumentResolver implements HandlerMethodArgumentResolver {

	// 判断是否支持要转换的参数类型
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(RequestBodyDecrypt.class);
	}

	// 当支持后进行相应的转换
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

		// 获取全部的所有参数
		Map<String, String[]> parMap = request.getParameterMap();
		// 获取加密的postBody
		String encryBody = GetRequestDataUtil.getEncryBody(request);

		// 进行解密操作返回解密后的书数据getRequestBody();
		String body = getRequestBody(encryBody);
		Object result;

		System.out.println("GET HTTP RequestBodyData IS " + body);
//        log.debug("GET HTTP RequestBodyData IS " +body);
		Class clazz = parameter.getParameterType();
		if (StringUtils.isEmpty(body)) {
			return null;
		}
		if (clazz.equals(List.class)) {
			result = JSON.parseArray(body);
		}
		// 利用fastjson转换为对应的类型
		if (JSONObjectWrapper.class.isAssignableFrom(parameter.getParameterType())) {
			result = new JSONObjectWrapper(JSON.parseObject(body));
		}

		result = JSON.parseObject(body.toString(), parameter.getParameterType());
		// 如果有key则获取key的值返回
		String key = parameter.getParameterAnnotation(RequestBodyDecrypt.class).value();
		if (StringUtils.isNotEmpty(key)) {
			JSONObject data = JSON.parseObject(body.toString());
			// 获取json的key
			result = data.get(key);

		}
		// mapWrapper类型
		if (MapWrapper.class.isAssignableFrom(clazz)) {
			MapWrapper mapWrapper = new MapWrapper();
			Map target = (Map) JSON.parseObject(body.toString());
			mapWrapper.setInnerMap(target);
			result = (Object) mapWrapper;
		}
		return result;

	}

	/**
	 * 获取提交的post json 验证加密
	 * 
	 * @param
	 * @return
	 */
	private String getRequestBody(String encryBody) throws SharechargerOpenException, Exception {
		// 这里可以做appId,appk的校验，这里涉及到企业代码不写了，用base64代替
		// 解析完后 的传递参数
		// 这里可以动态获取，我这写死了
		String data = AesCBC.decrypt(encryBody, "1234567812345678", "1234567812345678");
		return data;
	}

}
package com.nett.work.interceptor;
//package com.ntt.work.interceptor;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.alibaba.fastjson.JSON;
//
//@Component
//public class SignInterceptor implements HandlerInterceptor {
//	private static final String SIGN_KEY = "apisign_";
//	private static final Logger logger = LogManager.getLogger("bussniesslog");
//	@Autowired
//	private RedisTemplate redisTemplate;
//
//	/*
//	 * @author:liuhongdi
//	 * 
//	 * @date:2020/7/1 下午4:00
//	 * 
//	 * @description:
//	 * 
//	 * @param request：请求对象
//	 * 
//	 * @param response：响应对象
//	 * 
//	 * @param handler：处理对象：controller中的信息 * *@return:true表示正常,false表示被拦截
//	 */
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		// 依次检查各变量是否存在？
//		String appId = request.getHeader("appId");
//		if (StringUtils.isBlank(appId)) {
//			ServletUtil.renderString(response, JSON.toJSONString(ResultUtil.error(ResponseCode.SIGN_NO_APPID)));
//			return false;
//		}
//		String timestampStr = request.getHeader("timestamp");
//		if (StringUtils.isBlank(timestampStr)) {
//			ServletUtil.renderString(response, JSON.toJSONString(ResultUtil.error(ResponseCode.SIGN_NO_TIMESTAMP)));
//			return false;
//		}
//		String sign = request.getHeader("sign");
//		if (StringUtils.isBlank(sign)) {
//			ServletUtil.renderString(response, JSON.toJSONString(ResultUtil.error(ResponseCode.SIGN_NO_SIGN)));
//			return false;
//		}
//		String nonce = request.getHeader("nonce");
//		if (StringUtils.isBlank(nonce)) {
//			ServletUtil.renderString(response, JSON.toJSONString(ResultUtil.error(ResponseCode.SIGN_NO_NONCE)));
//			return false;
//		}
//		// 得到正确的sign供检验用
//		String origin = appId + Constants.APP_SECRET + timestampStr + nonce + Constants.APP_API_VERSION;
//		String signEcrypt = MD5Util.md5(origin);
//		long timestamp = 0;
//		try {
//			timestamp = Long.parseLong(timestampStr);
//		} catch (Exception e) {
//			logger.error("发生异常", e);
//		}
//		// 前端的时间戳与服务器当前时间戳相差如果大于180，判定当前请求的timestamp无效
//		if (Math.abs(timestamp - System.currentTimeMillis() / 1000) > 180) {
//			ServletUtil.renderString(response,
//					JSON.toJSONString(ResultUtil.error(ResponseCode.SIGN_TIMESTAMP_INVALID)));
//			return false;
//		}
//		// nonce是否存在于redis中，检查当前请求是否是重复请求
//		boolean nonceExists = redisStringUtil.hasStringkey(SIGN_KEY + timestampStr + nonce);
//		if (nonceExists) {
//			ServletUtil.renderString(response, JSON.toJSONString(ResultUtil.error(ResponseCode.SIGN_DUPLICATION)));
//			return false;
//		}
//		// 后端MD5签名校验与前端签名sign值比对
//		if (!(sign.equalsIgnoreCase(signEcrypt))) {
//			ServletUtil.renderString(response, JSON.toJSONString(ResultUtil.error(ResponseCode.SIGN_VERIFY_FAIL)));
//			return false;
//		}
//		// 将timestampstr+nonce存进redis
//		redisStringUtil.setStringValue(SIGN_KEY + timestampStr + nonce, nonce, 180L);
//		// sign校验无问题,放行
//		return true;
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//	}
//}

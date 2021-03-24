package com.nett.work.Aop;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//定义切面
@Aspect
@Component
public class LogAopAspect {

	@Before(value = "execution( * com.nett.work.controller.*.*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		try {
			String operationType = "";
			String operateExplain = "";
			// 获取封装了署名信息的对象,在该对象中可以获取到目标方法名,所属类的Class等信息
			Signature signature = joinPoint.getSignature();
			// 目标方法名
			String methodName = signature.getName();
			// 目标方法所属类的简单类名
			String simpleName = signature.getDeclaringType().getSimpleName();
			// 获取传入目标方法的参数对象
			Object target = joinPoint.getTarget();
			// 获取当前代理类的全限定名
			String targetName = joinPoint.getTarget().getClass().getName();

			// 获取传入目标方法的参数对象
			Object[] args = joinPoint.getArgs();
			Class<?> aClass = null;
			try {
				aClass = Class.forName(targetName);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			Method[] methods = aClass.getMethods();
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].getName() != methodName) {
					continue;
				}
				Class<?>[] clazzs = methods[i].getParameterTypes();
				if (clazzs.length == args.length) {
					operationType = methods[i].getAnnotation(Log.class).operateType(); // 获取指定类型的注释
					operateExplain = methods[i].getAnnotation(Log.class).operateExplain();
					break;
				}

			}
			System.out.println("署名信息的对象:" + signature);
			System.out.println("目标方法所属类的简单类名:" + simpleName);
			System.out.println("获取传入目标方法的参数对象:" + target);

			System.out.println("代理类：" + targetName);
			System.out.println("方法名:" + methodName);
			System.out.println("操作类型:" + operationType);
			System.out.println("操作解释:" + operateExplain);

			System.out.println("---------------------------开始保存日志----------------------------------");

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-HH-dd hh:mm:ss");
			String date = dateFormat.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 异常通知
//	@AfterThrowing(value = "execution( * com.nett.work.controller.UserConsumerController.get(..))", throwing = "e")
//	public void exceptionAdvice(JoinPoint joinPoint, Throwable e) {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//				.getRequest();
//		HttpSession session = request.getSession();
//		// 读取session中的用户
//		Object user = session.getAttribute("user");
//		// 请求的IP
//		String ip = request.getRemoteAddr();
//		String params = "";
//		if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
//			params = Arrays.toString(joinPoint.getArgs());
//		}
//		System.out.println(params);
//		try {
//			String operationType = "";
//			String operateExplain = "";
//			// 获取传入目标方法的参数对象
//			Object[] args = joinPoint.getArgs();
//			// 目标方法所属类的简单类名
//			String simpleName = joinPoint.getSignature().getDeclaringType().getSimpleName();
//			// 获取方法名
//			String methodName = joinPoint.getSignature().getName();
//			// 获取类的全限定名
//			String targetName = joinPoint.getTarget().getClass().getName();
//
//			// 获取该类的Class对象
//			Class<?> clazz = null;
//			try {
//				clazz = Class.forName(targetName);
//			} catch (ClassNotFoundException ee) {
//				ee.printStackTrace();
//			}
//			Method[] methods = clazz.getMethods();
//			for (int i = 0; i < methods.length; i++) {
//				if (methods[i].getName() != methodName) {
//					continue;
//				}
//
//				Class<?>[] parameterTypes = methods[i].getParameterTypes();
//				if (parameterTypes.length == args.length) {
//					methods[i].getAnnotation(Log.class);
//					Log annotation = methods[i].getAnnotation(Log.class);
//					operationType = annotation.operateType();
//					operateExplain = annotation.operateExplain();
//					break;
//				}
//			}
//			System.out.println("---------------------------开始保存日志----------------------------------");
//
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-HH-dd hh:mm:ss");
//			String date = dateFormat.format(new Date());
//		} catch (Exception ee) {
//			ee.printStackTrace();
//		}
//	}
}

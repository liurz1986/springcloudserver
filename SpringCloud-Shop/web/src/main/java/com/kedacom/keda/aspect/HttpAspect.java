package com.kedacom.keda.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//@Component
//@Aspect
public class HttpAspect {

	private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

	@Pointcut("execution(public * com.kedacom.keda.controller.UserController.*(..))")
	public void log() {
	}

	@Before("log()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// url
		logger.info("url={}", request.getRequestURI());

		// method
		logger.info("method={}", request.getMethod());

		// ip
		logger.info("ip={}", request.getRemoteAddr());

		// 类方法
		logger.info("class_method={}",
				joinPoint.getSignature().getDeclaringTypeName() + "," + joinPoint.getSignature().getName());

		// 参数
		logger.info("args={}", joinPoint.getArgs());

		logger.info("------Before------");

	}

	@After("log()")
	public void doAfter() {
		logger.info("------After------");
	}

	@AfterReturning(returning = "object", pointcut = "log()")
	public void DoAfterReturning(Object object) {
		logger.info("response={}", object);
	}
}

package com.nps.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Around("execution(* com.nps.*.*.addQuote*(..))")
	public Object arroundAddQuote(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object[] args = proceedingJoinPoint.getArgs();
		logger.info("About to save the quote: {}", args);
		long begin = System.currentTimeMillis();
		Object result = proceedingJoinPoint.proceed();
		long end = System.currentTimeMillis();
		logger.info("Added the quotes: {} in {}ms", result, end - begin);
		return result;
	}

	@Around("execution(* com.nps.*.*.getQueryQuotes*(..))")
	public Object arroundGetQueryQuote(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object[] args = proceedingJoinPoint.getArgs();
		logger.info("About to get the quotes for: {}", args);
		long begin = System.currentTimeMillis();
		Object result = proceedingJoinPoint.proceed();
		long end = System.currentTimeMillis();
		logger.info("Fetched quotes: {} in {}ms", result, end - begin);
		return result;
	}
	
	
}

package com.ecommerce.bakerymanagement.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Component
@Log4j
@Configuration
public class LoggerAspect {

	@Around("execution(* com.ecommerce.bakerymanagement..*.*(..))")
	public Object aroundAdviceForAllMethods(ProceedingJoinPoint joinPoint) throws Throwable {
		StringBuilder logMessage = new StringBuilder();
		long startTime = System.currentTimeMillis();
		logMessage.append("Method Start Time:-" + startTime + " \t ms ");
		logMessage.append(joinPoint.getTarget().getClass().getName());
		logMessage.append(".");
		logMessage.append(joinPoint.getSignature().getName());
		logMessage.append("(");
		Object[] args = joinPoint.getArgs();

		for (int i = 0; i < args.length; i++) {
			logMessage.append(args[i]).append(",");
		}
		if (args.length > 0) {
			logMessage.deleteCharAt(logMessage.length() - 1);
		}

		logMessage.append(")");
		log.info(logMessage.toString());

		logMessage = new StringBuilder();

		Object proceed = joinPoint.proceed();
		long endTime = System.currentTimeMillis();
		logMessage.append("** Method End Time:-" + endTime + "ms \t and ");
		long totalTime = endTime - startTime;
		logMessage.append("actual Time Taken By the  Method is:-" + totalTime / 1000 + " ");
		logMessage.append("\t ms");
		logMessage.append("and  method execution ends with Method Name " + joinPoint.getSignature().getName() + " **");

		log.info(logMessage.toString());

		return proceed;
	}
}

package com.ecommerce.bakerymanagement.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Component
@Configuration
@Log4j
public class ExceptionAspect {

	@AfterThrowing(pointcut = "execution(* com.ecommerce.bakerymanagement..*.*(..))", throwing = "ex")
	public Object logAfterThrowingAllMethods(Exception ex) throws Throwable {
		log.error("**Logged exception is:- " + ex + "** expecption cause is:-" + ex.getMessage());
		return ex;
	}

}

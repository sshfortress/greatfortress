package com.sshfortress.controller.log;


import java.lang.annotation.*;


@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface Log {

	     
	  /** 要执行的具体操作比如：添加用户 **/  
	   public String operationName() default "";
	
} 

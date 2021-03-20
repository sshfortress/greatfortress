package com.sshfortress.controller.log;


import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sshfortress.common.util.DateUtil;
import com.sshfortress.common.util.OperationContextHolder;


@Aspect
@Component
public class SystemLogAspect {
	
	//Controller层切点  
	@Pointcut("execution (* com.sshfortress.controller..add*(..)) or (* com.sshfortress.controller..delete*(..)) or (* com.sshfortress.controller..update*(..))")  
	public  void controllerAspect() {  
		
	}
	 
	   
	@After("controllerAspect()")  
	public  void after(JoinPoint joinPoint){
		String userId = OperationContextHolder.getUserId();
		String ip = OperationContextHolder.getLoginIp();
		try{
		String targetName = joinPoint.getTarget().getClass().getName();  
		String methodName = joinPoint.getSignature().getName();  
		Object[] arguments = joinPoint.getArgs();  
		Class targetClass = Class.forName(targetName);  
		Method[] methods = targetClass.getMethods();
		String operationName = "";
		for (Method method : methods) {  
			if (method.getName().equals(methodName)) {  
				Class[] clazzs = method.getParameterTypes();  
				if (clazzs.length == arguments.length) {  
					operationName = method.getAnnotation(Log.class).operationName();
					break;  
		                   }  
	               }  
	    }
		}catch(Exception e){
			
		}
	}
}

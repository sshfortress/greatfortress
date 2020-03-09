package com.sshfortress.common.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component("domainConfig")
public class DomainConfig {
	@Value("${current.environment}")
	public  String CURRENT_ENVIRONMENT; 
	@Value("${vm.environment}")
	public  String VM_ENVIRONMENT;
	@Value("${greatfortressframework.domain}")
	public  String GREATFORTRESSFRAMEWORK_DOMAIN; 
	@Value("${greatfortressframework.static.imageonline}")
	public String GREATFORTRESSFRAMEWORK_STATIC_IMAGEONLINE;
	@Value("${greatfortressframework.url}")
	public String GREATFORTRESSFRAMEWORK_URL;
	@Value("${greatfortressframework.service.url}")
	public String GREATFORTRESSFRAMEWORK_SERVICE_URL;
	
	@Value("${greatfortressframework.static.image.win}")
	public String GREATFORTRESSFRAMEWORK_STATIC_IMAGE_WIN;
	
	@Value("${greatfortressframework.static.image.linux}")
	public String GREATFORTRESSFRAMEWORK_STATIC_IMAGE_LINUX;
	
	
	public boolean isDebug()
	{
		 if (!StringUtils.equalsIgnoreCase("FORMAL",this.VM_ENVIRONMENT)) {
	            return true;
	     }
		return false;
	}
	
	
	
	
	
}

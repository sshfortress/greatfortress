package com.sshfortress.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sshfortress.common.enums.HttpChannelType;
import com.sshfortress.common.enums.UserTypeEnums;
import com.sshfortress.common.model.SessionUser;
import com.sshfortress.common.util.JsonUtil;
import com.sshfortress.common.util.OperationContextHolder;
import com.sshfortress.common.util.SignUtils;

public class DoSessionFilter extends OncePerRequestFilter {

	/**
	 * jar包不要引错 org.apache.commons.logging.*
	 */

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession().getServletContext());
		
		//System.out.println("进入过滤器开始");
		TicketHelper ticketHelper = (TicketHelper) webApplicationContext.getBean("ticketHelper");

		// 取ticket（加签的）
		String ticket = ticketHelper.getTicket(request, HttpChannelType.APP);

		// 共享session_id失效或者不存在时
		if (StringUtils.isBlank(ticket)) {
		//filterChain.doFilter(request, response);
		//System.out.println("解签 第一步");
		//return;
			ticket="";
		}

		// 解签ticket
		String dticket = SignUtils.decrypt(ticket);

		if (StringUtils.isBlank(dticket)) {
		//filterChain.doFilter(request, response);
		//System.out.println("第2步 解签");
		//return;
			dticket="";
		}

		SessionUser sessionUser = null;

		try {
			if(!StringUtils.isBlank(dticket)){
			sessionUser = (SessionUser) JsonUtil.toObject(dticket, SessionUser.class);
			}
			if (sessionUser == null || !UserTypeEnums.getEnumByCode(sessionUser.getUserType()).isAppMember()) {
				filterChain.doFilter(request, response);
			} else {
				// TODO(待做)
				// 注册上下文
				OperationContextHolder.setIsLoggerUser(sessionUser);

				ticketHelper.setCookie(request, response, ticket);

				filterChain.doFilter(request, response);
			}
			//System.out.println("进入过滤器结束");	
		} catch (Exception e) {
			filterChain.doFilter(request, response);
		} finally {
			// 每个request请求结束后清理该用户上下文")
			//System.out.println("执行过滤器FINALLY方法");
			OperationContextHolder.clearUser();
		}
	}

}

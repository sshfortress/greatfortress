package com.sshfortress.common.websocket;

import java.util.Map;



import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.sshfortress.common.model.User;

public class MyHandShakeInterceptor2 implements HandshakeInterceptor  {

	
	  @Override
	    public boolean beforeHandshake(ServerHttpRequest request,
	            ServerHttpResponse response, WebSocketHandler wsHandler,
	            Map<String, Object> attributes) throws Exception {
	       System.out.println("执行beforeHandshake方法*****拦截器"); 
		   if (request instanceof ServletServerHttpRequest) {
	            // 保存session中已登录的用户到websocket的上下文环境中。在推送消息的时候，需要根据当前登录用户获取点位权限
	            User u=new User();
	            u.setId(1);
	            u.setName("test");
	            u.setUserId(1);
	            attributes.put("1", u);	  
	            attributes.put("userId",((ServletServerHttpRequest) request).getServletRequest().getParameter("userId"));
	        }
	        return true;
	    }

	    @Override
	    public void afterHandshake(ServerHttpRequest request,
	            ServerHttpResponse response, WebSocketHandler wsHandler,
	            Exception exception) {
	        System.out.println("执行afterHandshake方法*****拦截器");
	    }
	
}

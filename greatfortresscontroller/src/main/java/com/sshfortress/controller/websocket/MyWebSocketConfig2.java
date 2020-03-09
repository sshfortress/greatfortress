package com.sshfortress.controller.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.sshfortress.common.websocket.MyHandShakeInterceptor2;


@Component
@EnableWebSocket
@EnableWebMvc
public class MyWebSocketConfig2 implements WebSocketConfigurer{
	
	@Autowired
	MyWebSocketHandler2 myWebSocketHandler2;
		
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(myWebSocketHandler2, "/myHandler").setAllowedOrigins("*").addInterceptors(new MyHandShakeInterceptor2());
		System.out.println("执行registerWebSocketHandlers方法");
	}

}

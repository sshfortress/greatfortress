package com.sshfortress.common.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.sshfortress.common.model.User;

public class MyHandShakeInterceptor implements HandshakeInterceptor  {

	@Override
	public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
			WebSocketHandler webSocketHandler, Exception arg3) {
		
		
	}

	@Override
	 public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        System.out.println("Websocket:用户[ID:" + ((ServletServerHttpRequest) serverHttpRequest).getServletRequest().getSession(false).getAttribute("user") + "]已经建立连接");
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) serverHttpRequest;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            // 标记用户
            User user = (User) session.getAttribute("user");
            if(user!=null){
                map.put("uid", user.getUserId());//为服务器创建WebSocketSession做准备
                System.out.println("用户id："+user.getUserId()+" 被加入");
            }else{
                System.out.println("user为空");
                return false;
            }
        }
        return true;
    }

}

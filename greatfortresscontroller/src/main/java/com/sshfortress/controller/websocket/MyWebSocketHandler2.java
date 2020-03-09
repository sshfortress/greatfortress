package com.sshfortress.controller.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;


@Component
public class MyWebSocketHandler2 implements WebSocketHandler {

	
	private static final Map<String,WebSocketSession> userSocketSessionMapThreadLocal = new HashMap<String,WebSocketSession>();


	@Override
	/**
	 * 连接关闭
	 */
	public void afterConnectionClosed(WebSocketSession arg0, CloseStatus arg1)
			throws Exception {

		//webSocketContextHolder.clearContext();

		userSocketSessionMapThreadLocal.remove(arg0.getAttributes().get("userId").toString());

		System.out.println("执行afterConnectionClosed方法*****关闭连接 userId "+arg0.getAttributes().get("userId").toString());

	}

	@Override
	/**
	 * 建立链接
	 */
	public void afterConnectionEstablished(WebSocketSession arg0)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("执行afterConnectionEstablished方法*****建立连接 userId "+arg0.getAttributes().get("userId").toString());		

		userSocketSessionMapThreadLocal.put(arg0.getAttributes().get("userId").toString(),arg0);
		// String a="你好";
		// byte[] b=a.getBytes();
		// arg0.sendMessage(new TextMessage(b));
//		while(true){
//			this.sendMessage("12312346546");
//		}
	}

	/**
	 * 发送消息
	 */
	public void handleMessage(WebSocketSession arg0, WebSocketMessage<?> arg1)
			throws Exception {
		// TODO Auto-generated method stub

		// arg0.sendMessage(arg1);
		// String a="这个是一个坑";
		// byte[] b=a.getBytes();
		// arg0.sendMessage(new TextMessage(b));
		System.out.println("执行handleMessage方法*****发送消息");
	}

	@Override
	/**
	 * 传输错误异常
	 */
	public void handleTransportError(WebSocketSession arg0, Throwable arg1)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("执行handleTransportError方法*****传输错误");

	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		System.out.println("执行supportsPartialMessages方法");
		return false;
	}

	/**
	 * 发送消息
	 * @throws IOException 
	 */
	public void sendMessage(String message) throws IOException {
		
		Iterator iter = userSocketSessionMapThreadLocal.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			WebSocketSession ws =  (WebSocketSession) entry.getValue();
			ws.sendMessage(new TextMessage(message.getBytes()));
		}
	}
}

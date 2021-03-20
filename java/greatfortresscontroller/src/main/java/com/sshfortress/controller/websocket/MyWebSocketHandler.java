package com.sshfortress.controller.websocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketExtension;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.model.User;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.common.websocket.Message;
import com.sshfortress.service.websocket.WebSocketService;

@Component
//@Controller
@RequestMapping("websocket2")
public class MyWebSocketHandler implements WebSocketHandler {

	@Autowired
	WebSocketService webSocketService;
	
	

	//当MyWebSocketHandler类被加载时就会创建该Map，随类而生
	public static final Map<Integer, WebSocketSession> userSocketSessionMap;

	static {
		userSocketSessionMap = new HashMap<Integer, WebSocketSession>();
	}
	
	
	@RequestMapping(value = "/add.do",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody 
	public ResponseObj sendMsg(User user) throws Exception {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		final int list = webSocketService.addUser(user);
		WebSocketSession webSocketSession=new WebSocketSession() {
			
			@Override
			public void setTextMessageSizeLimit(int messageSizeLimit) {
				
				
			}
			
			@Override
			public void setBinaryMessageSizeLimit(int messageSizeLimit) {
				
				
			}
			
			@Override
			public void sendMessage(WebSocketMessage<?> message) throws IOException {
				
				
			}
			
			@Override
			public boolean isOpen() {
				
				return false;
			}
			
			@Override
			public URI getUri() {
				
				return null;
			}
			
			@Override
			public int getTextMessageSizeLimit() {
				
				return 0;
			}
			
			@Override
			public InetSocketAddress getRemoteAddress() {
				
				return null;
			}
			
			@Override
			public Principal getPrincipal() {
				
				return null;
			}
			
			@Override
			public InetSocketAddress getLocalAddress() {
				
				return null;
			}
			
			@Override
			public String getId() {
				
				return null;
			}
			
			@Override
			public HttpHeaders getHandshakeHeaders() {
				
				return null;
			}
			
			@Override
			public List<WebSocketExtension> getExtensions() {
				
				return null;
			}
			
			@Override
			public int getBinaryMessageSizeLimit() {
				
				return 0;
			}
			
			@Override
			public Map<String, Object> getAttributes() {
				
				return null;
			}
			
			@Override
			public String getAcceptedProtocol() {
				
				return null;
			}
			
			@Override
			public void close(CloseStatus status) throws IOException {
				
				
			}
			
			@Override
			public void close() throws IOException {
				
				
			}
		};
		
		Message msg=new Message();
		msg.setMessageDate(new Date());
		msg.setMessageText("增加用户，id为 "+list);
		msg.setTitle("增加用户websocket测试");
		
		WebSocketMessage<?> webSocketMessage=new WebSocketMessage<Message>() {

			@Override
			public Message getPayload() {
				System.out.println("执行payLoad方法");
				Message msg=new Message();
				msg.setMessageDate(new Date());
				msg.setMessageText("增加用户，id为 "+list);
				msg.setTitle("增加用户websocket测试");
				return msg;
			}

			@Override
			public int getPayloadLength() {
				System.out.println("执行getPayloadLength方法");
				return 1;
			}

			@Override
			public boolean isLast() {
				System.out.println("执行isLast方法");
				return false;
			}
		};
		
		this.handleMessage(webSocketSession, webSocketMessage);
		System.out.println(webSocketMessage.getPayload());
		obj.setData(list);
		return obj;
	}
	
	
	 //握手实现连接后
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        int uid = (Integer) webSocketSession.getAttributes().get("uid");
        if (userSocketSessionMap.get(uid) == null) {
            userSocketSessionMap.put(uid, webSocketSession);
        }
    }
	

	


	/**
     * 在此刷新页面就相当于断开WebSocket连接,原本在静态变量userSocketSessionMap中的
     * WebSocketSession会变成关闭状态(close)，但是刷新后的第二次连接服务器创建的
     * 新WebSocketSession(open状态)又不会加入到userSocketSessionMap中,所以这样就无法发送消息
     * 因此应当在关闭连接这个切面增加去除userSocketSessionMap中当前处于close状态的WebSocketSession，
     * 让新创建的WebSocketSession(open状态)可以加入到userSocketSessionMap中
     * @param webSocketSession
     * @param closeStatus
     * @throws Exception
     */
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

        System.out.println("WebSocket:"+webSocketSession.getAttributes().get("uid")+"close connection");
        Iterator<Map.Entry<Integer,WebSocketSession>> iterator = userSocketSessionMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer,WebSocketSession> entry = iterator.next();
            if(entry.getValue().getAttributes().get("uid")==webSocketSession.getAttributes().get("uid")){
                userSocketSessionMap.remove(webSocketSession.getAttributes().get("uid"));
                System.out.println("WebSocket in staticMap:" + webSocketSession.getAttributes().get("uid") + "removed");
            }
        }
    }


    //发送信息前的处理
	public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage)
			throws Exception {
		
		 if(webSocketMessage.getPayloadLength()==0)return;

	        //得到Socket通道中的数据并转化为Message对象
	        Message msg=(Message) webSocketMessage.getPayload();
	        
	        Date now = new Date();
	        msg.setMessageDate(now);
	        //将信息保存至数据库
	        webSocketService.addMessage(msg.getTitle(),msg.getMessageText(),msg.getMessageDate());

	        //发送Socket信息
	        //sendMessageToUser(msg.getId(), new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
	    }


	@Override
	public void handleTransportError(WebSocketSession arg0, Throwable arg1)
			throws Exception {
		
		
	}


	@Override
	public boolean supportsPartialMessages() {
		
		return false;
	}

	//发送信息的实现
    public void sendMessageToUser(int uid, TextMessage message)
            throws IOException {
        WebSocketSession session = userSocketSessionMap.get(uid);
        if (session != null && session.isOpen()) {
            session.sendMessage(message);
        }
    }


}

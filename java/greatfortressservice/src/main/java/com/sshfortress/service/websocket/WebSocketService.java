package com.sshfortress.service.websocket;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshfortress.common.model.User;
import com.sshfortress.common.websocket.Message;
import com.sshfortress.dao.common.mapper.CommonMapper;

@Service
public class WebSocketService {
	
	@Autowired
	CommonMapper commonMapper;
	
	public int addUser(User user){
		commonMapper.addTest(user);
		user.setUserId(user.getId());
		return user.getUserId();
	}
	
	
	public int addMessage(String title,String messageText,Date messageDate){
		Message message=new Message();
		message.setMessageDate(new Date());
		message.setTitle(title);
		message.setMessageText(messageText);
		commonMapper.addMsg(message);
		return 1;
	}
}

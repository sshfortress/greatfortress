package com.sshfortress.service.commands;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshfortress.common.model.PageListByCommands;
import com.sshfortress.dao.commands.mapper.CommandsMapper;

@Service
public class CommandsService {
	
	@Autowired
	CommandsMapper commandsMapper;
	
	
	public List<PageListByCommands> getCommandsPageList(Map<String,Object> m){
		
		return commandsMapper.getCommandsPageList(m);
	}
	
	
}

package com.sshfortress.dao.commands.mapper;

import java.util.List;
import java.util.Map;

import com.sshfortress.common.model.PageListByCommands;

public interface CommandsMapper {
    
    List<PageListByCommands> getCommandsPageList(Map<String,Object> m);
}
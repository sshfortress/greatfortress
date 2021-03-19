package com.sshfortress.dao.system.mapper;

import java.util.List;
import java.util.Map;

import com.sshfortress.common.base.BaseMapper;
import com.sshfortress.common.beans.DabaseTable;


public interface DabaseTableMapper extends BaseMapper<Object,DabaseTable>{
	List<Map<String, String>> queryColumns(String tableName);
}

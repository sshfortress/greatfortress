package com.sshfortress.service.sftpcomm;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshfortress.common.model.PageListBySftpcomm;
import com.sshfortress.dao.sftpcomm.mapper.SftpcommMapper;

@Service
public class SftpcommService {
	
	@Autowired
	SftpcommMapper sftpcommMapper;
	
	
	public List<PageListBySftpcomm> getSftpcommPageList(Map<String,Object> m){
		
		return sftpcommMapper.getSftpcommPageList(m);
	}
	
	
}

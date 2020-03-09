package com.sshfortress.dao.sftpcomm.mapper;

import java.util.List;
import java.util.Map;

import com.sshfortress.common.model.PageListBySftpcomm;

public interface SftpcommMapper {
    
    List<PageListBySftpcomm> getSftpcommPageList(Map<String,Object> m);
}
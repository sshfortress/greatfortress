package com.sshfortress.dao.ssh.mapper;

import java.util.List;
import java.util.Map;

import com.sshfortress.common.entity.SshList;
import com.sshfortress.common.model.DetailBySsh;
import com.sshfortress.common.model.PageListBySsh;

public interface SshListMapper {
    int deleteByPrimaryKey(Long id);

    int addSSH(SshList record);

    int insertSelective(SshList record);

    SshList selectByPrimaryKey(Long id);

    int updateSSH(SshList record);

    int updateByPrimaryKey(SshList record);
    
    List<PageListBySsh> getSshPageList(Map<String,Object> m);
    
    DetailBySsh getDetailBySsh(Map<String,Object> m);
    
    List<String> isExistNameBySsh(Map<String,Object> m);
}
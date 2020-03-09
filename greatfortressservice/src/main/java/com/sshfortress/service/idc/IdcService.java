package com.sshfortress.service.idc;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sshfortress.common.entity.IdcList;
import com.sshfortress.common.model.DetailByIdc;
import com.sshfortress.common.model.PageListByIdc;
import com.sshfortress.common.util.OperationContextHolder;
import com.sshfortress.dao.idc.mapper.IdcListMapper;

@Service
public class IdcService {
	
	@Autowired
	IdcListMapper idcListMapper;
	
	/**机房分页列表*/
	public List<PageListByIdc> getIdcPageList(Map<String,Object> m){
		
		return idcListMapper.getIdcPageList(m);
	}
	
	/**机房详情*/
	public DetailByIdc getIdcDetail(Map<String,Object> m){
		
		return idcListMapper.getIdcDetail(m);
	}
	
	/**名称是否存在*/
	public List<String> isExistName(Map<String,Object> m){
		
		return idcListMapper.isExistName(m);
	}
	
	/**增加*/
	@Transactional
	public int addIdc(IdcList record){
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		record.setIsDelete(0);
		record.setCreator(OperationContextHolder.getUserId());
		return idcListMapper.addIdc(record);
	}
	
	/**更新*/
	@Transactional
	public int updateIdc(IdcList record){
		
		return idcListMapper.updateIdc(record);
	} 
	
	/**删除*/
	@Transactional
	public int deleteIdc(String[] idcId){
		for(int i=0;i<idcId.length;i++){
			IdcList idcList=new IdcList();
			idcList.setId(Long.valueOf(idcId[i]));
			idcList.setIsDelete(1);
			idcListMapper.updateIdc(idcList);
		}
		return 1;
	} 
}

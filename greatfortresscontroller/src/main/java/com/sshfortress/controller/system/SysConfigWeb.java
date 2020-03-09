package com.sshfortress.controller.system;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshfortress.common.beans.SysConfig;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.model.SysAreaModel;
import com.sshfortress.common.util.OperationContextHolder;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.common.util.StringUtil;
import com.sshfortress.common.util.TreeDataUtil;
import com.sshfortress.dao.system.mapper.SysAreaMapper;
import com.sshfortress.service.system.AreaService;
import com.sshfortress.service.system.SysConfigService;

@Controller
@RequestMapping(value = "/configWeb")
public class SysConfigWeb {


    @Autowired
    private SysConfigService configService;
    
    /**
     *  更新配置的值  
     *  ckey:后台给
     *  cvalue:需要更新的字段
     * @param config
     * @return
     */
    @RequestMapping(value= "/updateCvalueByCkey.web" ,method=RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseObj updateCvalueByCkey(SysConfig config){
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), ViewShowEnums.INFO_SUCCESS.getDetail());
		if(StringUtil.isNullOrEmpty(config.getCvalue())){
			obj.setShowMessage("不能为空");
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			return obj;
		}
		int updateCvalueByCkey = configService.updateCvalueByCkey(config);
		if(updateCvalueByCkey<=0){
			obj.setShowMessage("修改失败");
			obj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			return obj;
		}
		obj.setData(updateCvalueByCkey);
		return obj;
	}
    
    /**
     * 查询配置的值
     * @param ckey:后台给
     * @return
     */
    @RequestMapping(value= "/getCvalueByCkey.web" ,method=RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResponseObj getCvalueByCkey(String ckey){
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), ViewShowEnums.INFO_SUCCESS.getDetail());
		String cvalue = configService.getCvalueByCkey(ckey);
		obj.setData(cvalue);
		return obj;
	}
    
}

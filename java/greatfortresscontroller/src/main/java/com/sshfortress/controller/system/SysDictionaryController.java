package com.sshfortress.controller.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.service.init.SysDictionary;


@Controller
@RequestMapping(value = "/dictionary")
public class SysDictionaryController {
	
	@Autowired
	SysDictionary sysDictionary;
		
	  @RequestMapping(value= "/getCvalueByCkey.do" ,method=RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
		@ResponseBody
		public ResponseObj getCvalueByCkey(String ckey){
			ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), ViewShowEnums.INFO_SUCCESS.getDetail());
			List<Map<String, String>> cvalue = sysDictionary.getDvalueList(ckey);
			obj.setData(cvalue);
			return obj;
		}
}

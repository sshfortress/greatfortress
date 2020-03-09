package com.sshfortress.controller.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshfortress.common.beans.MemberForm;
import com.sshfortress.common.beans.MemberInfo;
import com.sshfortress.common.beans.SysUser;
import com.sshfortress.common.beans.SysUserInfo;
import com.sshfortress.common.enums.UserStatus;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.model.UserDetailDto;
import com.sshfortress.common.util.PageModel;
import com.sshfortress.common.util.Pager;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.common.util.StringUtil;
import com.sshfortress.common.util.ValidateUtil;
import com.sshfortress.controller.websocket.MyWebSocketHandler2;
import com.sshfortress.dao.system.mapper.SysUserMapper;
import com.sshfortress.dao.user.mapper.SysUserInfoMapper;
import com.sshfortress.service.common.CommonService;
import com.sshfortress.service.system.SysUserService;

@Controller
@RequestMapping("common")
public class Common {

	@Autowired
	CommonService commonService;

	@Autowired
	MyWebSocketHandler2 myWebSocketHandler2;


	@RequestMapping(value = "/geoCoordMap.do",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody 
	public ResponseObj geoCoordMap() throws IOException {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		Map<String,Object> g=new HashMap<String,Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		//经纬度map
		
		//城市/国家及对应的经纬度
		Map<String, Object> cityMap = new HashMap<String, Object>();
		ArrayList<Double> a=new ArrayList<Double>();
		a.add(77.216667);
		a.add(28.666668);
		ArrayList<Double> h=new ArrayList<Double>();
		h.add(119.5313);
		h.add(29.8773);
		ArrayList<Double> h1=new ArrayList<Double>();
		h1.add(139.691711);
		h1.add(35.689499);
		ArrayList<Double> h2=new ArrayList<Double>();
		h2.add(100.51667);
		h2.add(13.75);
		cityMap.put("印度", a.toArray());
		cityMap.put("杭州", h.toArray());
		cityMap.put("东京", h1.toArray());
		cityMap.put("泰国", h2.toArray());

		//固定的map,写死
		Map<String, Object> hzMap = new HashMap<String, Object>();
		hzMap.put("name", "杭州");
		List<List<Map<String,Object>>> m=new ArrayList<List<Map<String,Object>>>();
		//某个城市/国家及对应的数量
		Map<String, Object> numberMap = new HashMap<String, Object>();
		numberMap.put("name", "印度");
		numberMap.put("value", 900);
		Map<String, Object> numberMap2 = new HashMap<String, Object>();
		numberMap2.put("name", "东京");
		numberMap2.put("value", 100);
//		Map<String, Object> numberMap3 = new HashMap<String, Object>();
//		numberMap3.put("name", "泰国");
//		numberMap3.put("value", 500);
		List<Map<String,Object>> l=new ArrayList<Map<String,Object>>();
		l.add(numberMap);
		l.add(hzMap);
		m.add(l);
		List<Map<String,Object>> l2=new ArrayList<Map<String,Object>>();
		l2.add(numberMap2);
		l2.add(hzMap);
		m.add(l2);
//		List<Map<String,Object>> l3=new ArrayList<Map<String,Object>>();
//		l3.add(numberMap3);
//		l3.add(hzMap);
//		m.add(l3);

		map.put("geoCoord", cityMap);
		map.put("mm", m);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.fromObject(map);

		myWebSocketHandler2.sendMessage(jsonObject.fromObject(map).toString());
		System.out.println(jsonObject.fromObject(map).toString());
		obj.setData(map);
		return obj;

	}

	@RequestMapping(value = "/userList.do",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody 
	public ResponseObj getUserList() {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = commonService.getUserList(map);
		obj.setData(list);
		return obj;
	}



	@RequestMapping(value = "/assetList.do",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody 
	public ResponseObj getAssetPageList(Pager page,String searchText) {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchText", searchText);
		//不传表示ALL
		if(null==page.getPageSize() || "".equals(page.getPageSize())){

		}else{
			map.put("page", page);
		}
		List<Map<String, Object>> list = commonService.getAssetPageList(map);
		obj.setData(new PageModel(page, list));
		return obj;
	}


	@RequestMapping(value = "/assetGroupList.do",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody 
	public ResponseObj getAssetGroupList() {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = commonService.getAssetGroupList(map);
		obj.setData(list);
		return obj;
	}


	@RequestMapping(value = "/idcList.do",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody 
	public ResponseObj getIdcList() {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = commonService.getIdcList(map);
		obj.setData(list);
		return obj;
	}
	
	@RequestMapping(value = "/top5.do",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody 
	public ResponseObj top5() {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = commonService.getSSHLoginNameByWeek();
		obj.setData(list);
		return obj;
	}
	
	@RequestMapping(value = "/month.do",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody 
	public ResponseObj month() {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		Map<String, Object> list = commonService.getSSHLoginByMonth();
		obj.setData(list);
		return obj;
	}



}

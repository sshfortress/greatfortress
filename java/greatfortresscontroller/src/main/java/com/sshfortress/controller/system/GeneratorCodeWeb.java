//package com.sshfortress.controller.system;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.sshfortress.common.beans.DabaseTable;
//import com.sshfortress.common.util.Pager;
//import com.sshfortress.dao.system.mapper.DabaseTableMapper;
//import com.sshfortress.service.system.GeneratorCodeService;
//
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//
//@Controller
//@RequestMapping("generator")
//public class GeneratorCodeWeb {
//	@Autowired
//	DabaseTableMapper dabaseTableMapper;
//	@Autowired
//	GeneratorCodeService generatorCodeService;
//	
//	@ApiOperation(value="数据库", notes="获取库中的表")
//	@RequestMapping(value= "/dabaseTableList" ,method=RequestMethod.GET)
//	@ResponseBody
//	public List<DabaseTable> userList(HttpServletRequest req, HttpServletResponse res, Pager page) {
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("page", page);
//		List<DabaseTable> list = dabaseTableMapper.queryByParamsListPager(map);
//		return list;
//	}
//
//	@RequestMapping(value="/generatorCode" ,method=RequestMethod.GET)
//	@ResponseBody public Map<String, Object> generatorCode(Pager page) {
//		Map<String, Object> resultMap  = new HashMap<String, Object>();
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("page", page);
//		List<DabaseTable> list = dabaseTableMapper.queryByParamsListPager(map);
//		
//		resultMap.put("status", Integer.valueOf(200));
//	    resultMap.put("message", "生成成功");
//		try {
//			generatorCodeService.generatorCode(list);
//		} catch (Exception e) {
//			e.printStackTrace();
//			resultMap.put("status", Integer.valueOf(500));
//		    resultMap.put("message", "错误");
//		}
//		return resultMap;
//	}
//
//}

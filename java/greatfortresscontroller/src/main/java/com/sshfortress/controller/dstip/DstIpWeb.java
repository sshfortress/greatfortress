package com.sshfortress.controller.dstip;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshfortress.common.entity.AssetList;
import com.sshfortress.common.entity.DstipLogin;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.model.DetailByAsset;
import com.sshfortress.common.model.PageListByAsset;
import com.sshfortress.common.util.PageModel;
import com.sshfortress.common.util.Pager;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.common.util.StringUtil;
import com.sshfortress.common.util.ValidateUtil;
import com.sshfortress.controller.websocket.MyWebSocketHandler2;
import com.sshfortress.service.asset.AssetService;
import com.sshfortress.service.dstip.DstIpService;
import com.sshfortress.service.dstip.DstIpService2;

@Controller
@RequestMapping("dstipWeb")
public class DstIpWeb {

	@Autowired
	DstIpService dstIpService;
	
	@Autowired
	DstIpService2 dstIpService2;
	
	@Autowired
	MyWebSocketHandler2 myWebSocketHandler2;

	/**
	 * <p class="detail">
	 * 功能：登录日志记录
	 * </p>
	 * @param dstipLogin
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/addDstIpLogin.do",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.GET)
	@ResponseBody 
	public ResponseObj addDstIpLogin(DstipLogin dstipLogin,String minTime,String maxTime) throws IOException {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		Map<String,Object> m=dstIpService2.addDstIpLogin(dstipLogin,minTime,maxTime);
		
		//发送WebSocket消息
		JSONObject jsonObject = new JSONObject();
		
		myWebSocketHandler2.sendMessage(jsonObject.fromObject(m).toString());
		obj.setData(m);
		return obj;
	}
	
	@RequestMapping(value = "/getDstIpLoginDetail.do",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody 
	public ResponseObj addDstIpLogin2(DstipLogin dstipLogin,String minTime,String maxTime) throws IOException {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		Map<String,Object> m=dstIpService2.addDstIpLogin(dstipLogin,minTime,maxTime);
		
		//发送WebSocket消息
//		JSONObject jsonObject = new JSONObject();
//		
//		myWebSocketHandler2.sendMessage(jsonObject.fromObject(m).toString());
		obj.setData(m);
		return obj;
	}



}

package com.sshfortress.controller.sftpcomm;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshfortress.common.entity.AssetList;
import com.sshfortress.common.entity.DstipLogin;
import com.sshfortress.common.entity.Sftpcomm;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.model.DetailByAsset;
import com.sshfortress.common.model.PageListByAsset;
import com.sshfortress.common.model.PageListByCommands;
import com.sshfortress.common.model.PageListBySftpcomm;
import com.sshfortress.common.util.PageModel;
import com.sshfortress.common.util.Pager;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.common.util.StringUtil;
import com.sshfortress.common.util.ValidateUtil;
import com.sshfortress.controller.websocket.MyWebSocketHandler2;
import com.sshfortress.service.asset.AssetService;
import com.sshfortress.service.dstip.DstIpService;
import com.sshfortress.service.sftpcomm.SftpcommService;

@Controller
@RequestMapping("sftpcomm")
public class SftpcommWeb {

	@Autowired
	SftpcommService sftpCommService;
	

	@RequestMapping(value = "/getSftpCommPageList.web",produces = { "application/json;charset=UTF-8" },  method = RequestMethod.POST)
	@ResponseBody 
	public ResponseObj addSftpComm(Pager page,String searchText,String minTime,String maxTime) throws IOException {
		ResponseObj obj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchText", searchText);
		map.put("minTime", minTime);
		map.put("maxTime", maxTime);
		//不传表示ALL
		if(null==page.getPageSize() || "".equals(page.getPageSize())){

		}else{
			map.put("page", page);
		}
		List<PageListBySftpcomm> data=sftpCommService.getSftpcommPageList(map);
		obj.setData(new PageModel(page, data));
		return obj;
	}



}

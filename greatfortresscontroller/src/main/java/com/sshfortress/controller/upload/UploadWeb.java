package com.sshfortress.controller.upload;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sshfortress.common.contants.ViewContants;
import com.sshfortress.common.enums.HttpChannelType;
import com.sshfortress.common.enums.PicAppType;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.util.OperationContextHolder;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.controller.base.FileBaseController;
import com.sshfortress.service.helper.UploadPicHelper;

@Controller
@RequestMapping(value = "/systemweb")
public class UploadWeb  extends FileBaseController {

	@Autowired
	UploadPicHelper uploadPicHelper;
	
	 /** <p class="detail">
	 * 功能：上传单张图片
	 * </p>
	 * @param file
	 * @param picTypeName
	 * @param request
	 * @return    
	 */
	@RequestMapping(value = "/upload.do", produces = { "application/json;charset=UTF-8" }, method = {
			RequestMethod.POST })
	@ResponseBody
	public String uploadPic(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) {


		ResponseObj ajaxResponseObj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), ViewShowEnums.INFO_SUCCESS.getDetail());;
		String originFileName = file.getOriginalFilename();
		ajaxResponseObj = uploadPicHelper.uploadPic(file,  request);

		if (ajaxResponseObj.getStatus() == 0) {
			return ajaxResponseObj.toAppJson();
		}

		String picUrl = ajaxResponseObj.getData().toString();
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("originFileName", originFileName);
		m.put("picUrl", picUrl);
		return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), ViewShowEnums.INFO_SUCCESS.getDetail(), m)
				.toAppJson();
	}
	
}

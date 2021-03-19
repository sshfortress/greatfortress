package com.sshfortress.controller.upload;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.util.CommonUtils;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.common.util.StringUtil;
import com.sshfortress.service.helper.UploadFileOss;


@Controller
@RequestMapping(value = "/fileoss")
public class FileUpload {
		
	   @Autowired
       private UploadFileOss uploadFileOss;
	
	    @RequestMapping(value="uploadFile.do",produces={"application/json;charset=UTF-8"})
	    @ResponseBody
	    public String uploadFile(@RequestParam(value = "file", required = false) MultipartFile[] files,String menu, HttpServletRequest request, HttpServletResponse response){
	    	
	    	String str=request.getHeader("User-Agent").toLowerCase();
	    	    	
	    	if(StringUtil.isNullOrEmpty(menu)){
	    	menu="";
	    	}	   
	        String pathPreName = "greatfortress/"+menu;
	        List<Object> imgUrl = new ArrayList<Object>();
	        for(MultipartFile file:files){
	        	
	        	boolean b=CommonUtils.isApp(str); //返回是否为APP端请求       	
	            ResponseObj obj = uploadFileOss.uploadFile(file, pathPreName,b);
	            if(obj.getStatus()!=ViewShowEnums.INFO_SUCCESS.getStatus()){
	                return obj.toWebJson(request, response);
	            }
	            imgUrl.add(obj.getData());
	        }
	        return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), ViewShowEnums.INFO_SUCCESS.getDetail(), imgUrl).toWebJson(request, response);
	    }
	    
	
	    
}

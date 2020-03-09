package com.sshfortress.controller.system;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;









import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sshfortress.common.beans.LoginFormWeb;
import com.sshfortress.common.enums.UserStatus;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.common.util.UserTypesUtil;
import com.sshfortress.controller.websocket.MyWebSocketHandler2;
import com.sshfortress.service.helper.LoginHelper;


@Controller
@RequestMapping("/userweb/")
public class LoginWeb {
	
	  @Autowired
	  LoginHelper loginHelper;
	  
	  @Autowired
	  MyWebSocketHandler2 m;

	  @RequestMapping(value = "login.do", produces = { "application/json;charset=UTF-8" },method = RequestMethod.POST)
	  @ResponseBody
	  public  ResponseObj  login(LoginFormWeb loginFormWeb, BindingResult result,
	                        HttpServletRequest request, HttpServletResponse response) throws IOException {
		  ResponseObj ajaxResponseObj  =new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), ViewShowEnums.INFO_SUCCESS.getDetail());
	        if (result.hasErrors()) {
	                 ajaxResponseObj = new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(),
	                result.getAllErrors().get(0).getDefaultMessage());

	            return ajaxResponseObj;
	        }

//	        if(!loginFormWeb.getSrand().toUpperCase().equals(request.getSession().getAttribute("piccode").toString().toUpperCase())){
//	        	ajaxResponseObj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
//	        	ajaxResponseObj.setShowMessage("输入验证码不正确");
//
//				return ajaxResponseObj;
//			}
	        
//	        m.sendMessage("登陆成功");
	        return loginHelper.loginWeb(loginFormWeb.getUserName(),loginFormWeb.getPassWord(), UserTypesUtil.getWebuserTypes(), UserStatus.OPEN.getCode(),request, response,ajaxResponseObj);

	    }
}

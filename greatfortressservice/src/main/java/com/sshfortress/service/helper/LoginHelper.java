package com.sshfortress.service.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sshfortress.common.beans.SysLogin;
import com.sshfortress.common.beans.TicketModel;
import com.sshfortress.common.beans.UserInfo;
import com.sshfortress.common.entity.Member;

import com.sshfortress.common.enums.UserTypeEnums;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.filter.TicketHelper;
import com.sshfortress.common.model.SessionUser;
import com.sshfortress.common.model.User;
import com.sshfortress.common.model.UserDetailDto;
import com.sshfortress.common.util.DateUtil;
import com.sshfortress.common.util.JsonUtil;
import com.sshfortress.common.util.OperationContextHolder;
import com.sshfortress.common.util.RequestParam;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.common.util.SignUtils;
import com.sshfortress.dao.system.mapper.SysLoginMapper;
import com.sshfortress.dao.system.mapper.SysUserMapper;
import com.sshfortress.dao.user.mapper.SysUserInfoMapper;

@Service("loginHelper")
public class LoginHelper {
	
	Logger log=Logger.getLogger(LoginHelper.class);
	
	@Autowired
	SysUserInfoMapper sysUserInfoMapper;
	@Autowired
	SysUserMapper sysUserMapper;
	@Autowired
	TicketHelper ticketHelper;
	
	@Autowired
	SysLoginMapper sysLoginMapper;
	
	/**
	 * 
	 * @param userName
	 * @param passWord
	 * @param userTypes
	 * @param status
	 * @param request
	 * @param response
	 * @param respObj
	 * @return
	 */
	@Transactional
	public ResponseObj loginWeb(String userName, String passWord, List<Integer> userTypes,Integer status, HttpServletRequest request,
			HttpServletResponse response, ResponseObj respObj) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userName", userName);
		param.put("userTypes", userTypes);
		param.put("passWord", passWord);
		param.put("status", 1);
		UserInfo userInfo = sysUserInfoMapper.selecttUserInfoByManyUserType(param);
		if (userInfo == null) {
			respObj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
			respObj.setShowMessage("用户不存在或密码错误");
			return respObj;
		}
		TicketModel ticketModel = new TicketModel();
		BeanUtils.copyProperties(userInfo, ticketModel);
		respObj.setData(ticketModel);
		param.put("userId", userInfo.getUserId());
		// 修改用户
		UserInfo userInfoUp = new   UserInfo();
		userInfoUp.setUserId(userInfo.getUserId());
		userInfoUp.setLastLoginTime(new Date());
		userInfoUp.setUpdateTime(new Date());
		userInfoUp.setLastLoginIp(RequestParam.getRealAddress(request));
		sysUserInfoMapper.updateByPrimaryKeySelective(userInfoUp);
		setTicket(ticketModel, request, response, respObj);
		
		//登录日志
		SysLogin sl=new SysLogin();
		sl.setDevType(UserTypeEnums.DEALER.getCode());
		sl.setUserId(Long.parseLong(userInfo.getUserId()));
		sl.setTicket(ticketModel.getTicket());
		sl.setLoginTime(new Date());
		sysLoginMapper.insert(sl);
		return respObj;
	}
	
	 
    /**
     * <p class="detail">
     * 功能：登出 注销
     * </p>
     * @param userName
     * @param userType
     * @param request
     * @param response
     * @param respObj
     * @return
     */
    public ResponseObj logout(HttpServletRequest request,
			HttpServletResponse response, ResponseObj respObj)
    {
    	OperationContextHolder.clearUser();
		return respObj;
    }
    
	 /** <p class="detail">
	 * 功能：修改密码
	 * </p>
	 * @param appCode
	 * @param UserName
	 * @param passWord
	 * @return    
	 */
	public ResponseObj changePassword(String userName, String passWord,Integer type) {
		  ResponseObj appResponseObj = new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
		            ViewShowEnums.INFO_SUCCESS.getDetail());
		  Map<String,Object> params = new HashMap<String, Object>();
		  params.put("userName", userName);
		  params.put("passWord", passWord);
		  params.put("userType", type);
		  int updateByUserNameAndUserType = sysUserMapper.updateByUserNameAndUserType(params);
          if(updateByUserNameAndUserType>0)
          {
        	  appResponseObj.setData("");
        	  return appResponseObj;
          }
          else
          {
        	  appResponseObj.setStatus(ViewShowEnums.ERROR_FAILED.getStatus());
        	  appResponseObj.setShowMessage("修改失败");
        	  appResponseObj.setData("");
        	  return appResponseObj;
          }
	     
	  }
	
	
	
	/**
	 * <p class="detail">
	 * 功能：这里写方法描述
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 * @param respObj
	 * @return
	 */
	public ResponseObj fillTicketModel(HttpServletRequest request, HttpServletResponse response, String userId,
			ResponseObj respObj) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		UserInfo userInfo = sysUserInfoMapper.selecLogintUserInfoByParam(param);
		TicketModel ticketModel = new TicketModel();
		if (null != userInfo) {
			BeanUtils.copyProperties(userInfo, ticketModel);
			return setTicket(ticketModel, request, response, respObj);
		}

		return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "登录失败");
	}
    
	/**
	 * <p class="detail">
	 * 功能：得到ticket
	 * </p>
	 * @param request
	 * @param response
	 * @param userId
	 * @param respObj
	 * @return
	 */
	public String getTicketInfo(HttpServletRequest request, HttpServletResponse response, String userId,
			ResponseObj respObj) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		UserInfo userInfo = sysUserInfoMapper.selecLogintUserInfoByParam(param);
		TicketModel ticketModel = new TicketModel();
		if (null != userInfo) {
			BeanUtils.copyProperties(userInfo, ticketModel);
			setTicket(ticketModel, request, response, respObj);
			return ticketModel.getTicket();
		}

		return ticketModel.getTicket();
	}
	
	/**
	 * <p class="detail">
	 * 功能：设置ticket
	 * </p>
	 * 
	 * @param ticketModel
	 * @param request
	 * @param response
	 * @param respObj
	 * @return
	 */
	@Transactional
	private ResponseObj setTicket(TicketModel ticketModel, HttpServletRequest request, HttpServletResponse response,
			ResponseObj respObj) {
		SessionUser sessionUser = new SessionUser();
		sessionUser.setUserId(ticketModel.getUserId());
		sessionUser.setUserName(ticketModel.getUserName());
		sessionUser.setLoginTime(ticketModel.getLastLoginTime());
		sessionUser.setUserType(ticketModel.getUserType());
		//sessionUser.setLoginIp(ticketModel.getLoginIp());
		String ticket = SignUtils.encrypt(StringUtils.trim(JsonUtil.toJson(sessionUser)));
		ticketModel.setTicket(ticket);
		ticketHelper.setCookie(request, response, ticket);
		return respObj;
	}

}

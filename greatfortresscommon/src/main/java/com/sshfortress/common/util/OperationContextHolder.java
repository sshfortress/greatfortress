/**
 * winchance Inc.
 */
package com.sshfortress.common.util;

import java.util.Date;

import com.sshfortress.common.enums.UserTypeEnums;
import com.sshfortress.common.model.SessionUser;


/** <p class="detail">
 * 功能：会话上下文环境
 * </p>
 * @ClassName: OperationContextHolder 
 * @version V1.0  
 */
public class OperationContextHolder {

    public static void setIsLoggerUser(SessionUser sessionUser) {

        ContextHolder.setConcurrentObject(sessionUser);
    }

    /**
     * 获取当前会话
     * 
     * @return
     */
    public static SessionUser getSessionUser() {

        return (SessionUser) ContextHolder.getConcurrentObject();
    }

    /**
     * 获取上下文登录userId
     * 
     * @return
     */
    public static String getUserId() {

        SessionUser sessionUser = getSessionUser();
        return null != sessionUser ? sessionUser.getUserId() : null;
    }

    /**
     * 获取上下文登录loginId
     * 
     * @return
     */
    public static String getUserName() {

        SessionUser sessionUser = getSessionUser();
        return null != sessionUser ? sessionUser.getUserName() : null;
    }



    public static UserTypeEnums getUserType() {
        SessionUser sessionUser = getSessionUser();
        return null != sessionUser ? UserTypeEnums.getEnumByCode(sessionUser.getUserType()) : null;
    }

    /**
     * 登录loginTime
     * 
     * @return
     */
    public static Date getLoginTime() {

        SessionUser sessionUser = getSessionUser();
        return null != sessionUser ? sessionUser.getLoginTime() : null;

    }
    
    
    public static String getLoginIp() {
        SessionUser sessionUser = getSessionUser();
        return null != sessionUser ? sessionUser.getLoginIp() : null;

    }
    /**
     * 清除当前会话
     */
    public static void clearUser() {

        ContextHolder.clearContext();
    }
}

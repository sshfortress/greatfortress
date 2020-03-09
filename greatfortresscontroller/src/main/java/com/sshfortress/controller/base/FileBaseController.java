/*
 *  
 * This software is the confidential and proprietary information of
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 */
package com.sshfortress.controller.base;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sshfortress.common.contants.ViewContants;
import com.sshfortress.common.enums.HttpChannelType;
import com.sshfortress.common.filter.TicketHelper;
import com.sshfortress.common.model.SessionUser;
import com.sshfortress.common.util.JsonUtil;
import com.sshfortress.common.util.OperationContextHolder;
import com.sshfortress.common.util.SignUtils;

/** <p class="detail">
 * 功能：上传二进制文件controller需要继承FileBaseController来获取登录信息
 * </p>
 * @ClassName: FileBaseAction 
 * @version V1.0  
 */
public abstract class FileBaseController {

    /**
     * jar包不要引错 org.apache.commons.logging.*
     */
    private static final Log logger = LogFactory.getLog(FileBaseController.class);

    @Autowired
    private TicketHelper     ticketHelper;

    protected boolean loginWebValidate(String ticket) {

        if (StringUtils.isBlank(ticket)) {
            return false;
        }

        String detTicket = SignUtils.decrypt(StringUtils.trim(ticket));

        try {
            return fromJson(detTicket);
        } catch (Exception e) {
            logger.error(detTicket + "校验登录解析异常", e);
            return false;
        }

    }
    
     /** <p class="detail">
     * 功能：文件上传端登录校验
     * </p>
     * @param request
     * @param httpChannelType
     * @return    
     */
    protected boolean loginValidate(HttpServletRequest request, HttpChannelType httpChannelType) {

        String ticket = ticketHelper.getTicket(request, httpChannelType);

        if (StringUtils.isBlank(ticket)) {
            return false;
        }

        String dticket = SignUtils.decrypt(ticket);

        if (StringUtils.isBlank(dticket)) {
            return false;
        }

        try {
            return fromJson(dticket);
        } catch (Exception e) {
            logger.error(dticket + ":校验登录解析异常", e);
            return false;
        }

    }

    private boolean fromJson(String detTicket) {
        SessionUser sessionUser = JsonUtil.toObject(detTicket, SessionUser.class);
        if (validatorLoginTime(sessionUser.getLoginTime())) {
            OperationContextHolder.setIsLoggerUser(sessionUser);
            return true;
        }
        return false;
    }

    /** <p class="detail">
    * 功能：验证登录时效性  
    * </p>
    * @see ViewContants.TRUST_COOKIE_TIME 分钟后过期
    * @param loginTime
    * @return    
    */
    private boolean validatorLoginTime(Date loginTime) {
        /*        int minute = DateUtilSelf.calculateDecreaseMinute(loginTime, new Date());
                // 30分钟后过期
                if (minute > ViewContants.TRUST_COOKIE_TIME) {
                    return false;
                }*/
        return true;
    }
     /** <p class="detail">
     * 功能：设置浏览器允许跨域，*表示允许所有域名，可以指定允许接入的域名(逗号分隔)
     * </p>
     * @param response    
     */
    protected void setAccessContrlAllowOrigin(HttpServletResponse response) {
        /*
         * origin参数指定一个允许向该服务器提交请求的域名(eg:http://www.sshfortress.cn;多个域名使用逗号分隔)，*：允许任意域名跨域访问资源，生产库域名申请完成后需配置服务器明确的域名
         */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /*
         * 指明资源可以被请求的方式有哪些(一个或者多个). 这个响应头信息在客户端发出预检请求的时候会被返回
         */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        /*
         * 设置浏览器允许访问的服务器的头信息的白名单
         */
        response
            .setHeader(
                "Access-Control-Allow-Headers",
                "Origin,X-Requested-With,Content-Type,Authorization,Accept, No-Cache, If-Modified-Since,Last-Modified, Cache-Control, Expires, X-E4M-With");
        /*
         * 默认情况下，跨源请求不提供凭据(cookie、HTTP认证及客户端SSL证明等)。
         * 通过将withCredentials属性设置为true，可以指定某个请求应该发送凭据。如果服务器接收带凭据的请求，会用下面的HTTP头部来响应
         * 如果发送的是带凭据的请求，但服务器的相应中没有包含这个头部，那么浏览器就不会把相应交给JavaScript(于是，responseText中将是空字符串，status的值为0，而且会调用onerror()事件处理程序)
         */
        response.setHeader("Access-Control-Allow-Credentials", "true");
        /*
         * 一次预请求的结果的有效时间(秒)，在该时间内不再发送预请求
         */
        response.setHeader("Access-Control-Max-Age", "2592000");
    }
}

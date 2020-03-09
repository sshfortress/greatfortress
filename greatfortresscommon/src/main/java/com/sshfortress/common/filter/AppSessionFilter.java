/*
 *  
 * This software is the confidential and proprietary information of
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 */
package com.sshfortress.common.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sshfortress.common.contants.ViewContants;
import com.sshfortress.common.enums.HttpChannelType;
import com.sshfortress.common.enums.UserTypeEnums;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.model.SessionUser;
import com.sshfortress.common.util.JsonUtil;
import com.sshfortress.common.util.OperationContextHolder;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.common.util.SignUtils;


/** <p class="detail">
 * 功能：
 * </p>
 * @ClassName: AppSessionFilter 
 * @version V1.0  
 */
public class AppSessionFilter extends OncePerRequestFilter {

    /**
     * jar包不要引错 org.apache.commons.logging.*
     */
    private static final Log sessionLog = LogFactory.getLog(AppSessionFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        WebApplicationContext webApplicationContext = WebApplicationContextUtils
            .getWebApplicationContext(request.getSession().getServletContext());

        TicketHelper ticketHelper = (TicketHelper) webApplicationContext.getBean("ticketHelper");

        // 取ticket（加签的）
        String ticket = ticketHelper.getTicket(request, HttpChannelType.APP);

        // 共享session_id失效或者不存在时
        if (StringUtils.isBlank(ticket)) {
            notLogin(response);
            return;
        }

        //解签ticket
        String dticket = SignUtils.decrypt(ticket);

        if (StringUtils.isBlank(dticket)) {
            notLogin(response);
            return;
        }

        SessionUser sessionUser = null;

        try {
            sessionUser = (SessionUser) JsonUtil.toObject(dticket, SessionUser.class);

            if (sessionUser != null) {

                if (!UserTypeEnums.getEnumByCode(sessionUser.getUserType()).isAppMember()) {
                    notAuthority(response);
                    return;
                }
                //TODO(待做)
                // 注册上下文
                OperationContextHolder.setIsLoggerUser(sessionUser);
 
                ticketHelper.setCookie(request, response, ticket);

                filterChain.doFilter(request, response);
            } else {
                sessionLog.warn("app用户登录信息失效dticket: " + dticket);
                notLogin(response);
                return;
            }
        } catch (Exception e) {
            sessionLog.error("未处理的系统异常", e);
            doException(response);
            return;
        } finally {
            // 每个request请求结束后清理该用户上下文")
            OperationContextHolder.clearUser();
        }

    }

    private void notAuthority(HttpServletResponse response) {
        ResponseObj ajaxResponseObj = new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(),
            ViewContants.NO_AUTHORITY);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(ajaxResponseObj.toAppJson());
        } catch (Exception e) {
            sessionLog.error("json输出失败", e);
        } finally {
            try {
                if (null != writer) {
                    writer.close();
                }
            } catch (Exception e) {
                sessionLog.error("json关闭异常", e);
            }
            writer = null;
        }
        return;
    }

    private void notLogin(HttpServletResponse response) {
        ResponseObj ajaxResponseObj = new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(),
            ViewContants.NOT_LOG_SHOW);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(ajaxResponseObj.toAppJson());
        } catch (Exception e) {
            sessionLog.error("json输出失败", e);
        } finally {
            try {
                if (null != writer) {
                    writer.close();
                }
            } catch (Exception e) {
                sessionLog.error("json关闭异常", e);
            }
            writer = null;
        }
        return;
    }

    private void doException(HttpServletResponse response) {
        ResponseObj ajaxResponseObj = new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(),
            "未处理的系统异常");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(ajaxResponseObj.toAppJson());
        } catch (Exception e) {
            sessionLog.error("json输出失败", e);
        } finally {
            try {
                if (null != writer) {
                    writer.close();
                }
            } catch (Exception e) {
                sessionLog.error("json关闭异常", e);
            }
            writer = null;
        }
        return;
    }
}

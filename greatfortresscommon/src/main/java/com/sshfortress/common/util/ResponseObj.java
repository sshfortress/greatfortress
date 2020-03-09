package com.sshfortress.common.util;
/*
 *  
 * This software is the confidential and proprietary information of
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 */


import java.io.Serializable;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.sshfortress.common.contants.ViewContants;


/** <p class="detail">
 * 功能：web与app通用的选择json
 * </p>
 * @ClassName: ResponseObj 
 * @version V1.0  
 */
public class ResponseObj implements Serializable {

    /** 
     * <p class="detail">
     * 功能：这里写描述
     * </p>
     * @Fields serialVersionUID 
     */ 
    private static final long serialVersionUID = -7049443021893341045L;
    /**
     * ERROR_FAILED(0, "系统内部错误"), INFO_SUCCESS(1, "操作成功")
     */
    private int               status;
    /**
     * 前台显示的提示信息
     */
    private String            showMessage;
    /**
     * 返回app端的的json数据字符串
     */
    private Object            data;

    // 异常情况
    public ResponseObj(int status, String showMessage) {
        this.status = status;
        this.showMessage = showMessage;
        if(null==this.data){
    	   this.data=new HashMap();
        }

    }

    // 正确情况
    public ResponseObj(int status, String showMessage, Object data) {
        this.status = status;
        this.showMessage = showMessage;
        this.data = data;
       

    }

    
     /** <p class="detail">
     * 功能：data拼写的json
     * </p>
     * @return    
     */
    public String toAppDataString() {
        if (null == this.data) {
            this.data = "";
        }
        if (data instanceof String) {
            if (StringUtils.isBlank(data.toString())) {
                this.data = "";
            }
        }
        return toDataString();
    }
    
     /** <p class="detail">
     * 功能：web端data拼写的json
     * </p>
     * @param request
     * @param response
     * @return    
     */
    public String toWebDataString(HttpServletRequest request, HttpServletResponse response) {

        String result = toDataString();
        //如果客户端传入了callBack变量说明该请求是jsonp跨域请求，则将数据包装成jsonp所需格式返回
        String callBackFunName = request.getParameter(ViewContants.JSONP_CALLBACK_FUN_NAME);
        setAccessContrlAllowOrigin(response);
        if (StringUtils.isNotBlank(callBackFunName)) {
            return callBackFunName + "(" + result + ")";
        }

        return result;
    }
    
     /** <p class="detail">
     * 功能：data由自己拼写的json串
     * </p>
     * @return    
     */
    private String toDataString() {
        StringBuilder builder = new StringBuilder("{\"status\":");
        builder.append(this.getStatus());
        builder.append(",\"showMessage\":\"");
        builder.append(this.getShowMessage());
        builder.append("\",\"data\":");
        builder.append(this.data);
        builder.append("}");
        return builder.toString();
    }

    
     /** <p class="detail">
     * 功能：app返回的json字符串
     * </p>
     * @return    
     */
    public String toAppJson() {
        if (null == this.data) {
            return getBlankDataJson();
        }
        if (data instanceof String && StringUtils.isBlank(data.toString())) {
            return getBlankDataJson();
        }
        return JsonUtil.toJson(this);
    }

    
     /** <p class="detail">
     * 功能：data数据为空时配合app的json结果
     * </p>
     * @return    
     */
    private String getBlankDataJson() {
        StringBuilder builder = new StringBuilder("{\"status\":");
        builder.append(this.getStatus());
        builder.append(",\"showMessage\":\"");
        builder.append(this.getShowMessage());
        builder.append("\",\"data\":{}}");
        return builder.toString();
    }

    /** <p class="detail">
    * 功能：web端返回的json字符串
    * </p>
    * @param request
    * @param response
    * @return    
    */
    public String toWebJson(HttpServletRequest request, HttpServletResponse response) {

        //如果客户端传入了callBack变量说明该请求是jsonp跨域请求，则将数据包装成jsonp所需格式返回
        String callBackFunName = request.getParameter(ViewContants.JSONP_CALLBACK_FUN_NAME);
        setAccessContrlAllowOrigin(response);
        if (StringUtils.isNotBlank(callBackFunName)) {
            return callBackFunName + "(" + JsonUtil.toJson(this) + ")";
        }

        return JsonUtil.toJson(this);
    }

    /**
     * <p class="detail">
     * 功能：设置浏览器允许跨域，*表示允许所有域名，可以指定允许接入的域名(逗号分隔)
     * </p>
     * @param response 请求响应
     */
    private void setAccessContrlAllowOrigin(HttpServletResponse response) {
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

    /** <p class="detail">
    * 功能：0:失败; 1:成功
    * </p>
    * @return    
    */
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setShowMessage(String showMessage) {
        this.showMessage = showMessage;
    }

    /**
     * @return the showMessage
     */
    public String getShowMessage() {
        return showMessage;
    }

    /** <p class="detail">
    * 功能：
    * </p>
    * @return 
    * @see java.lang.Object#toString() 
    */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ResponseObj[status=");
        builder.append(this.status).append(",").append("showMessage=");
        builder.append(this.showMessage).append(",").append("data=<");

        if (null != this.data) {
            builder.append(this.data.toString());
        }
        builder.append(">]");
        return builder.toString();
    }

}

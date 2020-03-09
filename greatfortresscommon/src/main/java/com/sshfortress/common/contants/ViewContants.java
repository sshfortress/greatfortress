package com.sshfortress.common.contants;
/**
 *
 */
public interface ViewContants {

    String LOGIN_HEADER_COOKIE     = "cookie";

    String LOGIN_TICKET_KEY        = "ticket";

    String LOGIN_TICKET_USER       = "LOGIN_TICKET_USER";

    /**
     * 信任COOKIE失效时间 单位秒
     */
    int    TRUST_COOKIE_TIME       = 2 * 60 * 60;

    String JSONP_CALLBACK_FUN_NAME = "callback";

    String NOT_LOG_SHOW            = "请重新登录";

    String NO_AUTHORITY            = "无权限";
    /**
     * 参数有误提示 
     */
    String PARAMETER_ERORY         = "参数有误";

    /** 
     * <p class="detail">
     * 功能：代表取消点赞、取消收藏等
     * </p>
     * @Fields CANCEL 
     */
    int    CANCEL                  = 0;

    /** 
     * <p class="detail">
     * 功能：代表已赞、已收藏等
     * </p>
     * @Fields CONFIRM 
     */
    int    CONFIRM                 = 1;

    String VIEW_STATUS             = "view_status";

    /** 
     * <p class="detail">
     * 功能：velocity  提示信息
     * </p>
     * @Fields VIEW_MESSAGE 
     */
    String VIEW_MESSAGE            = "view_message";

}

package com.sshfortress.common.enums;

import org.apache.commons.lang.StringUtils;



/** <p class="detail">
 * 功能：这里写类描述
 * </p>
 * @ClassName: RegCodeType 
 * @version V1.0  
 */
public enum RegCodeType {

    REGIST("REGIST", "注册"), 
    BACK_PASSWORD("BACK_PASSWORD", "找回密码"), 
    BIND_PHONE("BIND_PHONE", "绑定手机号"), 
    BIND_EMAIL("BIND_EMAIL", "绑定email"),
    FAST_LOGIN("FAST_LOGIN","快捷登录"),
    MODIFY_PHONE("MODIFY_PHONE","修改手机号");

    private String code;

    private String detail;

    RegCodeType(String code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    /**
     * 获得枚举
     * 
     * @param code
     * @return
     */
    public static RegCodeType getEnumByCode(String code) {

        for (RegCodeType activitie : RegCodeType.values()) {
            if (StringUtils.equals(code, activitie.getCode())) {
                return activitie;
            }
        }
        return null;
    }

    /**
     * @return code
     */

    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail
     *            the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }
}

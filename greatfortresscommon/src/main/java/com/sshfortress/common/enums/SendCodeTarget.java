package com.sshfortress.common.enums;

import org.apache.commons.lang.StringUtils;


/** <p class="detail">
 * 功能：这里写类描述
 * </p>
 * @ClassName: SendCodeTarget 
 * @version V1.0  
 */
public enum SendCodeTarget {
    
    EMAIL("EMAIL", "邮箱"), 
    PHONE("PHONE", "手机 ");

    private String code;

    private String detail;

    SendCodeTarget(String code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    /**
     * 获得枚举
     * 
     * @param code
     * @return
     */
    public static SendCodeTarget getEnumByCode(String code) {

        for (SendCodeTarget activitie : SendCodeTarget.values()) {
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

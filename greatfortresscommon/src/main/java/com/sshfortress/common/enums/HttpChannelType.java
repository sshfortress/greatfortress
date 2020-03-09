/*
 *  
 * This software is the confidential and proprietary information of
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 */
package com.sshfortress.common.enums;

import org.apache.commons.lang.StringUtils;

/** <p class="detail">
 * 功能：HTTP渠道类型
 * </p>
 * @ClassName: UserTypeEnums 
 * @version V1.0  
 */
public enum HttpChannelType {

    APP("APP", "APP"), 
    WEB("WEB", "WEB"), 
    H5("H5", "H5");

    private String code;

    private String detail;

    HttpChannelType(String code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    /**
     * 获得枚举
     * 
     * @param code
     * @return
     */
    public static HttpChannelType getEnumByCode(String code) {

        if (StringUtils.isNotBlank(code)) {
            for (HttpChannelType activitie : HttpChannelType.values()) {
                if (StringUtils.equals(code, activitie.getCode())) {
                    return activitie;
                }
            }
        }
        return null;
    }

    public static String getDetailByCode(String code) {

        if (StringUtils.isNotBlank(code)) {
            for (HttpChannelType activitie : HttpChannelType.values()) {
                if (StringUtils.equals(code, activitie.getCode())) {
                    return activitie.getDetail();
                }
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

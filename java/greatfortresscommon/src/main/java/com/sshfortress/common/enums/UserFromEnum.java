/*
 *  
 * This software is the confidential and proprietary information of
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 */
package com.sshfortress.common.enums;

/** <p class="detail">
 * 功能：userFrom 用户来源：10-App注册  20-网站注册  30-第三方平台 
 * </p>
 * @ClassName: UserFrom 
 * @version V1.0  
 */
public enum UserFromEnum {

    FROM_APP(10, "App注册 "), 
    FROM_NETWORK(20, "网站注册"), 
    FROM_THIRD_PLATFORM(30, "第三方平台 ");

    private Integer code;

    private String  detail;

    UserFromEnum(Integer code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    /**
     * 获得枚举
     * 
     * @param code
     * @return
     */
    public static UserFromEnum getEnumByCode(Integer code) {

        if (null != code) {
            for (UserFromEnum activitie : UserFromEnum.values()) {
                if (code.intValue() == activitie.getCode().intValue()) {
                    return activitie;
                }
            }
        }
        return null;
    }
    
    public static String getDetailByCode(Integer code) {

        if (null != code) {
            for (UserFromEnum activitie : UserFromEnum.values()) {
                if (code.intValue() == activitie.getCode().intValue()) {
                    return activitie.getDetail();
                }
            }
        }
        return null;
    }

    /**
     * @return code
     */

    public Integer getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(Integer code) {
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

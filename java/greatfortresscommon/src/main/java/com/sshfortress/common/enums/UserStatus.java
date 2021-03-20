/*
 *  
 * This software is the confidential and proprietary information of
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 */
package com.sshfortress.common.enums;

/** <p class="detail">
 * 功能：用户状态，1：正常，0：被禁用
 * </p>
 * @ClassName: UserStatus 
 * @version V1.0  
 */
public enum UserStatus {

    OPEN(1, "启用 "),
    VOID(0, "禁用");

    private int    code;

    private String detail;

    UserStatus(int code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    /**
     * 获得枚举
     * 
     * @param code
     * @return
     */
    public static UserStatus getEnumByCode(Integer code) {

        if (null != code) {
            for (UserStatus activitie : UserStatus.values()) {
                if (code.intValue() == activitie.getCode()) {
                    return activitie;
                }
            }
        }
        return null;
    }

    public static String getDetailByCode(Integer code) {

        if (null != code) {
            for (UserStatus activitie : UserStatus.values()) {
                if (code.intValue() == activitie.getCode()) {
                    return activitie.getDetail();
                }
            }
        }
        return null;
    }
    
    /**
     * @return code
     */

    public int getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(int code) {
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

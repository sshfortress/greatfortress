/*
 *  
 * This software is the confidential and proprietary information of
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 */
package com.sshfortress.common.enums;

/** <p class="detail">
 * 功能：
 * </p>
 * @ClassName: SexType 
 * @version V1.0  
 */
public enum SexType {

    SECRECY(0, "保密"), 
    MAN(1, "男 "), 
    WOMAN(2, "女");

    private int    code;

    private String detail;

    SexType(int code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    /**
     * 获得枚举
     * 
     * @param code
     * @return
     */
    public static SexType getEnumByCode(Integer code) {

        if (null != code) {
            for (SexType activitie : SexType.values()) {
                if (code.intValue() == activitie.getCode()) {
                    return activitie;
                }
            }
        }
        return null;
    }

    /**
     * 获得枚举
     * 
     * @param code
     * @return
     */
    public static String getDetailByCode(Integer code) {

        if (null != code) {
            for (SexType activitie : SexType.values()) {
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

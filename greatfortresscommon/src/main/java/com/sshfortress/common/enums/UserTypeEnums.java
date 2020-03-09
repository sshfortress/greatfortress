/*
 *  
 * This software is the confidential and proprietary information of
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 */
package com.sshfortress.common.enums;

import org.apache.commons.lang.StringUtils;

/** <p class="detail">
 * 功能：用户类型
 * </p>
 * @ClassName: UserTypeEnums 
 * @version V1.0  
 */
public enum UserTypeEnums {

	SYSTEM_MANAGER(0, "平台管理员"), 
	DEALER(1, "加盟商"), 
    MEMBER(2, "会员");
	 
    private int    code;

    private String detail;

    /** <p class="detail">
    * 功能：是否web端用户
    * </p>
    * @return    
    */
    public boolean isWebMember() {
        switch (this) {
            case SYSTEM_MANAGER:
            case DEALER:
                return true;
            default:
                return false;
        }
    }

    /** <p class="detail">
    * 功能：普通会员登录app
    * </p>
    * @return    
    */
    public boolean isAppMember() {
        switch (this) {
            case MEMBER:
                return true;
            default:
                return false;
        }
    }

    UserTypeEnums(int code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    /**
     * 获得枚举
     * 
     * @param code
     * @return
     */
    public static UserTypeEnums getEnumByCode(Integer code) {

        if (null != code) {
            for (UserTypeEnums activitie : UserTypeEnums.values()) {
                if (code == activitie.getCode()) {
                    return activitie;
                }
            }
        }
        return null;
    }

    public static String getDetailByCode(Integer code) {

        if (null != code) {
            for (UserTypeEnums activitie : UserTypeEnums.values()) {
                if (code == activitie.getCode()) {
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

    /** <p class="detail">
    * 功能：前端下拉菜单,仅显示用
    * </p>
    * @return 
    */
    public static String toSelectJsonShow() {
        StringBuilder builder = new StringBuilder("[");
        for (UserTypeEnums activitie : UserTypeEnums.values()) {
            builder.append("{");
            builder.append("\"name\"").append(":\"").append(activitie.name()).append("\",");
            builder.append("\"code\"").append(":\"").append(activitie.getCode()).append("\",");
            builder.append("\"detail\"").append(":\"").append(activitie.getDetail()).append("\"},");
        }
        String result = StringUtils.substring(builder.toString(), 0, builder.length() - 1);
        return result + "]";
    }

}

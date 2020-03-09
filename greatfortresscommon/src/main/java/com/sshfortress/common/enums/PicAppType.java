/*
 *  
 * This software is the confidential and proprietary information of
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 */
package com.sshfortress.common.enums;
/** <p class="detail">
 * 功能：应用图片路径类型
 * </p>
 * @ClassName: PicAppType 
 * @version V1.0  
 */
public enum PicAppType {

    USER_HEAD("/USER/HEAD/", "用户头像"),
    USER_MCHA("/USER/MCHA/", "用户机械");
    private String code;

    private String detail;

    PicAppType(String code, String detail) {
        this.code = code;
        this.detail = detail;
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
     * @return detail
     */

    public String getDetail() {
        return detail;
    }

    /**
     * @param detail
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

}

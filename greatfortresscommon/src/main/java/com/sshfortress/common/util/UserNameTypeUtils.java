/*
 *  
 * This software is the confidential and proprietary information of
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 */
package com.sshfortress.common.util;

import com.sshfortress.common.enums.UserNameType;

/** <p class="detail">
 * 功能：
 * </p>
 * @ClassName: UserNameTypeUtils 
 * @version V1.0  
 */
public class UserNameTypeUtils {

    /** <p class="detail">
    * 功能：获取账号类型
    * </p>
    * @param userName
    * @return    
    */
    public static UserNameType getTypeByUserName(String userName) {

        if (ValidateUtil.isValidMobile(userName)) {
            return UserNameType.PHONE;
        }

        if (ValidateUtil.isValidEmail(userName)) {
            return UserNameType.EMAIL;
        }

        return null;
    }
}

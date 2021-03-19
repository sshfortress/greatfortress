/*
 *  
 * This software is the confidential and proprietary information of
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 */
package com.sshfortress.common.util;

import java.util.ArrayList;
import java.util.List;

import com.sshfortress.common.enums.UserTypeEnums;

/** <p class="detail">
 * 功能：用户类型Util
 * </p>
 * @ClassName: UserTypesUtil 
 * @version V1.0  
 */
public class UserTypesUtil {

    /** <p class="detail">
    * 功能：web端登录者的用户类型
    * </p>
    * @return    
    */
    public static List<Integer> getWebuserTypes() {
        List<Integer> userTypes = new ArrayList<Integer>();

        userTypes.add(UserTypeEnums.SYSTEM_MANAGER.getCode());
        userTypes.add(UserTypeEnums.DEALER.getCode());
        return userTypes;
    }

    /** <p class="detail">
    * 功能：app端登录类型限制
    * </p>
    * @return    
    */
    public static List<Integer> getAppuserTypes() {
        List<Integer> userTypes = new ArrayList<Integer>();

        userTypes.add(UserTypeEnums.MEMBER.getCode());
        return userTypes;
    }
}

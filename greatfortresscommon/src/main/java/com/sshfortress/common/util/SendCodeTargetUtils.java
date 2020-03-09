/*
 *  
 * This software is the confidential and proprietary information of
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 */
package com.sshfortress.common.util;

import com.sshfortress.common.enums.SendCodeTarget;
import com.sshfortress.common.util.ValidateUtil;


/** <p class="detail">
 * 功能：这里写类描述
 * </p>
 * @ClassName: SendCodeTargetUtils 
 * @version V1.0  
 */
public class SendCodeTargetUtils {

    /** <p class="detail">
    * 功能：获取发送目标类型  手机还是email
    * </p>
    * @param userName
    * @return    
    */
    public static SendCodeTarget getTypeByTarget(String target) {

        if (ValidateUtil.isValidMobile(target)) {
            return SendCodeTarget.PHONE;
        }

        if (ValidateUtil.isValidEmail(target)) {
            return SendCodeTarget.EMAIL;
        }

        return null;
    }
}

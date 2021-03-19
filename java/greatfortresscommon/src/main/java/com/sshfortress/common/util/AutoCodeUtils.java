/*
 *  
 * This software is the confidential and proprietary information of
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 */
package com.sshfortress.common.util;

import java.util.Random;

import org.apache.commons.lang.StringUtils;

/** <p class="detail">
 * 功能：生成code工具类
 * </p>
 * @ClassName: AutoCodeUtils 
 * @version V1.0  
 */
public class AutoCodeUtils {

    /** <p class="detail">
    * 功能： 取charCount位随机数
    * </p>
    * @param charCount   随机数位数
    * @return    
    */
    public static String getRandNum(int charCount) {
        StringBuilder charValue = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < charCount; i++) {
            // 取大于等于0小于10的整数
            charValue.append(r.nextInt(10));
        }
        return charValue.toString();
    }

    /** <p class="detail">
    * 功能：生成邀请码  6位=头字母大写+5位数字
    * </p>
    * @return    
    */
    public static String getInvitationCode() {
        StringBuilder builder = new StringBuilder();
        // 随机头大写字母
        char c = (char) (int) (Math.random() * 26 + 97);
        builder.append(StringUtils.upperCase(String.valueOf(c)));
        // 5位随机数字
        builder.append(getRandNum(5));
        return builder.toString();
    }

}

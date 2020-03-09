/*
 *  
 * This software is the confidential and proprietary information of
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 */
package com.sshfortress.common.contants;

/** <p class="detail">
 * 功能：这里写类描述
 * </p>
 * @ClassName: RegCodeConstants 
 * @version V1.0  
 */
public interface RegCodeConstants {
	
	
	

    /** 
     * <p class="detail">
     * 功能：发送短信验证码并发锁
     * </p>
     * @Fields lockKey 
     */
    String smsLockKey   = "SEND_SMS_REGCODE_";

    /** 
     * <p class="detail">
     * 功能：发送邮件验证码并发锁
     * </p>
     * @Fields emailLockKey 
     */
    String emailLockKey = "SEND_EMAIL_REGCODE_";

    /** 
     * <p class="detail">
     * 功能：重发限制时间差 单位秒
     * </p>
     * @Fields repMaxSecond 
     */
    int    repMaxSecond = 50;

    /** 
     * <p class="detail">
     * 功能：默认情况下验证码失效时长5分钟
     * </p>
     * @Fields timeliness 
     */
    int    timeliness   = 5;
}

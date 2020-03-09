/**
 * winchance Inc.
 */
package com.sshfortress.common.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 天搜内部签名 签名（约定key的算法）
 * 
 * @version $Id: SignUtils.java, v 0.1 2014-6-4 下午01:05:55 kaneiqi Exp $
 */
public class SignUtils {

    private static final Log logger = LogFactory.getLog(SignUtils.class);

    /** 24bit 私钥KEY: TIANSOU-golf@#160314g%(^$} */
    private static String    key    = "TIANSOU-TS@#160314g%(^$}";
    
    private static TripleDES tripleDES;

    static {
        try {
            tripleDES = new TripleDES(key);
        } catch (UnsupportedEncodingException e) {
            logger.error("初始化签名对象异常", e);
        }
    }

    /**
     * <li>init-method 与afterPropertiesSet 都是在初始化bean的时候执行</li> <li>
     * 执行顺序是afterPropertiesSet 先执行，init-method 后执行</li> <li>必须实现
     * InitializingBean接口</li>
     * 
     * @throws Exception
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */

    /**
     * 加签
     * 
     * @param src
     * @return
     */
    public static String encrypt(String src) {
        try {
            return tripleDES.encrypt(StringUtils.trim(src));
        } catch (Exception ex) {
            logger.error(src + "签名异常", ex);
        }
        return "";
    }

    /**
     * 解签
     * 
     * @param src
     * @return
     */
    public static String decrypt(String src) {
    	if(StringUtil.isNullOrEmpty(src)){
    		return "";
    	}else{
    		try {
                return tripleDES.decrypt(StringUtils.trim(src));
            } catch (Exception ex) {
                logger.error(src + "解签异常", ex);
            }
    	}
        
        return "";
    }

}

/*
 *  
 * This software is the confidential and proprietary information of
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 */
package com.sshfortress.service.helper;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshfortress.common.config.DomainConfig;

/** <p class="detail">
 * 功能�?
 * </p>
 * @ClassName: ImagePathHelper 
 * @version V1.0  
 */
@Service("imagePathHelper")
public class ImagePathHelper {

	@Autowired
	DomainConfig domainConfig;
    
     /** <p class="detail">
     * 功能：静态图片地�?��放地�?
     * </p>
     * @return    
     */
    public String getImagePath() {

        String environment =domainConfig.VM_ENVIRONMENT;

        if (StringUtils.equalsIgnoreCase("win", environment)) {
            return domainConfig.GREATFORTRESSFRAMEWORK_STATIC_IMAGE_WIN;
        }
        return domainConfig.GREATFORTRESSFRAMEWORK_STATIC_IMAGE_LINUX;
    }
    public String getImagePathOnline() {
        return domainConfig.GREATFORTRESSFRAMEWORK_STATIC_IMAGEONLINE;
    }
    
    
}

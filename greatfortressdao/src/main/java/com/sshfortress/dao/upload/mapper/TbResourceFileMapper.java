package com.sshfortress.dao.upload.mapper;

import com.sshfortress.common.entity.TbResourceFile;

/**
 * <p class="detail">
 * 功能：添加资源文件的情况
 * </p>
 * @ClassName: TbResourceFileMapper 
 * @version V1.0  
 */

public interface TbResourceFileMapper {
    
    int insert(TbResourceFile record);

}
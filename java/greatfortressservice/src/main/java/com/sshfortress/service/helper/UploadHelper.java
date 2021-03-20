package com.sshfortress.service.helper;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service("uploadHelper")
public class UploadHelper {

    private static final Log logger = LogFactory.getLog(UploadHelper.class);

    
    public boolean uploadFile(MultipartFile file, String upload_path, String fileName) {

       
        String all_upload_path = new StringBuilder(upload_path).toString();
        System.out.println(all_upload_path);
        try {
        	
            File targetFileDir = new File(all_upload_path);

            if (!targetFileDir.exists()) {
                logger.info("首次创建目录:" + all_upload_path);
                targetFileDir.mkdirs();
            }

            File targetFile = new File(all_upload_path, fileName);
          
            file.transferTo(targetFile);
            
            return true;
        } catch (Exception e) {
            logger.error("上传文件异常fileName:" + all_upload_path + fileName, e);
        }

        return false;
    }
}

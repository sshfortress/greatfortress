package com.sshfortress.service.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CompleteMultipartUploadRequest;
import com.aliyun.oss.model.CompleteMultipartUploadResult;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.InitiateMultipartUploadRequest;
import com.aliyun.oss.model.InitiateMultipartUploadResult;
import com.aliyun.oss.model.ListPartsRequest;
import com.aliyun.oss.model.PartETag;
import com.aliyun.oss.model.PartListing;
import com.aliyun.oss.model.PartSummary;
import com.aliyun.oss.model.UploadFileRequest;
import com.aliyun.oss.model.UploadFileResult;
import com.aliyun.oss.model.UploadPartRequest;
import com.aliyun.oss.model.UploadPartResult;
import com.sshfortress.common.entity.TbResourceFile;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.util.CommonUtils;
import com.sshfortress.common.util.FileUtil;
import com.sshfortress.common.util.OperationContextHolder;
import com.sshfortress.common.util.ResponseObj;
import com.sshfortress.common.util.StringUtil;
import com.sshfortress.dao.upload.mapper.TbResourceFileMapper;
import com.sshfortress.dao.user.mapper.SysUserInfoMapper;
import com.sshfortress.service.system.SysConfigService;

/** <p class="detail">
 * 功能：
 * </p>
 * @ClassName: UploadFile 
 * @version V1.0  
 */
@Component(value = "uploadFile")
public class UploadFileOss {
    private final Log        logger    = LogFactory.getLog(this.getClass());

    private static OSSClient ossClient = null;

    @Autowired
    SysConfigService sysConfigService;
    
    
    @Autowired
    TbResourceFileMapper tbResourceFileMapper;
    
    /**
     * <p class="detail">
     * 功能：分片文件上传
     * </p>
     * @param file  要上传的视频文件
     * @return
     */
    public ResponseObj uploadFile(MultipartFile multipartFile, String pathPreName,boolean b) {
    	long startTime=System.currentTimeMillis();
    	
    	CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
       // File tfile = fi.getStoreLocation();
        System.out.println(fi.getStoreLocation());
        File file=null;
        System.out.println(multipartFile.getOriginalFilename());
        if(!b && CommonUtils.checkPicFile(multipartFile.getOriginalFilename())){
//        try {
//			 //Thumbnails.of(tfile).scale(1f).outputQuality(0.25f).toFile(file);
//        	 file=new File(FileUtil.getFileUrl()+multipartFile.getOriginalFilename());
//			 //Thumbnails.of(multipartFile.getInputStream()).scale(1f).outputQuality(0.25f).toFile(file); 
//        } catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} 	
        	file = fi.getStoreLocation();
        }else{
        	file = fi.getStoreLocation();
        }
        
        String fileName = multipartFile.getOriginalFilename();
        //上传域名
        String endpoint = sysConfigService.getValue("endpoint");
        //上传keyId
        String accessKeyId = sysConfigService.getValue("accessKeyId");
        //上传keySecret
        String accessKeySecret = sysConfigService.getValue("accessKeySecret");
        //上传Bucket
        String bucketName = sysConfigService.getValue("bucketName");
        //上传key
        String key = pathPreName + "/" +StringUtil.getUUID()+fileName.substring(fileName.lastIndexOf("."), fileName.length());
        
        String url="http://"+bucketName+"."+endpoint+"/"+key;
        /*
         * Constructs a client instance with your account for accessing OSS
         */
        ClientConfiguration conf = new ClientConfiguration();
        conf.setIdleConnectionTime(1000);
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);

        boolean exists = ossClient.doesBucketExist(bucketName);
        if (!exists) {
            ossClient.createBucket(bucketName);
        }
        //设置Bucket权限为公开可读
        ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);

        try {
            /*
             * Claim a upload id firstly
             */
            String uploadId = claimUploadId(bucketName, key);

            /*
             * Calculate how many parts to be divided
             */
            final long partSize = 1 * 1024 * 1024L; // 5MB
            long fileLength = file.length();
            int partCount = (int) (fileLength / partSize);
            if (fileLength % partSize != 0) {
                partCount++;
            }
            if (partCount > 10000) {
                //最大切片数不能超过10000
                return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(),ViewShowEnums.ERROR_FAILED.getDetail());
            } else {
                logger.info("上传文件：" + file.getName() + "共切片" + partCount);
            }

            /*
             * Upload multiparts to your bucket
             */
            //            System.out.println("Begin to upload multiparts to OSS from a file\n");
            logger.info("开始上传");
            List<PartETag> partETags = Collections.synchronizedList(new ArrayList<PartETag>());
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            for (int i = 0; i < partCount; i++) {
                long startPos = i * partSize;
                long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
                if (!executorService.isShutdown()) {
                    executorService.execute(new PartUploader(file, startPos, curPartSize, i + 1,
                        uploadId, bucketName, key, partETags));
                }
            }

            /*
             * Waiting for all parts finished
             */
            if (!executorService.isShutdown()) {
                executorService.shutdown();
            }
            while (!executorService.isTerminated()) {
                try {
                    executorService.awaitTermination(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            /*
             * Verify whether all parts are finished
             */
            if (partETags.size() != partCount) {
                //                throw new IllegalStateException(
                //                    "Upload multiparts fail due to some parts are not finished yet");
                logger.error("上传失败，因为还有一些没上传完");
                 return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(),ViewShowEnums.ERROR_FAILED.getDetail());
            } else {
                //                System.out.println("Succeed to complete multiparts into an object named " + key
                //                                   + "\n");
                logger.info("所有切片全部上传成功，文件名：" + key);
            }

            /*
             * View all parts uploaded recently
             */
            listAllParts(uploadId, bucketName, key);

            /*
             * Complete to upload multiparts
             */
            completeMultipartUpload(uploadId, bucketName, key, partETags);

            /*
             * Fetch the object that newly created at the step below.
             */
            System.out.println("Fetching an object");
            ossClient.getObject(new GetObjectRequest(bucketName, key), file);

        } catch (OSSException oe) {
            logger.error("Caught an OSSException, which means your request made it to OSS, "
                         + "but was rejected with an error response for some reason.");
            logger.error("Error Message: " + oe.getMessage());
            logger.error("Error Code:       " + oe.getErrorCode());
            logger.error("Request ID:      " + oe.getRequestId());
            logger.error("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            logger.error("Caught an ClientException, which means the client encountered "
                         + "a serious internal problem while trying to communicate with OSS, "
                         + "such as not being able to access the network.");
            logger.error("Error Message: " + ce.getMessage());
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        
        long endTime=System.currentTimeMillis();//记录结束时间
        
        TbResourceFile trf=new TbResourceFile();
        trf.setSourceName(key);
        trf.setSourceUrl(url);
        trf.setSourceUserid(OperationContextHolder.getUserId()==null?0l:Long.valueOf(OperationContextHolder.getUserId()));
        trf.setSourceOldName(fileName);
        trf.setSourceSize(BigDecimal.valueOf((multipartFile.getSize())));
        trf.setSourceSuffix(fileName.substring(fileName.lastIndexOf("."), fileName.length()));
        trf.setSourceCreatetime(new Date());
        trf.setRemark("耗时"+(endTime-startTime)+"毫秒");
        tbResourceFileMapper.insert(trf);
        
        return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(), ViewShowEnums.INFO_SUCCESS.getDetail(),
            url);
    }

    private String claimUploadId(String bucketName, String key) {
        InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, key);
        InitiateMultipartUploadResult result = ossClient.initiateMultipartUpload(request);
        return result.getUploadId();
    }

    private class PartUploader implements Runnable {

        private File           localFile;
        private long           startPos;
        private List<PartETag> partETags;

        private long           partSize;
        private int            partNumber;
        private String         uploadId;
        private String         bucketName;
        private String         key;

        public PartUploader(File localFile, long startPos, long partSize, int partNumber,
                            String uploadId, String bucketName, String key, List<PartETag> partETags) {
            this.localFile = localFile;
            this.startPos = startPos;
            this.partSize = partSize;
            this.partNumber = partNumber;
            this.uploadId = uploadId;
            this.bucketName = bucketName;
            this.key = key;
            this.partETags = partETags;
        }

        @Override
        public void run() {
            InputStream instream = null;
            try {
                instream = new FileInputStream(this.localFile);
                instream.skip(this.startPos);

                UploadPartRequest uploadPartRequest = new UploadPartRequest();
                uploadPartRequest.setBucketName(bucketName);
                uploadPartRequest.setKey(key);
                uploadPartRequest.setUploadId(this.uploadId);
                uploadPartRequest.setInputStream(instream);
                uploadPartRequest.setPartSize(this.partSize);
                uploadPartRequest.setPartNumber(this.partNumber);

                UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
                System.out.println("Part#" + this.partNumber + " done\n");
                synchronized (partETags) {
                    partETags.add(uploadPartResult.getPartETag());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (instream != null) {
                    try {
                        instream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void listAllParts(String uploadId, String bucketName, String key) {
        logger.info("Listing all parts......");
        ListPartsRequest listPartsRequest = new ListPartsRequest(bucketName, key, uploadId);
        PartListing partListing = ossClient.listParts(listPartsRequest);

        int partCount = partListing.getParts().size();
        for (int i = 0; i < partCount; i++) {
            PartSummary partSummary = partListing.getParts().get(i);
            System.out.println("\tPart#" + partSummary.getPartNumber() + ", ETag="
                               + partSummary.getETag());
        }
        System.out.println();
    }

    private void completeMultipartUpload(String uploadId, String bucketName, String key,
                                         List<PartETag> partETags) {
        // Make part numbers in ascending order
        Collections.sort(partETags, new Comparator<PartETag>() {

            @Override
            public int compare(PartETag p1, PartETag p2) {
                return p1.getPartNumber() - p2.getPartNumber();
            }
        });

        logger.info("Completing to upload multiparts\n");
        CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest(
            bucketName, key, uploadId, partETags);
        ossClient.completeMultipartUpload(completeMultipartUploadRequest);
    }
    
    
    
    /**
     * <p class="detail">
     * 功能：简单的文件上传
     * </p>
     * @param uploadFile
     * @param key
     * @return
     */
    
    public  String samplesUpload(String uploadFile,String key ){
        
  	  //上传域名
       String endpoint = sysConfigService.getValue("endpoint");
       //上传keyId
       String accessKeyId = sysConfigService.getValue("accessKeyId");
       //上传keySecret
       String accessKeySecret = sysConfigService.getValue("accessKeySecret");
       //上传Bucket
       String bucketName = sysConfigService.getValue("bucketName");
       //上传key
       //String key = pathPreName + "/" +StringUtil.getUUID()+fileName.substring(fileName.lastIndexOf("."), fileName.length());
       String pathPreName="zbzx/";
       String url="http://"+bucketName+"."+endpoint+"/"+key;	 
  	 
 	 OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
 	 ossClient.setBucketAcl(bucketName,CannedAccessControlList.PublicRead);
      try {
          UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, key);
          // 待上传的本地文件
          uploadFileRequest.setUploadFile(uploadFile);
          // 设置并发下载数，默认1
          uploadFileRequest.setTaskNum(5);
          // 设置分片大小，默认100KB
          uploadFileRequest.setPartSize(1024 * 1024 * 1);
          // 开启断点续传，默认关闭
          uploadFileRequest.setEnableCheckpoint(true);
          
          UploadFileResult uploadResult = ossClient.uploadFile(uploadFileRequest);
          
          CompleteMultipartUploadResult multipartUploadResult = 
                  uploadResult.getMultipartUploadResult();
          System.out.println(multipartUploadResult.getETag());
          
         
      } catch (OSSException oe) {
          System.out.println("Caught an OSSException, which means your request made it to OSS, "
                  + "but was rejected with an error response for some reason.");
          System.out.println("Error Message: " + oe.getErrorCode());
          System.out.println("Error Code:       " + oe.getErrorCode());
          System.out.println("Request ID:      " + oe.getRequestId());
          System.out.println("Host ID:           " + oe.getHostId());
      } catch (ClientException ce) {
          System.out.println("Caught an ClientException, which means the client encountered "
                  + "a serious internal problem while trying to communicate with OSS, "
                  + "such as not being able to access the network.");
          System.out.println("Error Message: " + ce.getMessage());
      } catch (Throwable e) {
          e.printStackTrace();
      } finally {
          ossClient.shutdown();
      }
      return url;
      
 }
    
}

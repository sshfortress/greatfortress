package com.sshfortress.service.helper;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sshfortress.common.enums.PicAppType;
import com.sshfortress.common.enums.ViewShowEnums;
import com.sshfortress.common.util.CommonUtils;
import com.sshfortress.common.util.PrimaryKeyUtils;
import com.sshfortress.common.util.ResponseObj;


/** <p class="detail">
 * 功能：上传图片
 * </p>
 * @ClassName: UploadPicHelper 
 * @version V1.0  
 */
@Service("uploadPicHelper")
public class UploadPicHelper {

	private static final Log logger = LogFactory.getLog(UploadPicHelper.class);

	@Autowired
	private UploadHelper     uploadHelper;
	@Autowired
	private ImagePathHelper  imagePathHelper;

	

	/** <p class="detail">
	 * 功能：校验图片
	 * </p>
	 * @param file
	 * @return    
	 */
	public ResponseObj validator(MultipartFile file) {

		if (null == file || file.isEmpty()) {
			return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "图片不能为空");
		}

		// 源文件名
		String originFileName = file.getOriginalFilename();

		String suffix = CommonUtils.getFileSuffix(originFileName);

		if (!CommonUtils.checkPicFileSuffix(suffix)) {
			return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), originFileName
					+ "非图片类型");
		}

		if (file.getSize() > 1024 * 1024) {
			return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), originFileName
					+ "单张图片不能超过1MB");
		}

		return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
	}
	/**
	 * <p class="detail">
	 * 功能：校验文�?
	 * </p>
	 * @param file
	 * @return
	 */
	public ResponseObj validatorFile(MultipartFile file) {

		if (null == file || file.isEmpty()) {
			return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "文件不能为空");
		}

		// 源文件名
		String originFileName = file.getOriginalFilename();

		String suffix = CommonUtils.getFileSuffix(originFileName);

		if (!CommonUtils.checkFileFileSuffix(suffix)) {
			return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), originFileName
					+ "非文件类型");
		}

		/*  if (file.getSize() > 1024 * 1024) {
            return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), originFileName
                                                                           + "单张图片不能超过1MB");
        }
		 */
		return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail());
	}
	
	 /** <p class="detail">
	 * 功能：上传图�?
	 * </p>
	 * @param file
	 * @param picAppType上传图片类型（用户头像等�?
	 * @param userId
	 * @return 图片访问地址   
	 */
	public ResponseObj uploadPic(MultipartFile file, HttpServletRequest request) {

		// 源文件名
		String originFileName = file.getOriginalFilename();

		String image_path = imagePathHelper.getImagePath();

		// 生成要保存上传的新文件名
		String fileName = originFileName;

		boolean flag = uploadHelper.uploadFile(file, image_path,
				fileName);

		if (!flag) {
			return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "上传失败");
		}

		String zsjyframework_static_imageonline = imagePathHelper.getImagePathOnline();

		String picUrl = zsjyframework_static_imageonline  + fileName;

		return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail(), picUrl);

	}
	/**
	 * <p class="detail">
	 * 功能：上传文�?
	 * </p>
	 * @param file
	 * @param picAppType
	 * @param appCode
	 * @param userId
	 * @return
	 */
	public ResponseObj uploadFile(MultipartFile file, PicAppType picAppType, String appCode,
			String userId) {

		// 源文件名
		String originFileName = file.getOriginalFilename();

		String suffix = CommonUtils.getFileSuffix(originFileName);

		String image_path = imagePathHelper.getImagePath();

		// 生成要保存上传的新文件名
		String fileName = new StringBuilder(userId).append("_")
				.append(PrimaryKeyUtils.getUniqueKey()).append(".").append(suffix).toString();

		boolean flag = uploadHelper.uploadFile(file, image_path,
				fileName);

		if (!flag) {
			return new ResponseObj(ViewShowEnums.ERROR_FAILED.getStatus(), "上传文件失败");
		}

		String zsjyframework_static_imageonline = imagePathHelper.getImagePathOnline();

		String picUrl = zsjyframework_static_imageonline + picAppType.getCode() + fileName;

		return new ResponseObj(ViewShowEnums.INFO_SUCCESS.getStatus(),
				ViewShowEnums.INFO_SUCCESS.getDetail(), picUrl);

	}

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);
		return list.contains(type);
	}
}

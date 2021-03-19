
package com.sshfortress.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

/** <p class="detail">
 * 功能：这里写类描述
 * </p>
 * @ClassName: CommonUtils 
 * @version V1.0  
 */
public class CommonUtils {

    /** 文件后缀分割符（占用1个字符） */
    public static final String FILE_SUFFIX_SPLIT_MARK = "\\.";

    /** excel后缀 */
    public static final String EXCELX_FILE_SUFFIX     = "xlsx";

    /** excel后缀 */
    public static final String EXCEL_FILE_SUFFIX      = "xls";

    /** jpg后缀 */
    public static final String JPG_FILE_SUFFIX        = "jpg";

    /** jpeg后缀 */
    public static final String JPEG_FILE_SUFFIX       = "jpeg";

    /** png后缀 */
    public static final String PNG_FILE_SUFFIX        = "png";

    /** ico后缀 */
    public static final String ICO_FILE_SUFFIX        = "ico";

    /** ico后缀 */
    public static final String GIF_FILE_SUFFIX        = "gif";
    
    public static final String DOC_FILE_SUFFIX        = "doc";
    
    public static final String DOCX_FILE_SUFFIX        = "docx";
    
    public static final String PPT_FILE_SUFFIX        = "ppt";
    
    public static final String PPTX_FILE_SUFFIX        = "pptx";
    
    public static final String PDF_FILE_SUFFIX        = "pdf";

    public static List<String> PIC_FILE_SUFFIXS;
    
    public static List<String> FILE_FILE_SUFFIXS;

    static {
        PIC_FILE_SUFFIXS = new ArrayList<String>();
        PIC_FILE_SUFFIXS.add(JPG_FILE_SUFFIX);
        PIC_FILE_SUFFIXS.add(JPEG_FILE_SUFFIX);
        PIC_FILE_SUFFIXS.add(PNG_FILE_SUFFIX);
        PIC_FILE_SUFFIXS.add(ICO_FILE_SUFFIX);
        PIC_FILE_SUFFIXS.add(GIF_FILE_SUFFIX);
        FILE_FILE_SUFFIXS = new ArrayList<String>();
        FILE_FILE_SUFFIXS.add(DOC_FILE_SUFFIX);
        FILE_FILE_SUFFIXS.add(DOCX_FILE_SUFFIX);
        FILE_FILE_SUFFIXS.add(PPT_FILE_SUFFIX);
        FILE_FILE_SUFFIXS.add(PPTX_FILE_SUFFIX);
        FILE_FILE_SUFFIXS.add(PDF_FILE_SUFFIX);
    }

    /**
     * 校验图片文件类型
     * 
     * @param filename
     * @return
     */
    public static boolean checkPicFile(String filename) {

        if (PIC_FILE_SUFFIXS.contains(getFileSuffix(filename))) {
            return true;
        }

        return false;
    }
    public static boolean checkFileFile(String filename) {

        if (FILE_FILE_SUFFIXS.contains(getFileSuffix(filename))) {
            return true;
        }

        return false;
    }
    /**
     * 校验图片文件后缀类型
     * 
     * @param filename
     * @return
     */
    public static boolean checkPicFileSuffix(String suffix) {

        if (PIC_FILE_SUFFIXS.contains(suffix)) {
            return true;
        }

        return false;
    }
    public static boolean checkFileFileSuffix(String suffix) {

        if (FILE_FILE_SUFFIXS.contains(suffix)) {
            return true;
        }

        return false;
    }
    /**
     * unicode码转汉子
     * 
     * @param unicode
     * @return
     */
    public static String convertUnicode(String unicode) {

        if (StringUtils.isBlank(unicode)) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;

        while ((i = unicode.indexOf("\\u", pos)) != -1) {
            sb.append(unicode.substring(pos, i));
            if (i + 5 < unicode.length()) {
                pos = i + 6;
                sb.append((char) Integer.parseInt(unicode.substring(i + 2, i + 6), 16));
            }
        }

        return sb.toString();
    }

    /**
     * 获取拼装后的uri
     * 
     * @param params
     * @param url
     * @return
     */
    public static String appendUrl(Map<String, String> params, String url) {
        if (CollectionUtils.isEmpty(params) || StringUtils.isBlank(url)) {
            return null;
        }

        StringBuilder builder = new StringBuilder(url).append("?");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }

        return StringUtils.substring(builder.toString(), 0, builder.length() - 1);
    }

    /**
     * 数组是否存在该字符串
     * 
     * @param strs
     * @param parm
     * @return
     */
    public static boolean isExist(String[] strs, String parm) {
        if (strs != null) {
            for (String str : strs) {
                if (StringUtils.equals(str, parm)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 去掉最后一个指定字符
     * 
     * @param str
     * @param regex
     * @return
     */
    public static String delLastchar(String str, String regex) {
        if (StringUtils.isNotBlank(str)) {
            String lastChar = StringUtils.substring(str, str.length() - 1, str.length());
            if (StringUtils.equals(regex, lastChar)) {
                return StringUtils.substring(str, 0, str.length() - 1);
            }
        }
        return str;
    }

    /**
     * 校验excel文件类型
     * 
     * @param filename
     * @return
     */
    public static boolean checkExcelFile(String filename) {
        String suffix = getFileSuffix(filename);
        if (StringUtils.equalsIgnoreCase(EXCELX_FILE_SUFFIX, suffix)) {
            return true;
        }

        if (StringUtils.equalsIgnoreCase(EXCEL_FILE_SUFFIX, suffix)) {
            return true;
        }

        return false;
    }

    /**
     * 获取文件后缀名
     * 
     * @param filename
     * @return
     */
    public static String getFileSuffix(String filename) {

        if (StringUtils.isBlank(filename)) {
            return "";
        }

        String[] strs = filename.split(FILE_SUFFIX_SPLIT_MARK);
        if (strs.length < 2) {
            return "";
        }
        // hzxiaoshanwater.txt.xls
        return StringUtils.lowerCase(strs[strs.length - 1]);

    }

    /**
     * 数组中是否包含该元素
     * 
     * @param strs
     * @param str
     * @return
     */
    public static boolean contain(String[] strs, String str) {

        if (strs == null || StringUtils.isBlank(str)) {
            return false;
        }
        // 循环查找字符串数组中的每个字符串中是否包含所有查找的内容
        for (int i = 0; i < strs.length; i++) {
            if (StringUtils.equalsIgnoreCase(strs[i], str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 取目标字符前两位
     * 
     * @param order
     * @return
     */
    public static String beforeTwoLine(String order) {
        if (StringUtils.isBlank(order) || StringUtils.trim(order).length() < 2) {
            return null;
        }

        return StringUtils.substring(StringUtils.trim(order), 0, 2).toUpperCase();
    }

    /**
     * 拼接成三位 001-999 超出不减
     * 
     * @param order
     * @return
     */
    public static String concatThreeLine(int order) {

        String str = String.valueOf(order);

        return getThreeLine(str);

    }

    /**
     * 截取后三位或拼接成三位 001-999 超出不减
     * 
     * @param order
     * @return
     */
    public static String getThreeLine(String str) {

        if (StringUtils.isBlank(str)) {
            return null;
        }
        str = StringUtils.trim(str);

        StringBuilder builder = new StringBuilder();

        if (str.length() == 1) {
            builder.append("00").append(str);
            return builder.toString();
        }
        if (str.length() == 2) {
            builder.append("0").append(str);
            return builder.toString();
        }
        if (str.length() > 3) {
            return StringUtils.substring(str, str.length() - 3);
        }
        return str;

    }
    
    
    /**
     * 判断是否是手机端的请求
     * @param s
     * @return
     */
    public static boolean isApp(String s){    	
    	 String[] deviceArray = new String[]{"android","mac os","windows phone"};	    	
         for(int i=0;i<deviceArray.length;i++){
             if(s.indexOf(deviceArray[i])>0){
                 return true;
             }
         }	    	
    	return false;
    }
    
}

package com.sshfortress.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p class="detail">
 * 功能：后台通用校验类，提供数值常规通用校验
 * </p>
 * @ClassName: Validation 
 * @version V1.0  
 */
public class ValidationComm {

	/**
	 * <p class="detail">
	 * 功能：判断是否是移动手机号码
	 * </p>
	 * @param value 待判断的值
	 * @return true：是移动手机号，false：非移动手机号
	 */
	public static boolean isCellphone(String value){
		Pattern p = Pattern.compile("^1(3|4|5|7|8)\\d{9}$");
		Matcher m = p.matcher(value);
		return m.matches();
	}
	/**
	 * <p class="detail">
	 * 功能：判断是否是座机号码
	 * </p>
	 * @param value 待判断的值 
	 * @return true：是座机号，false：非座机号
	 */
	public static boolean isTelephone(String value){
        Pattern p = Pattern.compile("([0-9]{3,4}-)?[0-9]{7,8}");
        Matcher m = p.matcher(value);
        return m.matches();
	}
	/**
	 * <p class="detail">
	 * 功能：判断是否是电话号码(包含移动手机号码和座机号码)
	 * </p>
	 * @param value 待判断的值 
	 * @return true：是电话号码，false：非电话号码
	 */
	public static boolean isPhone(String value){
		Pattern p = Pattern.compile("^1(3|4|5|7|8)\\d{9}$");
		Matcher m = p.matcher(value);
		Pattern p2 = Pattern.compile("([0-9]{3,4}-)?[0-9]{7,8}");
	    Matcher m2 = p2.matcher(value);
		return m.matches()||m2.matches();
	}
	/**
	 * <p class="detail">
	 * 功能：判断是否是空值（空值情况包括值为null或""）
	 * </p>
	 * @param value 待判断的值
	 * @return true：值为空，false：值为非空
	 */
	public static boolean isEmpty(String value){
		boolean result=false;
		if(value != null && value.equals("")){
			result=true;
		}else{
			result=false;
		}
		return result;
	}
	/**
	 * <p class="detail">
	 * 功能：判断是否是邮箱
	 * </p>
	 * @param value 待判断的值
	 * @return true：是邮箱，false：不是邮箱
	 */
	public static boolean isEmail(String value) {  
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");  
        Matcher m = pattern.matcher(value);  
        return m.matches();
    }
	/**
	 * <p class="detail">
	 * 功能：判断是否是整数
	 * </p>
	 * @param value 待判断的值
	 * @return true：是数字，false：不是数字
	 */
	public static boolean isNumeric(String value) {  
        Pattern pattern = Pattern.compile("[0-9]*");  
        if(value==null || "".equals(value)){
        	return false;
        }else{
	        Matcher isNum = pattern.matcher(value);  
	        if (isNum.matches()) {  
	            return true;  
	        }else {  
	            return false;  
	        }
        }
    }
	
    /**  
	 * <p class="detail">
	 * 功能：判断是否是日期格式
	 * </p>
	 * @param value 待判断的值
	 * @return true：是日期，false：不是日期
	 */
    public static boolean isDataFormat(String value){
     boolean flag=false;
     //String regxStr="[1-9][0-9]{3}-[0-1][0-2]-((0[1-9])|([12][0-9])|(3[01]))";
     String regxStr="^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
     Pattern pattern1=Pattern.compile(regxStr);
     Matcher isNo=pattern1.matcher(value);
     if(isNo.matches()){
      flag=true;
     }
     return flag;
    }
 
	/**
	 * <p class="detail">
	 * 功能：判断是否是身份证号码
	 * </p>
	 * @param value 待判断的值
	 * @return true：是身份证号码，false：不是身份证号码
     * @throws ParseException 
     * @throws NumberFormatException 
	 */
	public static boolean isIdentityCardNo(String value) throws NumberFormatException, ParseException{
        String[] ValCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4",  
                "3", "2" };  
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",  
                "9", "10", "5", "8", "4", "2" };  
        String Ai = ""; 
        // ================ 号码的长度 15位或18位 ================  
        if (value.length() != 15 && value.length() != 18) {  
            return false;  
        }  
        // =======================(end)========================  
 
        // ================ 数字 除最后以为都为数字 ================  
        if (value.length() == 18) {  
            Ai = value.substring(0, 17);  
        } else if (value.length() == 15) {  
            Ai = value.substring(0, 6) + "19" + value.substring(6, 15);  
        }  
        if (isNumeric(Ai) == false) {  
            return false;  
        }  
        // =======================(end)========================  
 
        // ================ 出生年月是否有效 ================  
        String strYear = Ai.substring(6, 10);// 年份  
        String strMonth = Ai.substring(10, 12);// 月份  
        String strDay = Ai.substring(12, 14);// 月份  
        if (isDataFormat(strYear + "-" + strMonth + "-" + strDay) == false) {  
            return false;  
        }  
        GregorianCalendar gc = new GregorianCalendar();  
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");  
        if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150 
                || (gc.getTime().getTime() - s.parse(  
                        strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {  
            return false;  
        }  
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {  
            return false;  
        }  
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {  
            return false; 
        }
        Hashtable<String,String> h = getAreaCode();  
        if (h.get(Ai.substring(0, 2)) == null) {  
            return false;  
        }  
        // ==============================================  
 
        // ================ 判断最后一位的值 ================  
        int TotalmulAiWi = 0;  
        for (int i = 0; i < 17; i++) {  
            TotalmulAiWi = TotalmulAiWi  
                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))  
                    * Integer.parseInt(Wi[i]);  
        }  
        int modValue = TotalmulAiWi % 11;  
        String strVerifyCode = ValCodeArr[modValue];  
        Ai = Ai + strVerifyCode;  
 
        if (value.length() == 18) {  
             if (Ai.equals(value) == false) {  
                 return false;  
             }  
         } else {  
             return false;  
         }  
         // =====================(end)=====================  
        return true;
	}
	/**
	 * <p class="detail">
	 * 功能：设置地区编码
	 * </p>
     * @throws ParseException 
     * @throws NumberFormatException 
	 */
	 private static Hashtable<String,String> getAreaCode() {  
         Hashtable<String,String> hashtable = new Hashtable<String,String>();  
         hashtable.put("11", "北京");  
         hashtable.put("12", "天津");  
         hashtable.put("13", "河北");  
         hashtable.put("14", "山西");  
         hashtable.put("15", "内蒙古");  
         hashtable.put("21", "辽宁");  
         hashtable.put("22", "吉林");  
         hashtable.put("23", "黑龙江");  
         hashtable.put("31", "上海");  
         hashtable.put("32", "江苏");  
         hashtable.put("33", "浙江");  
         hashtable.put("34", "安徽");  
         hashtable.put("35", "福建");  
         hashtable.put("36", "江西");  
         hashtable.put("37", "山东");  
         hashtable.put("41", "河南");  
         hashtable.put("42", "湖北");  
         hashtable.put("43", "湖南");  
         hashtable.put("44", "广东");  
         hashtable.put("45", "广西");  
         hashtable.put("46", "海南");  
         hashtable.put("50", "重庆");  
         hashtable.put("51", "四川");  
         hashtable.put("52", "贵州");  
         hashtable.put("53", "云南");  
         hashtable.put("54", "西藏"); 
         hashtable.put("61", "陕西");  
         hashtable.put("62", "甘肃");  
         hashtable.put("63", "青海");  
         hashtable.put("64", "宁夏");  
         hashtable.put("65", "新疆");  
         hashtable.put("71", "台湾");  
         hashtable.put("81", "香港");  
         hashtable.put("82", "澳门");  
         hashtable.put("91", "国外");  
         return hashtable;  
     } 
	 
	 public static boolean checkPassword(String password) {   
	        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9a-zA-Z]{6,12}";   
	        return Pattern.matches(regex, password);   
	    } 
	 
	 public static void main(String[] args){
			System.out.println(new ValidationComm().checkPassword("aaaaaaaa1"));
	 }
}

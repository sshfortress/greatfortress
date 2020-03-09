package com.sshfortress.common.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

/**
 * 
 * <p class="detail">
 * 功能：工具类，将中文转成拼音输出
 * </p>
 * 
 * @ClassName: Pinyin4j
 * @version V1.0
 */
public class Pinyin4j {

	public static void main(String[] args) throws Exception {
		System.out.println(getPYName("*xxx/x--尕^_^尕聚O(∩_∩)O哈哈~划%@!`？算--xxx", false, true));
		System.out.println(getPYName("*2017小学1期别", false, true));
	}

	/**
	 * 
	 * <p class="detail">
	 * 功能：中文转拼音
	 * </p>
	 * 
	 * @param cnName
	 *            中文
	 * @return
	 * @throws Exception
	 */
	public static String getPYName(String cnName, boolean uppercase, boolean getFirstLetter) throws Exception {
		try {
			String result = "";
			HanyuPinyinOutputFormat fmt = new HanyuPinyinOutputFormat();
			fmt.setToneType((HanyuPinyinToneType.WITHOUT_TONE));// 去声调 1、2、3、4
			fmt.setCaseType(uppercase?HanyuPinyinCaseType.UPPERCASE:HanyuPinyinCaseType.LOWERCASE);
			cnName = cnName.trim();// 去除省市区和空格等字
			for (int i = 0; i < cnName.length(); i++) {
				char charInput = cnName.charAt(i);
				if(charInput >= 0x4e00 && charInput <= 0x9fbb){//如果不是字母.汉字区间[0x4e00, 0x9fbb]
					// 考虑到多音字的情况，返回的是数组
					String[] strArray = PinyinHelper.toHanyuPinyinStringArray(charInput, fmt);
					if(!getFirstLetter){
						result += strArray[0];
					}else{
						result += strArray[0].substring(0, 1);
					}
				}else{
					result += charInput;
				}
			}
			return result;
		} catch (Exception e) {
			throw e;
		}
	}
}


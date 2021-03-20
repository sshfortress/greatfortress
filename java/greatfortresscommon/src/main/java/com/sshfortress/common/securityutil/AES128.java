package com.sshfortress.common.securityutil;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 * <p>
 * 功能：AES128位对称加密类，该类参数需要16位字符串秘钥及待加/解密字符串
 * <br/>参考文章：
 * http://blog.csdn.net/coyote1994/article/details/52368921
 * <br/>http://jueyue.iteye.com/blog/1830792
 * </p>
 * @ClassName: AES128
 * @version V1.0  
 */
public class AES128 {
	/*
	 * 定义一个初始向量，需要与iOS端的统一，使用CBC模式，需要一个向量iv，可增加加密算法的强度    
	 */
	private static final String IV_STRING = "16-Bytes--String";
	public static final String KEY = "w1e2i3s4i5d6o7m8";
	/**
	 * <p class="detail">
	 * 功能：AES128加密方法
	 * </p>
	 * @param content 待加密内容
	 * @param key 加密密钥（16位字符串）
	 * @return 加密后的字符串（结果已经过base64加密）
	 * @throws Exception
	 */
	public static String encryptAES(String content, String key)
			throws Exception {
		byte[] byteContent = content.getBytes("UTF-8");
		// 注意，为了能与 iOS 统一
		// 这里的 key 不可以使用 KeyGenerator、SecureRandom、SecretKey 生成
		byte[] enCodeFormat = key.getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
		byte[] initParam = IV_STRING.getBytes();
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
		// 指定加密的算法、工作模式和填充方式
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
		byte[] encryptedBytes = cipher.doFinal(byteContent);
		// 同样对加密后数据进行 base64 编码
		return Base64.encodeBase64String(encryptedBytes);
	}

	/**
	 * <p class="detail">
	 * 功能：解密函数
	 * </p>
	 * @param content 待解密字符串
	 * @param key 解密秘钥（16位字符串，需要与加密字符串一致）
	 * @return
	 * @throws Exception
	 */
	public static String decryptAES(String content, String key)
			throws Exception {
		// 先base64 解码
		byte[] encryptedBytes = Base64.decodeBase64(content);
		byte[] enCodeFormat = key.getBytes();
		SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, "AES");
		byte[] initParam = IV_STRING.getBytes();//使用CBC模式，需要一个向量iv，可增加加密算法的强度    
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
		byte[] result = cipher.doFinal(encryptedBytes);
		return new String(result, "UTF-8");
	}

	/*
	 * 测试函数
	 */
	public static void main(String[] args) throws Exception {
		String content="01234567891";
		String key="7t3e506j9z10xbd4";//必须为16位		
		//String deContent="Z2pVoa509Z0OYKLRseCek7abpYVbFbKETdJoQI4Kv28=";
		String deKey="7t3e506j9z10xbd4";
		String enstring = encryptAES(content, key);
		String destring = decryptAES(enstring,deKey);
		System.out.println("原始字符串："+content);
		System.out.println("加密后字符串："+enstring);
		System.out.println("长度："+enstring.length());
		//System.out.println("待解密字符串："+deContent);
		System.out.println("解密后字符串："+destring);
	}
}

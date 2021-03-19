/*
 *  
 * This software is the confidential and proprietary information of
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 */
package com.sshfortress.common.util;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/** <p class="detail">
 * 功能：用于渲染Json前端 枚举的下拉菜单
 * 备注：仅支持枚举参数名 为 code、detail的枚举
 * </p>
 * @ClassName: ReflectUtils 
 * @version V1.0  
 */
public class ReflectUtils {

    private static final Log logger = LogFactory.getLog(ReflectUtils.class);

    
    
    
    /**
     * <p class="detail">
     * 功能：获取属性值
     * </p>
     * @param obj	目标对象
     * @param name	属性名
     * @return
     */
    public static Object getProperty(Object obj, String name){
		if(obj != null){
			Class<?> clazz = obj.getClass();
			while(clazz != null){
				Field field = null;
				try {
					field = clazz.getDeclaredField(name);
				} catch (Exception e) {
					clazz = clazz.getSuperclass();
					continue;
				}
				try {
					field.setAccessible(true);
					return field.get(obj);
				} catch (Exception e) {
					return null;
				}finally{
					field.setAccessible(false);
				}
			}
		}
		return null;
	}
    
    /**
     * <p class="detail">
     * 功能：设置属性值
     * </p>
     * @param obj 目标对象
     * @param name	 属性名
     * @param value  属性值(可为空)，当属性类型不一致，目标属性为基本类型且该参数为字符串则进行转化（转化失败返回false）
     * @return
     */
	public static boolean setProperty(Object obj, String name, Object value){
		if(obj != null){
			Class<?> clazz = obj.getClass();
			while(clazz != null){
				Field field = null;
				try {
					field = clazz.getDeclaredField(name);
				} catch (Exception e) {
					clazz = clazz.getSuperclass();
					continue;
				}
				try {
					Class<?> type = field.getType();
					if(type.isPrimitive() == true && value != null){
						if(value instanceof String){
							if(type.equals(int.class) == true){
								value = Integer.parseInt((String)value);
							}else if(type.equals(double.class) == true){
								value = Double.parseDouble((String)value);
							}else if(type.equals(boolean.class) == true){
								value = Boolean.parseBoolean((String)value);
							}else if(type.equals(long.class) == true){
								value = Long.parseLong((String)value);
							}else if(type.equals(byte.class) == true){
								value = Byte.parseByte((String)value);
							}else if(type.equals(char.class) == true){
								value = Character.valueOf(((String)value).charAt(0));
							}else if(type.equals(float.class) == true){
								value = Float.parseFloat((String)value);
							}else if(type.equals(short.class) == true){
								value = Short.parseShort((String)value);
							}
						}
						field.setAccessible(true);
						field.set(obj, value);
						field.setAccessible(false);
					}
					if(value == null || type.equals(value.getClass()) == true){
						field.setAccessible(true);
						field.set(obj, value);
						field.setAccessible(false);
					}
					return true;
				} catch (Exception e) {
					return false;
				}
			}
		}
		return false;
	}
	/**
	 * <p class="detail">
	 * 功能：获取当前方法的全路径
	 * </p>
	 * @return
	 */
	public static String getCurrentClassMethodName(){
		return Thread.currentThread().getStackTrace()[2].getClassName()+"."+Thread.currentThread().getStackTrace()[2].getMethodName();
	}
	
}

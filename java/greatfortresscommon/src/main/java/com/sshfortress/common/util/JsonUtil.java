package com.sshfortress.common.util;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {  
  
    private static final String DEFAULT_DATE_FORMAT="yyyy-MM-dd HH:mm:ss";  
    private static final ObjectMapper mapper;  
  
    static {  
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);  
        mapper = new ObjectMapper();  
        mapper.setDateFormat(dateFormat);  
    }  
      
    public static String toJson(Object obj) {  
        try {  
            return mapper.writeValueAsString(obj);  
        } catch (Exception e) {  
            throw new RuntimeException("转换json字符失败!");  
        }  
    }  
      
    public static <T> T toObject(String jsonStr, Class<T> valueType) {
		try {
			return mapper.readValue(jsonStr, valueType);
		} catch (Exception e) {
			throw new RuntimeException("将json字符转换为对象时失败!");  
		}

	}
    
    
    /**
	 * json数组转List
	 * @param jsonStr
	 * @param valueTypeRef
	 * @return
	 */
	public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef){

		try {
			return mapper.readValue(jsonStr, valueTypeRef);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
}
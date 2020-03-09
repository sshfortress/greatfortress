package com.sshfortress.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshfortress.common.beans.SysConfig;
import com.sshfortress.common.beans.SysConfigKeyValue;
import com.sshfortress.common.contants.SystemContants;
import com.sshfortress.dao.system.mapper.SysConfigMapper;


/** <p class="detail">
 * 鍔熻兘锛氳繖閲屽啓绫绘弿杩�
 * </p>
 * @ClassName: SysConfigService 
 * @version V1.0  
 */
@Service("sysConfigService")
public class SysConfigService {

   private static final Log                  logger             = LogFactory.getLog(SysConfigService.class);
 
    private Map<String, String>               SYS_CONFIG_MAP     = new ConcurrentHashMap<String, String>();
	
    @Autowired
    private SysConfigMapper sysConfigMapper;
	
    
   
    private void getGroupCode(String ckey) {

        String lock = SystemContants.SYSTEM_CONFIG_KEY_LOCK + "_" + ckey;

        synchronized (lock.intern()) {

            String value = SYS_CONFIG_MAP.get(ckey);

            if (StringUtils.isBlank(value)) {
            	Map<String,Object> map = new HashMap<String,Object>();
            	map.put("ckey", ckey);
                String groupCode = sysConfigMapper.selectGroupCodeByCkey(map);
                if (StringUtils.isNotBlank(groupCode)) {
                	Map<String,Object> params = new HashMap<String,Object>();
                	params.put("groupCode", groupCode);
                    List<SysConfigKeyValue> keyValues =sysConfigMapper.selectKeyValueByGroupCode(params);
                    if (CollectionUtils.isNotEmpty(keyValues)) {
                        
                        for (SysConfigKeyValue KeyValue : keyValues) {
                            String key = KeyValue.getCkey();
                            SYS_CONFIG_MAP.put(key, KeyValue.getCvalue());
                        }
                    }
                }
            }
        }

    }

    public String getValue(String ckey) {
        if (StringUtils.isBlank(ckey)) {
            return null;
        }
        String key =ckey;
        String value = SYS_CONFIG_MAP.get(key);
        if (StringUtils.isBlank(value)) {
            getGroupCode(ckey);
        }
        return SYS_CONFIG_MAP.get(key);
    }

     
  
    public void updateValue( String ckey, String value) {
         SYS_CONFIG_MAP.put(ckey, value);
     }
     
   
    public void deleteValue(String ckey, String value) {
         SYS_CONFIG_MAP.remove(ckey);
     }
 
    /** 更新配置的值  */
    public int updateCvalueByCkey (SysConfig config){
    	int count=sysConfigMapper.updateCvalueByCkey(config);
    	return count;
    }
    
    /** 查询配置的值  */
    public String getCvalueByCkey(String ckey){
    	String str=sysConfigMapper.getCvalueByCkey(ckey);
    	return str;
    }
}

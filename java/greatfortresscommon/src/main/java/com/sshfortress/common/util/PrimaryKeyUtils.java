/**
 * 
 */
package com.sshfortress.common.util;

import java.util.Date;
import java.util.UUID;

/** <p class="detail">
 * 功能：生成18位主键规则
 * </p>
 * @ClassName: PrimaryKeyUtils 
 * @version V1.0  
 */
public class PrimaryKeyUtils {

    /**
     * 生成18位主键
     * 
     * @return yyMMddHH + 10位补全hashCode
     */
    public static String getPrimaryKey() {
        String uid = UUID.randomUUID().toString();
        long hashCodeV = uid.hashCode();

        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }

        String hashCodeStr = String.valueOf(hashCodeV);

        // 时间长度   8位
        StringBuilder primaryBuilder = new StringBuilder(DateUtilSelf.dtPrimary(new Date()));
        // hashCodeStr 长度小于等于10 如果小于10用随机数补全
        primaryBuilder.append(AutoCodeUtils.getRandNum(10 - hashCodeStr.length()));

        primaryBuilder.append(hashCodeStr);

        return primaryBuilder.toString();
    }

    /**
     * 生成27位唯一 17位毫秒+10位 uid.hashcode
     * 绝对的唯一(带有时间的 瞬时发生的唯一数据)
     * 
     * @return yyyyMMddHHmmssS + 10位补全hashCode
     */
    public static String getUniqueKey() {
        String uid = UUID.randomUUID().toString();
        long hashCodeV = uid.hashCode();

        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }

        String hashCodeStr = String.valueOf(hashCodeV);

        // 时间长度   yyyyMMddHHmmssS 14 + 3位
        StringBuilder primaryBuilder = new StringBuilder(DateUtilSelf.dtLongMillFormat(new Date()));
        // hashCodeStr 长度小于等于10 如果小于10用随机数补全
        primaryBuilder.append(AutoCodeUtils.getRandNum(10 - hashCodeStr.length()));

        primaryBuilder.append(hashCodeStr);

        return primaryBuilder.toString();
    }

}

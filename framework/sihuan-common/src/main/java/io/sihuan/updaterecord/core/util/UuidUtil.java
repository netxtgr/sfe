/*

 */

package io.sihuan.updaterecord.core.util;

import java.util.UUID;

/**
 * UUID工具类
 * @author liujiangtao
 * @date 2018-11-08
 */
public class UuidUtil {

    public static String getUuid(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }
    
}

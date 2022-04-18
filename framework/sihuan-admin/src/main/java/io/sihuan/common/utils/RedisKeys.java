/**
 * 
 *
 * 
 *
 * 版权所有，侵权必究！
 */

package io.sihuan.common.utils;

/**
 * Redis所有Keys
 *
 * @author liujiangtao@sihuanpharm.com
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }

    public static String getShiroSessionKey(String key){
        return "sessionid:" + key;
    }
}

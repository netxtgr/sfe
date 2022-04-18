/*

 */

package io.sihuan.updaterecord.core.util;

import io.sihuan.updaterecord.core.constant.UpdateRecordConstant;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Proxy;

/**
 * @author liujiangtao
 * @date 2019-11-16
 **/
public class PluginUtil {
    public static <T> T getTarget(Object target) {
        if (Proxy.isProxyClass(target.getClass())) {
            MetaObject metaObject = SystemMetaObject.forObject(target);
            return getTarget(metaObject.getValue(UpdateRecordConstant.H_TARGET));
        }
        return (T) target;
    }

}

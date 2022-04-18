/*

 */

package io.sihuan.updaterecord.core.util;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;

/**
 * 转换工具类
 *
 * @author liujiangtao
 * @date 2019-11-17
 **/
public class ConverterUtil {

    /**
     * 驼峰命名转下划线命名
     *
     * @param cameString
     * @return
     */
    public static String camelToUnderline(String cameString) {
        return CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE).convert(cameString);
    }
}

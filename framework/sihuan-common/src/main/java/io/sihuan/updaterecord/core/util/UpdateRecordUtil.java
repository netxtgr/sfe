/*

 */

package io.sihuan.updaterecord.core.util;

import io.sihuan.updaterecord.core.constant.UpdateRecordConstant;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 工具类
 *
 * @author liujiangtao
 * @date 2019-12-06
 **/
public class UpdateRecordUtil {

    /**
     * 将集合转换成字符串，使用分隔符串联
     *
     * @param list
     * @param separator
     * @return
     */
    public static String join(List<String> list, String separator) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        if (StringUtils.isEmpty(separator)) {
            separator = UpdateRecordConstant.SEPARATOR;
        }
        return StringUtils.join(list, separator);
    }

}

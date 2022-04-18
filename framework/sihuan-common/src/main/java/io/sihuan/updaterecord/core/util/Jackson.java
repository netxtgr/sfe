/*

 */

package io.sihuan.updaterecord.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Jackson序列化工具类
 *
 * @author liujiangtao
 * @date 2019-11-01
 **/
public class Jackson {

    /**
     * 键按自然顺序输出
     *
     * @param object
     * @return
     */
    public static String toJsonString(Object object) {
        return toJsonString(object, false);
    }

    /**
     * 键按自然顺序格式化输出
     *
     * @param object
     * @param prettyFormat
     * @return
     */
    public static String toJsonString(Object object, boolean prettyFormat) {
        if (object == null) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //格式化输出
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, prettyFormat);
            //键按自然顺序输出
            objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}

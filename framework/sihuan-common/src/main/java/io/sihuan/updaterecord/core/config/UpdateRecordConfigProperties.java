/*

 */

package io.sihuan.updaterecord.core.config;

import io.sihuan.updaterecord.core.constant.UpdateRecordConstant;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 修改记录配置属性
 *
 * @author liujiangtao
 * @date 2019-11-13
 **/
@Component
public class UpdateRecordConfigProperties {

    public static boolean DEBUG = false;
    public static String SERVER_NAME;
    public static String VERSION = "1.0";
    public static String ADD_MODEL_FORMAT = "%s 添加值：%s";
    public static String UPDATE_MODEL_FORMAT = "%s 由 %s 修改为 %s";
    public static String DELETE_MODEL_FORMAT = "删除%s";
    public static String TABLE_SEPARATOR = UpdateRecordConstant.DEFAULT_SEPARATOR;
    public static String COLUMN_SEPARATOR = UpdateRecordConstant.DEFAULT_SEPARATOR;
    public static String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    public static String DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_PATTERN);
    public static DateFormat DATE_TIME_FORMAT = new SimpleDateFormat(DATE_TIME_FORMAT_PATTERN);
    public static Set<String> INCLUDE_TABLES = new HashSet<>();
    public static Set<String> EXCLUDE_TABLES = new HashSet<>();
    public static Set<String> INCLUDE_COLUMNS = new HashSet<>();
    public static Set<String> EXCLUDE_COLUMNS = new HashSet<>();

}

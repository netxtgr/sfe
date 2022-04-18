/**
 * 版权所有，侵权必究！
 */

package io.sihuan.common.utils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日期处理
 *
 * @author ljt liujiangtao@sihuanpharm.com
 */
public class ImportUtils {
    public static String errorMsg(int row, String errorMsg) {
        StringBuilder sb = new StringBuilder();
        sb.append("第").append(row).append("行，").append(errorMsg);
        return sb.toString();
    }
    public static String errorMsg(int row, int col, String errorMsg) {
        StringBuilder sb = new StringBuilder();
        sb.append("第").append(row).append("行，");
        sb.append("第").append(col).append("列，").append(errorMsg);
        return sb.toString();
    }

    public static String success(int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("导入成功，成功条数：").append(size);
        return sb.toString();
    }
    public static String fail() {
        return "导入失败";
    }

    public static Map<String, Object> importResult(List<String> successLst, List<String> failLst) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", successLst);
        map.put("errorMsg", failLst);
        return map;
    }


}

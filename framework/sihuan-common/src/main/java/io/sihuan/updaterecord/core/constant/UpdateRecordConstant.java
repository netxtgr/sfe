/*

 */

package io.sihuan.updaterecord.core.constant;

import java.util.Arrays;
import java.util.List;

/**
 * 修改记录常量
 *
 * @author liujiangtao
 * @date 2019-11-13
 **/
public interface UpdateRecordConstant {

    /**
     * 表名称
     */
    String TABLE = "table";

    /**
     * 变更map key
     * table-0-foo_bar-id-1
     */
    String DIFF_MAP_KEY = "%s-%d-%s-%s-%s";

    /**
     * 分隔符
     */
    String SEPARATOR = "\r\n";


    String DELEGATE_MAPPEDSTATEMENT = "delegate.mappedStatement";

    String DELEGATE_BOUNDSQL = "delegate.boundSql";

    String H_TARGET = "h.target";

    String ET = "et";
    String VERSION_COLUMN = "version";

    String VERSION_DESC = "版本";

    String SELECT_SQL = "select %s from %s where %s = ?";

    List<String> DEFAULT_EXCLUDE_FIELDS = Arrays.asList("serialVersionUID");

    String DEFAULT_SEPARATOR = ",";
}

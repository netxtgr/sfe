/*

 */

package io.sihuan.updaterecord.api.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * 表信息对象
 *
 * @author liujiangtao
 * @date 2019-11-16
 **/
@Data
@Accessors(chain = true)
public class TableVo implements Serializable {

    /**
     * 实体名称
     */
    private String entityName;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表描述
     */
    private String tableDesc;

    /**
     * 主键列名称
     */
    private String idColumnName;

    /**
     * 主键属性名称
     */
    private String idPropertyName;

    /**
     * 版本列名称
     */
    private String versionColumnName;

    /**
     * 版本属性名称
     */
    private String versionPropertyName;

    /**
     * 属性名对象map
     */
    private Map<String, ColumnVo> propertyMap;

    /**
     * 列名对象map
     */
    private Map<String, ColumnVo> columnMap;

    /**
     * 是否有字段需要记录
     * 默认记录
     */
    private boolean record = true;
}

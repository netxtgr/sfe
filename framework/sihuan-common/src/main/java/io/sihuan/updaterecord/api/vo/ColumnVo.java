/*

 */

package io.sihuan.updaterecord.api.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 列信息对象
 * @author liujiangtao
 * @date 2019-11-24
 **/
@Data
@Accessors(chain = true)
public class ColumnVo implements Serializable {
    private static final long serialVersionUID = -189450303083131452L;

    /**
     * 属性名称
     */
    private String propertyName;

    /**
     * 列名称
     */
    private String columnName;

    /**
     * 列名描述
     */
    private String columnDesc;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 类型Class
     */
    private Class<?> typeClass;
}

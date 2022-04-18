/*

 */

package io.sihuan.updaterecord.core.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 字段比较对象VO
 *
 * @author liujiangtao
 * @date 2019-11-10
 **/
@Data
public class FieldCompareVo implements Serializable {
    private static final long serialVersionUID = -2648342375115327440L;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 修改之前的值
     */
    private Object before;

    /**
     * 修改之后的值
     */
    private Object after;

    /**
     * 1: 添加
     * before: null, after:123
     * 2: 修改
     * before: 123, after:456
     * 3: 删除
     * before: 123, after:null
     */
    private Integer mode;

}

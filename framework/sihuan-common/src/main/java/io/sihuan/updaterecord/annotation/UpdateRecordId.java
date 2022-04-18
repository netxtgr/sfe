/*

 */

package io.sihuan.updaterecord.annotation;

import java.lang.annotation.*;

/**
 * <pre>
 * 用于实体类属性字段上
 * 字段修改记录描述
 * 默认实体类字段会被记录
 * 字段描述：{@code @UpdateRecordColumn("手机号码")}
 * 设置字段不记录：{@code @UpdateRecordColumn(record=false)}
 * 完整配置：{@code @UpdateRecordColumn(column="phone",value="手机号码",record=true)}
 * </pre>
 *
 * @author liujiangtao
 * @date 2022-03-17
 **/
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UpdateRecordId {

    /**
     * 数据库列名，默认实体类字段为列名的驼峰命名
     *
     * @return
     */
    String name() default "";

    /**
     * 字段描述
     *
     * @return
     */
    String value() default "";

}

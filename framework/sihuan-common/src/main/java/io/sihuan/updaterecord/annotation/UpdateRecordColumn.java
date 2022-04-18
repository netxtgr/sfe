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
 * 设置字段不记录：{@code @UpdateRecordIgnore}
 * 完整配置：{@code @UpdateRecordColumn(name="user_name", value="用户名称", sort=1)}
 * </pre>
 *
 * @author liujiangtao
 * @date 2022-03-17
 **/
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UpdateRecordColumn {

    /**
     * 数据库列名，默认实体类字段为列名的驼峰命名
     * 例如：
     * 属性名：createTime
     * 列名：create_time
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

    /**
     * 排序
     *
     * @return
     */
    int sort() default 0;

}

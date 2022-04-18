/*

 */

package io.sihuan.updaterecord.annotation;

import java.lang.annotation.*;

/**
 * <pre>
 * 用于实体类上
 * 表修改记录描述
 * 默认修改时，实体类会被记录
 * 类描述：{@code @UpdateRecordEntity("系统用户")}
 * 设置该类不记录：{@code @UpdateRecordIgnore}
 * 完整配置：{@code @UpdateRecordEntity(name="sys_user", value="系统用户", module="system",separator=",")}
 * </pre>
 *
 * @author liujiangtao
 * @date 2022-03-17
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UpdateRecordTable {

    /**
     * 数据库表名，默认实体类字段为表名的驼峰命名
     * 例如：
     * 实体类：SysUser
     * 表名：sys_user
     *
     * @return
     */
    String name() default "";

    /**
     * 表描述
     *
     * @return
     */
    String value() default "";

    /**
     * 分隔符
     *
     * @return
     */
    String separator() default "\n";

}

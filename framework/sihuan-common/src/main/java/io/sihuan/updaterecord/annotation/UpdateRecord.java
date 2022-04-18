/*

 */

package io.sihuan.updaterecord.annotation;

import java.lang.annotation.*;

/**
 * <pre>
 * 用于Controller方法上
 * 记录当前请求修改记录
 * 类描述：{@code @UpdateRecord("系统用户")}
 * 完整配置：{@code @UpdateRecordEntity(value="系统用户", module="system",includes={},excludes={})}
 * </pre>
 *
 * @author liujiangtao
 * @date 2022-03-17
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UpdateRecord {

    /**
     * 业务方法描述
     *
     * @return
     */
    String value() default "";

    /**
     * 模块名称
     *
     * @return
     */
    String module() default "";

}

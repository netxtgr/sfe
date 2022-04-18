/*

 */

package io.sihuan.updaterecord.annotation;

import java.lang.annotation.*;

/**
 * <pre>
 *   忽略
 *   用于实体类或者属性字段上
 * </pre>
 *
 * @author liujiangtao
 * @date 2022-03-17
 **/
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UpdateRecordIgnore {

}

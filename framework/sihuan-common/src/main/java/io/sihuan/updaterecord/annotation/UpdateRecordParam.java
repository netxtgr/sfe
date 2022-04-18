/*

 */

package io.sihuan.updaterecord.annotation;

import java.lang.annotation.*;

/**
 * <pre>
 * 作用于mapper修改方法上
 * 当修改参数为map时，可在方法上加上该注解，映射表和某个实体关联
 * 类描述：{@code @UpdateRecordRelation(Account.class)}
 * </pre>
 *
 * @author liujiangtao
 * @date 2019-11-27
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UpdateRecordParam {

    Class<?> value();

}

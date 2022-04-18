package io.sihuan.dynamicexcel.annotation;

import java.lang.annotation.*;

/**
 * 功能：注解在属性上
 * 描述：表示这个属性字段会被导出
 * @author： liujiangtao
 **/
@Documented
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ImportTit {

    /**
     * 用于校验
     * @return
     */
    String name() default "";

    /**
     * 当前列所在位置
     * @return
     */
    int index() default -1;

    /**
     * 当前字段是否开启导出，校验等
     * @return
     */
    boolean open() default true;


    /**
     * 当前列是否需要合并
     * @return
     */
    boolean openMerger() default false;

    /**
     * 当前字段是否是时间格式
     * @return
     */
    boolean isDate() default false;

    String sdf() default "yyyy-MM-dd HH:mm:ss";

}

package io.sihuan.dynamicexcel.annotation;

import io.sihuan.dynamicexcel.common.*;

import java.lang.annotation.*;

/**
 * @author： liujiangtao
 **/
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Tab {

    /**
     * 导出或导入文件名称，用于校验等
     * @return
     */
    String name() default "导出数据";

    /**
     * xls 03版本 xlsx07版本
     */

    String version() default Constant.EXCEL_VERSION_03;

    /**
     * 每个sheet存储的list行数
     * @return
     */
    int sheetRowNum() default 65500;

    /**
     * 是否开启二级表头
     * @return
     */
    boolean openSubtitle() default false;


    /**
     * 是否自动合并单元格
     * @return
     */
    boolean openMerger() default true;


    /**
     * 自动扩展列宽
     * @return
     */
    boolean autoSizeColumn() default true;


    /**
     * 表头字体样式
     * @return
     */
    HeaderFont headerFont() default HeaderFont.DEFAULT;

    /**
     * 表头样式
     * @return
     */
    HeaderStyle headerStyle() default HeaderStyle.DEFAULT;


    /**
     * 正文字体样式
     * @return
     */
    ContextFont contextFont() default ContextFont.DEFAULT;

    /**
     * 正文样式
     * @return
     */
    ContextStyle contextStyle() default ContextStyle.DEFAULT;


}

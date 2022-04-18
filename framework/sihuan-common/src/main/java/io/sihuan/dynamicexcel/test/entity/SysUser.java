package io.sihuan.dynamicexcel.test.entity;

import io.sihuan.dynamicexcel.annotation.Tab;
import io.sihuan.dynamicexcel.annotation.ExportTit;
import io.sihuan.dynamicexcel.common.*;
import lombok.Data;

import java.util.Date;

/**
 * @author： liujiangtao
 **/
@Tab(   name = "用户表",//导出的excel文件的名称
        version = Constant.EXCEL_VERSION_07,//导出excel的版本，默认03版本扩展名xls结尾
        sheetRowNum = 500,//导出excel的每个sheet存储导出内容数据的行数（不包含表头行数）
        openMerger=false,//导出excel的内容是否开启同一列连续相同内容自动合并，默认false
        openSubtitle=true,//导出excel的内容是否开启自动填充需要导出的每个属性字段作为表头，默认false
        headerFont = HeaderFont.DEFAULT,//导出excel的表头字体 默认样式，可不写
        headerStyle= HeaderStyle.DEFAULT,//导出excel的表头样式 默认样式，可不写
        contextFont= ContextFont.DEFAULT,//导出excel的内容字体 默认样式，可不写
        contextStyle= ContextStyle.DEFAULT,//导出excel的内容样式 默认样式，可不写
        autoSizeColumn=true//自动扩宽列，默认自动扩展，可不写
)
@Data
public class SysUser {

    @ExportTit(name = "公司",index = 0)
    private String company;

    @ExportTit(name = "部门",index = 1)
    private String dept;

    @ExportTit(name = "科室",index = 2)
    private String office;

    @ExportTit(name = "姓名",index = 3)
    private String name;

    @ExportTit(name = "性别",index = 4)
    private String sex;

    @ExportTit(name = "年龄",index = 5)
    private String age;


    @ExportTit(
            name = "生日",//当前属性字段名称
            index = 6,//当前属性字段导出到excel所在列索引（最开始为0），导入时则是对应excel中列的位置的值将会被解析到该字段存储
            isDate = true, //该字段是不是日期，默认为false
            sdf = "yyyy-MM-dd", //当当前字段为日期时 ，可以设置导入导出解析时间的格式，默认yyyy-MM-dd HH:mm:ss
            open = true, //当前字段是否为解析字段，默认true，true则导入导出时会参与解析，false反之
            openMerger = false //导出时当前字段是否按照同一列值连续相同自动合并，默认false
    )
    private Date birthday;

    public SysUser() {
    }

    public SysUser(String company, String dept, String office, String name, String sex, String age, Date birthday) {
        this.company = company;
        this.dept = dept;
        this.office = office;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.birthday = birthday;
    }

}

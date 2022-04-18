package io.sihuan.dynamicexcel.entity;


import lombok.Data;

/**
 * @author： liujiangtao
 **/
@Data
public class MergerData {

    /**
     * sheet位置
     */
    private Integer sheetIndex;

    /**
     * 列位置
     */
    private Integer colIndex;

    /**
     * 行位置
     */
    private Integer rowIndex;

    /**
     * 当前值
     */
    private String value;

    public MergerData() {
    }

    public MergerData(Integer sheetIndex, Integer colIndex, Integer rowIndex, String value) {
        this.sheetIndex = sheetIndex;
        this.colIndex = colIndex;
        this.rowIndex = rowIndex;
        this.value = value;
    }

}

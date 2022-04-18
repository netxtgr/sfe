package io.sihuan.dynamicexcel.entity;


import lombok.Builder;

/**
 * @author： liujiangtao
 **/
@Builder
public class Header {

    /**
     * 表头名称
     */
    private String name;

    /**
     * 合并简写
     * firstRow-lastRow-firstCol-lastCol
     */
    private String merge;

    /**
     * 合并--列起始位置
     */
    private Integer startCol;

    /**
     * 合并--列结束位置  -1表示不合并
     */
    private Integer endCol;


    /**
     * 合并--行起始位置
     */
    private Integer startRow;

    /**
     * 合并--行结束位置   -1表示不合并
     */
    private Integer endRow;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMerge() {
        return merge;
    }

    public void setMerge(String merge) {
        this.merge = merge;
    }

    public Integer getStartCol() {
        return startCol;
    }

    public void setStartCol(Integer startCol) {
        this.startCol = startCol;
    }

    public Integer getEndCol() {
        return endCol;
    }

    public void setEndCol(Integer endCol) {
        this.endCol = endCol;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getEndRow() {
        return endRow;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }

    public Header(String name, String merge, Integer startCol, Integer endCol, Integer startRow, Integer endRow) {
        this.name = name;
        this.merge = merge;
        this.startCol = startCol;
        this.endCol = endCol;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    public Header() {
    }
}

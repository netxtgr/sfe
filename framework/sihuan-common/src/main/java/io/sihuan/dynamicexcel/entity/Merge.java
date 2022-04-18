package io.sihuan.dynamicexcel.entity;


import lombok.Data;

/**
 * @author： liujiangtao
 **/
@Data
public class Merge {


    private Integer sheetId;

    private Integer startRow;

    private Integer endRow;

    private Integer startCol;

    private Integer endCol;

    private String value;


    public Merge() {
    }

    public Merge(Integer sheetId, Integer startRow, Integer endRow, Integer startCol, Integer endCol, String value) {
        this.sheetId = sheetId;
        this.startRow = startRow;
        this.endRow = endRow;
        this.startCol = startCol;
        this.endCol = endCol;
        this.value = value;
    }

}

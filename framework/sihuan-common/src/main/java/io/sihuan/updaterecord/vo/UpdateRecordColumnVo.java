package io.sihuan.updaterecord.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateRecordColumnVo {
    private String empName;
    private String username;
    private String moduleName;
    private String url;
    private String methodName;
    private String tableName;
    private String tableDesc;
    private String idValue;
    private String columnName;
    private String columnDesc;
    private String beforeValue;
    private String afterValue;
    private Date createTime;
}

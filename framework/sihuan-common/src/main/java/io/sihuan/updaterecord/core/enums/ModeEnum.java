/*

 */

package io.sihuan.updaterecord.core.enums;

/**
 * 修改记录枚举
 *
 * @author liujiangtao
 * @date 2019-11-25
 **/
public enum ModeEnum {

    ADD(1, "添加"),
    UPDATE(2, "修改"),
    DELETE(3, "删除");

    private int code;
    private String desc;

    ModeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

}

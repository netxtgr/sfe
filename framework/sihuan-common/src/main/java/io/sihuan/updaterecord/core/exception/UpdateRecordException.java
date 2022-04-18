/*

 */

package io.sihuan.updaterecord.core.exception;

/**
 * 修改记录异常类
 *
 * @author liujiangtao
 * @date 2019-11-16
 **/
public class UpdateRecordException extends RuntimeException {

    public UpdateRecordException(String message) {
        super(message);
    }

    public UpdateRecordException(String message, Throwable ex) {
        super(message, ex);
    }

}

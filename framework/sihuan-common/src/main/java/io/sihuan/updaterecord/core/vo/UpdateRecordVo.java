/*

 */

package io.sihuan.updaterecord.core.vo;

import io.sihuan.updaterecord.entity.UpdateRecordLogEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 当前请求的所有修改记录对象VO
 *
 * @author liujiangtao
 * @date 2019-11-10
 **/
@Data
@Accessors(chain = true)
public class UpdateRecordVo implements Serializable {
    private static final long serialVersionUID = -7801167457120562470L;

    /**
     * 线程名称
     */
    private String threadName;

    /**
     * 当次修改会话的唯一标识
     */
    private String commitId;

    /**
     * 修改记录
     */
    private UpdateRecordLogEntity updateRecordLog;

    /**
     * 修改记录,当前mapper方法信息
     */
    private List<UpdateRecordItemVo> updateRecordItemVos = new ArrayList<>();

}

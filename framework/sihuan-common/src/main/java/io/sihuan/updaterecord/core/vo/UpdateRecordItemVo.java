/*

 */

package io.sihuan.updaterecord.core.vo;

import io.sihuan.updaterecord.entity.UpdateRecordColumnLogEntity;
import io.sihuan.updaterecord.entity.UpdateRecordTableLogEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 表记录修改对象VO
 * @author liujiangtao
 * @date 2019-11-12
 **/
@Data
@Accessors(chain = true)
public class UpdateRecordItemVo implements Serializable {
    private static final long serialVersionUID = 3151389392610979285L;

    /**
     * 修改记录项
     */
    private UpdateRecordTableLogEntity updateRecordTableLog;

    /**
     * 修改记录详细对象集合
     */
    private Set<UpdateRecordColumnLogEntity> sysUpdateRecordColumnLogs = new LinkedHashSet<>();

    /**
     * 修改之前的map
     */
    private Map<String, Object> beforeMap;

    /**
     * 修改之后的map
     */
    private Map<String, Object> afterMap;

    /**
     * 变更值对象列表
     * <code>
     *   {
     *      "table-0-foo_bar-id-1":{"name":"foo","before":"hello","after":"你好","mode":2},
     *      "table-1-foo_bar-id-2":{"name":"foo","before":"world","after":"世界","mode":2}
     *   }
     * </code>
     */
    private Set<FieldCompareVo> diffSet;
}

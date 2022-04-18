/*

 */

package io.sihuan.updaterecord.core.factory;

import io.sihuan.updaterecord.service.UpdateRecordColumnLogService;
import io.sihuan.updaterecord.service.UpdateRecordLogService;
import io.sihuan.updaterecord.service.UpdateRecordTableLogService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 修改记录服务工厂类
 *
 * @author liujiangtao
 * @date 2019-11-23
 **/
@Component
public class UpdateRecordServiceFactory {
    /**
     * 修改服务实现类bean名称
     */
    private static final String UPDATE_RECORD_SERVICE_IMPL = "updateRecordServiceImpl";

    @Autowired
    private UpdateRecordColumnLogService updateRecordColumnLogService;

    @Autowired
    private UpdateRecordLogService updateRecordLogService;

    @Autowired
    private UpdateRecordTableLogService updateRecordTableLogService;

    /**
     * 构造函数中注入UpdateRecordService接口的所有实现类
     * 通常有三种情况
     * 1. 默认至少有一个实现类 DefaultUpdateRecordServiceImpl
     * 2. 依赖 update-record-persistence 模块时，注入：UpdateRecordServiceImpl
     * 3. 如果需要自定义，则不依赖 update-record-persistence 模块，注入自定义实现类
     *
     * @param map
     */
    public UpdateRecordServiceFactory(UpdateRecordColumnLogService updateRecordColumnLogService, UpdateRecordLogService updateRecordLogService, UpdateRecordTableLogService updateRecordTableLogService) {
        updateRecordColumnLogService = this.updateRecordColumnLogService;
        updateRecordLogService = this.updateRecordLogService;
        updateRecordTableLogService = this.updateRecordTableLogService;
    }

    public UpdateRecordColumnLogService getUpdateRecordColumnLogService() {
        return updateRecordColumnLogService;
    }

    public UpdateRecordLogService getUpdateRecordLogService() {
        return updateRecordLogService;
    }

    public UpdateRecordTableLogService getUpdateRecordTableLogService() {
        return updateRecordTableLogService;
    }

}

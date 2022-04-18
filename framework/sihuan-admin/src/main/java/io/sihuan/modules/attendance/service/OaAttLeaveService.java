package io.sihuan.modules.attendance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.sihuan.modules.attendance.entity.OaAttLeaveEntity;

import java.util.List;
import java.util.Map;

/**
 * OA请假数据
 *
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-10 14:54:56
 */
public interface OaAttLeaveService extends IService<OaAttLeaveEntity> {
    List<OaAttLeaveEntity> selectAttLeave();
    void deleteTbAttLeave();
    List<OaAttLeaveEntity> listAttLeave();
}


package io.sihuan.modules.attendance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.modules.attendance.entity.AttendanceSignEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 钉钉签到信息
 *
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-06 09:23:17
 */
public interface AttendanceSignService extends IService<AttendanceSignEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean removeByUserIdDate(String userId, Date startDate, Date endDate);

    List<AttendanceSignEntity> listAttSign();
}


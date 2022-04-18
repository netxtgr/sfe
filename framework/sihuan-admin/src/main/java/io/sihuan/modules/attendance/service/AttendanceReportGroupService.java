package io.sihuan.modules.attendance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.modules.attendance.entity.AttendanceReportGroupEntity;

import java.util.Map;

/**
 * 需统计考勤组
 *
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-12 14:41:41
 */
public interface AttendanceReportGroupService extends IService<AttendanceReportGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


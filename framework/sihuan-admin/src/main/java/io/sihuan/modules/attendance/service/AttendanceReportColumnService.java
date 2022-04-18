package io.sihuan.modules.attendance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.modules.attendance.entity.AttendanceReportColumnEntity;

import java.util.Map;

/**
 * 钉钉考勤报表接口列定义
 *
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-05 10:06:06
 */
public interface AttendanceReportColumnService extends IService<AttendanceReportColumnEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


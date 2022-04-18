package io.sihuan.modules.attendance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.modules.attendance.entity.AttendanceDailyEntity;
import io.sihuan.modules.attendance.vo.AttendanceReportDetailVO;
import io.sihuan.modules.attendance.vo.AttendanceReportVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 钉钉每日考勤结果
 *
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-05 10:06:06
 */
public interface AttendanceDailyService extends IService<AttendanceDailyEntity> {

    PageUtils queryPage(Map<String, Object> params);

    AttendanceDailyEntity getByUserIdAttDate(String userId, Date attDate);

    List<AttendanceDailyEntity> selectMonthAtts(Map<String, String> params);

    PageUtils queryPageReport(Map<String, Object> params);
    PageUtils queryPageReportDetail(Map<String, Object> params);

    List<AttendanceReportVO> exportReport(Map<String, Object> params);
    List<AttendanceReportDetailVO> exportReportDetail(Map<String, Object> params);
}


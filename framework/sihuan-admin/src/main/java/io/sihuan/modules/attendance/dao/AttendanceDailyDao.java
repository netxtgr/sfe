package io.sihuan.modules.attendance.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.sihuan.modules.attendance.entity.AttendanceDailyEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.sihuan.modules.attendance.vo.AttendanceReportDetailVO;
import io.sihuan.modules.attendance.vo.AttendanceReportVO;
import org.apache.ibatis.annotations.Mapper;
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
@Mapper
public interface AttendanceDailyDao extends BaseMapper<AttendanceDailyEntity> {
	List<AttendanceDailyEntity> selectMonthAtts(@Param(value = "params") Map<String, String> params);
	IPage<AttendanceReportVO> reportAtts(IPage<AttendanceReportVO> page, @Param(value = "params") Map<String, Object> params);
	IPage<AttendanceReportDetailVO> reportAttDetail(IPage<AttendanceReportVO> page, @Param(value = "params") Map<String, Object> params);
	List<AttendanceReportVO> reportAtts(@Param(value = "params") Map<String, Object> params);
	List<AttendanceReportDetailVO> reportAttDetail(@Param(value = "params") Map<String, Object> params);
}

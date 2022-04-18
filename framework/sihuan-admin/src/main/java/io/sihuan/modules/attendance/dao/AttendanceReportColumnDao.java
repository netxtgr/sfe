package io.sihuan.modules.attendance.dao;

import io.sihuan.modules.attendance.entity.AttendanceReportColumnEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 钉钉考勤报表接口列定义
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-05 10:06:06
 */
@Mapper
public interface AttendanceReportColumnDao extends BaseMapper<AttendanceReportColumnEntity> {
	
}

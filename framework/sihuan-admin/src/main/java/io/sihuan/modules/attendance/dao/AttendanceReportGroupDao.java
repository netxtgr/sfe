package io.sihuan.modules.attendance.dao;

import io.sihuan.modules.attendance.entity.AttendanceReportGroupEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 需统计考勤组
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-12 14:41:41
 */
@Mapper
public interface AttendanceReportGroupDao extends BaseMapper<AttendanceReportGroupEntity> {
	
}

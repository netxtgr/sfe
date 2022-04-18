package io.sihuan.modules.attendance.dao;

import io.sihuan.modules.attendance.entity.AttendanceUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-05 10:06:06
 */
@Mapper
public interface AttendanceUserDao extends BaseMapper<AttendanceUserEntity> {
	
}

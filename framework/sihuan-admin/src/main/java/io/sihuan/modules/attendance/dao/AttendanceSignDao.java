package io.sihuan.modules.attendance.dao;

import io.sihuan.modules.attendance.entity.AttendanceSignEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 钉钉签到信息
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-06 09:23:17
 */
@Mapper
public interface AttendanceSignDao extends BaseMapper<AttendanceSignEntity> {
    List<AttendanceSignEntity> listAttSign();
}

package io.sihuan.modules.attendance.dao;

import io.sihuan.modules.attendance.entity.OaAttLeaveEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * OA请假数据
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-10 14:54:56
 */
@Mapper
public interface OaAttLeaveDao extends BaseMapper<OaAttLeaveEntity> {
    List<OaAttLeaveEntity> selectAttLeave();
	void deleteTbAttLeave();
    List<OaAttLeaveEntity> listAttLeave();
}

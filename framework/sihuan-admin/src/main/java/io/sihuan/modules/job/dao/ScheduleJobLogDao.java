/**
 * 版权所有，侵权必究！
 */

package io.sihuan.modules.job.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.sihuan.modules.job.entity.ScheduleJobLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务日志
 * @author ljt liujiangtao@sihuanpharm.com
 */
@Mapper
public interface ScheduleJobLogDao extends BaseMapper<ScheduleJobLogEntity> {
	
}

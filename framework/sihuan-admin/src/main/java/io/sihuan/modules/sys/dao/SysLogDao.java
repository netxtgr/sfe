/**
 * 版权所有，侵权必究！
 */

package io.sihuan.modules.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.sihuan.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 *
 * @author ljt liujiangtao@sihuanpharm.com
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {
	
}

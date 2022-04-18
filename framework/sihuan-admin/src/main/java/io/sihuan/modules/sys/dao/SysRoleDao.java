/**
 * 版权所有，侵权必究！
 */

package io.sihuan.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.sihuan.modules.sys.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色管理
 *
 * @author ljt liujiangtao@sihuanpharm.com
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {
	

}

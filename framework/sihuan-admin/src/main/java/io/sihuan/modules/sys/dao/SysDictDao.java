/**
 * 版权所有，侵权必究！
 */

package io.sihuan.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.sihuan.modules.sys.entity.SysDictEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据字典
 *
 * @author ljt liujiangtao@sihuanpharm.com
 */
@Mapper
public interface SysDictDao extends BaseMapper<SysDictEntity> {
	
}

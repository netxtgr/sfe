/**
 * 版权所有，侵权必究！
 */

package io.sihuan.modules.oss.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.sihuan.modules.oss.entity.SysOssEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件上传
 * @author ljt liujiangtao@sihuanpharm.com
 */
@Mapper
public interface SysOssDao extends BaseMapper<SysOssEntity> {
	
}

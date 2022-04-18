/**

 *
 * 版权所有，侵权必究！
 */

package io.sihuan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.sihuan.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 *
 * @author ljt liujiangtao@sihuanpharm.com
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}

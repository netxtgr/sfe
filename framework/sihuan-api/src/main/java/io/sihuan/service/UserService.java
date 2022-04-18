/**

 *
 * 版权所有，侵权必究！
 */

package io.sihuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.sihuan.entity.UserEntity;
import io.sihuan.form.LoginForm;

import java.util.Map;

/**
 * 用户
 *
 * @author ljt liujiangtao@sihuanpharm.com
 */
public interface UserService extends IService<UserEntity> {

	UserEntity queryByMobile(String mobile);

	/**
	 * 用户登录
	 * @param form    登录表单
	 * @return        返回登录信息
	 */
	Map<String, Object> login(LoginForm form);
}

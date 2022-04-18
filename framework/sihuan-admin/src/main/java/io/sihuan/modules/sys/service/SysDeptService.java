/**
 * 版权所有，侵权必究！
 */

package io.sihuan.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.sihuan.modules.sys.entity.SysDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author ljt liujiangtao@sihuanpharm.com
 */
public interface SysDeptService extends IService<SysDeptEntity> {

	List<SysDeptEntity> queryList(Map<String, Object> map);

	/**
	 * 查询子部门ID列表
	 * @param parentId  上级部门ID
	 */
	List<Long> queryDetpIdList(Long parentId);

	/**
	 * 获取子部门ID，用于数据过滤
	 */
	List<Long> getSubDeptIdList(Long deptId);

	SysDeptEntity getByCode(Long deptId, String deptCode);

}

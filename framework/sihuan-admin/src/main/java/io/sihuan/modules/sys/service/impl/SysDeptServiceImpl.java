/**
 * 版权所有，侵权必究！
 */

package io.sihuan.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.common.annotation.DataFilter;
import io.sihuan.modules.sys.dao.SysDeptDao;
import io.sihuan.modules.sys.entity.SysDeptEntity;
import io.sihuan.modules.sys.service.SysDeptService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDeptEntity> implements SysDeptService {
	
	@Override
	@DataFilter(subDept = true, user = false, tableAlias = "t1")
	public List<SysDeptEntity> queryList(Map<String, Object> params){
		return baseMapper.queryList(params);
	}

	@Override
	public List<Long> queryDetpIdList(Long parentId) {
		return baseMapper.queryDetpIdList(parentId);
	}

	@Override
	public List<Long> getSubDeptIdList(Long deptId){
		//部门及子部门ID列表
		List<Long> deptIdList = new ArrayList<>();

		//获取子部门ID
		List<Long> subIdList = queryDetpIdList(deptId);
		getDeptTreeList(subIdList, deptIdList);

		return deptIdList;
	}

	@Override
	public SysDeptEntity getByCode(Long deptId, String deptCode) {
		QueryWrapper<SysDeptEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("dept_code", deptCode);
		if (deptId != null) {
			queryWrapper.ne("dept_id", deptId);
		}
		return baseMapper.selectOne(queryWrapper);
	}

	/**
	 * 递归
	 */
	private void getDeptTreeList(List<Long> subIdList, List<Long> deptIdList){
		for(Long deptId : subIdList){
			List<Long> list = queryDetpIdList(deptId);
			if(list.size() > 0){
				getDeptTreeList(list, deptIdList);
			}

			deptIdList.add(deptId);
		}
	}
}

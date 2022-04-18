package io.sihuan.modules.attendance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.modules.attendance.entity.AttendanceGroupEntity;

import java.util.Map;

/**
 * 
 *
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-04 14:25:18
 */
public interface AttendanceGroupService extends IService<AttendanceGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    AttendanceGroupEntity getGroup(int groupId);

}


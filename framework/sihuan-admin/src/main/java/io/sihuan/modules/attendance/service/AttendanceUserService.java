package io.sihuan.modules.attendance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.modules.attendance.entity.AttendanceUserEntity;

import java.util.Map;

/**
 * 
 *
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-05 10:06:06
 */
public interface AttendanceUserService extends IService<AttendanceUserEntity> {

    PageUtils queryPage(Map<String, Object> params);
    boolean removeByGroupId(int groupId);
    AttendanceUserEntity getByUserId(String userId);


}


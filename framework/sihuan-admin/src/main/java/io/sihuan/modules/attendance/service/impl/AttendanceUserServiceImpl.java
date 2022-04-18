package io.sihuan.modules.attendance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.Query;
import io.sihuan.modules.attendance.dao.AttendanceUserDao;
import io.sihuan.modules.attendance.entity.AttendanceGroupEntity;
import io.sihuan.modules.attendance.entity.AttendanceUserEntity;
import io.sihuan.modules.attendance.service.AttendanceUserService;
import org.springframework.stereotype.Service;
import java.util.Map;


@Service("attendanceUserService")
public class AttendanceUserServiceImpl extends ServiceImpl<AttendanceUserDao, AttendanceUserEntity> implements AttendanceUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttendanceUserEntity> page = this.page(
                new Query<AttendanceUserEntity>().getPage(params),
                new QueryWrapper<AttendanceUserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public boolean removeByGroupId(int groupId) {
        QueryWrapper<AttendanceUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_id", groupId);
        return this.remove(queryWrapper);
    }

    @Override
    public AttendanceUserEntity getByUserId(String userId) {
        QueryWrapper<AttendanceUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return this.getOne(queryWrapper);
    }

}

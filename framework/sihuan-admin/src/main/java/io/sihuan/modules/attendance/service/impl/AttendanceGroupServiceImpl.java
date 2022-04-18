package io.sihuan.modules.attendance.service.impl;

import io.sihuan.modules.attendance.dao.AttendanceGroupDao;
import io.sihuan.modules.attendance.entity.AttendanceGroupEntity;
import io.sihuan.modules.attendance.service.AttendanceGroupService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.Query;


@Service("attendanceGroupService")
public class AttendanceGroupServiceImpl extends ServiceImpl<AttendanceGroupDao, AttendanceGroupEntity> implements AttendanceGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttendanceGroupEntity> page = this.page(
                new Query<AttendanceGroupEntity>().getPage(params),
                new QueryWrapper<AttendanceGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public AttendanceGroupEntity getGroup(int groupId) {
        QueryWrapper<AttendanceGroupEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_id", groupId);
        return this.getOne(queryWrapper);
    }

}

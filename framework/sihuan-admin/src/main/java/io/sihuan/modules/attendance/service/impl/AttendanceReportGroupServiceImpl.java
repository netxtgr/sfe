package io.sihuan.modules.attendance.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.Query;

import io.sihuan.modules.attendance.dao.AttendanceReportGroupDao;
import io.sihuan.modules.attendance.entity.AttendanceReportGroupEntity;
import io.sihuan.modules.attendance.service.AttendanceReportGroupService;


@Service("attendanceReportGroupService")
public class AttendanceReportGroupServiceImpl extends ServiceImpl<AttendanceReportGroupDao, AttendanceReportGroupEntity> implements AttendanceReportGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttendanceReportGroupEntity> page = this.page(
                new Query<AttendanceReportGroupEntity>().getPage(params),
                new QueryWrapper<AttendanceReportGroupEntity>()
        );

        return new PageUtils(page);
    }

}

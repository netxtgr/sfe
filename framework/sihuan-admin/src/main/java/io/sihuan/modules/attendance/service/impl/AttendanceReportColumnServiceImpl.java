package io.sihuan.modules.attendance.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.Query;

import io.sihuan.modules.attendance.dao.AttendanceReportColumnDao;
import io.sihuan.modules.attendance.entity.AttendanceReportColumnEntity;
import io.sihuan.modules.attendance.service.AttendanceReportColumnService;


@Service("attendanceReportColumnService")
public class AttendanceReportColumnServiceImpl extends ServiceImpl<AttendanceReportColumnDao, AttendanceReportColumnEntity> implements AttendanceReportColumnService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttendanceReportColumnEntity> page = this.page(
                new Query<AttendanceReportColumnEntity>().getPage(params),
                new QueryWrapper<AttendanceReportColumnEntity>()
        );

        return new PageUtils(page);
    }

}

package io.sihuan.modules.attendance.service.impl;

import io.sihuan.modules.attendance.vo.AttendanceReportDetailVO;
import io.sihuan.modules.attendance.vo.AttendanceReportVO;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.Query;
import io.sihuan.modules.attendance.dao.AttendanceDailyDao;
import io.sihuan.modules.attendance.entity.AttendanceDailyEntity;
import io.sihuan.modules.attendance.service.AttendanceDailyService;


@Service("attendanceDailyService")
public class AttendanceDailyServiceImpl extends ServiceImpl<AttendanceDailyDao, AttendanceDailyEntity> implements AttendanceDailyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttendanceDailyEntity> page = this.page(
                new Query<AttendanceDailyEntity>().getPage(params),
                new QueryWrapper<AttendanceDailyEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public AttendanceDailyEntity getByUserIdAttDate(String userId, Date attDate) {
        QueryWrapper<AttendanceDailyEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.between("att_date", attDate, attDate);
        return this.getOne(queryWrapper);
    }

    @Override
    public List<AttendanceDailyEntity> selectMonthAtts(Map<String, String> params) {
        return baseMapper.selectMonthAtts(params);
    }

    @Override
    public PageUtils queryPageReport(Map<String, Object> params) {

        IPage<AttendanceReportVO> page = this.baseMapper.reportAtts(
                new Query<AttendanceReportVO>().getPage(params), params
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageReportDetail(Map<String, Object> params) {
        IPage<AttendanceReportDetailVO> page = this.baseMapper.reportAttDetail(
                new Query<AttendanceReportVO>().getPage(params), params
        );
        return new PageUtils(page);
    }

    @Override
    public List<AttendanceReportVO> exportReport(Map<String, Object> params) {
        return baseMapper.reportAtts(params);
    }

    @Override
    public List<AttendanceReportDetailVO> exportReportDetail(Map<String, Object> params) {
        return baseMapper.reportAttDetail(params);
    }
}

package io.sihuan.modules.attendance.service.impl;

import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.Query;
import io.sihuan.modules.attendance.dao.AttendanceSignDao;
import io.sihuan.modules.attendance.entity.AttendanceSignEntity;
import io.sihuan.modules.attendance.service.AttendanceSignService;


@Service("attendanceSignService")
public class AttendanceSignServiceImpl extends ServiceImpl<AttendanceSignDao, AttendanceSignEntity> implements AttendanceSignService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttendanceSignEntity> page = this.page(
                new Query<AttendanceSignEntity>().getPage(params),
                new QueryWrapper<AttendanceSignEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public boolean removeByUserIdDate(String userId, Date startDate, Date endDate) {
        QueryWrapper<AttendanceSignEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.between("checkin_time", startDate, endDate);
        return this.remove(queryWrapper);
    }

    @Override
    public List<AttendanceSignEntity> listAttSign() {
        return baseMapper.listAttSign();
    }

}

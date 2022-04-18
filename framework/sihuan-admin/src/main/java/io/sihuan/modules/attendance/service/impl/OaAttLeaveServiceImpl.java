package io.sihuan.modules.attendance.service.impl;

import io.sihuan.commons.dynamic.datasource.annotation.DataSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.Query;

import io.sihuan.modules.attendance.dao.OaAttLeaveDao;
import io.sihuan.modules.attendance.entity.OaAttLeaveEntity;
import io.sihuan.modules.attendance.service.OaAttLeaveService;


@Service("oaAttLeaveService")
public class OaAttLeaveServiceImpl extends ServiceImpl<OaAttLeaveDao, OaAttLeaveEntity> implements OaAttLeaveService {


    @Override
    @DataSource("oa")
    public List<OaAttLeaveEntity> selectAttLeave() {
        return baseMapper.selectAttLeave();
    }

    @Override
    public void deleteTbAttLeave() {
        baseMapper.deleteTbAttLeave();
    }

    @Override
    public List<OaAttLeaveEntity> listAttLeave() {
        return baseMapper.listAttLeave();
    }
}

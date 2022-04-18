package io.sihuan.modules.attendance.service.impl;

import io.sihuan.commons.dynamic.datasource.annotation.DataSource;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.modules.attendance.dao.OaAttCcsqDao;
import io.sihuan.modules.attendance.entity.OaAttCcsqEntity;
import io.sihuan.modules.attendance.service.OaAttCcsqService;


@Service("oaAttCcsqService")
public class OaAttCcsqServiceImpl extends ServiceImpl<OaAttCcsqDao, OaAttCcsqEntity> implements OaAttCcsqService {


    @Override
    @DataSource("oa")
    public List<OaAttCcsqEntity> selectAttCcsq() {
        return baseMapper.selectAttCcsq();
    }

    @Override
    public void deleteTbAttCcsq() {
        baseMapper.deleteTbAttCcsq();
    }

    @Override
    public List<OaAttCcsqEntity> listAttCcsq() {
        return baseMapper.listAttCcsq();
    }
}

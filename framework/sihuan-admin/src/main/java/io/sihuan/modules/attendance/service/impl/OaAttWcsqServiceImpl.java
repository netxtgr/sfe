package io.sihuan.modules.attendance.service.impl;

import io.sihuan.commons.dynamic.datasource.annotation.DataSource;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.modules.attendance.dao.OaAttWcsqDao;
import io.sihuan.modules.attendance.entity.OaAttWcsqEntity;
import io.sihuan.modules.attendance.service.OaAttWcsqService;


@Service("oaAttWcsqService")
public class OaAttWcsqServiceImpl extends ServiceImpl<OaAttWcsqDao, OaAttWcsqEntity> implements OaAttWcsqService {


    @Override
    @DataSource("oa")
    public List<OaAttWcsqEntity> selectAttWcsq() {
        return baseMapper.selectAttWcsq();
    }

    @Override
    public void deleteTbAttWcsq() {
        baseMapper.deleteTbAttWcsq();
    }

    @Override
    public List<OaAttWcsqEntity> listAttWcsq() {
        return baseMapper.listAttWcsq();
    }
}

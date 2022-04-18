package io.sihuan.modules.attendance.service.impl;

import io.sihuan.commons.dynamic.datasource.annotation.DataSource;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.modules.attendance.dao.OaAttJbDao;
import io.sihuan.modules.attendance.entity.OaAttJbEntity;
import io.sihuan.modules.attendance.service.OaAttJbService;


@Service("oaAttJbService")
public class OaAttJbServiceImpl extends ServiceImpl<OaAttJbDao, OaAttJbEntity> implements OaAttJbService {

    @Override
    @DataSource("oa")
    public List<OaAttJbEntity> selectAttJb() {
        return baseMapper.selectAttJb();
    }

    @Override
    public void deleteTbAttJb() {
        baseMapper.deleteTbAttJb();
    }

    @Override
    public List<OaAttJbEntity> listAttJb() {
        return baseMapper.listAttJb();
    }
}

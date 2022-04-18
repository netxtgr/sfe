package io.sihuan.modules.attendance.service.impl;

import io.sihuan.commons.dynamic.datasource.annotation.DataSource;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.modules.attendance.dao.OaAttExceptionDao;
import io.sihuan.modules.attendance.entity.OaAttExceptionEntity;
import io.sihuan.modules.attendance.service.OaAttExceptionService;


@Service("oaAttExceptionService")
public class OaAttExceptionServiceImpl extends ServiceImpl<OaAttExceptionDao, OaAttExceptionEntity> implements OaAttExceptionService {

    @Override
    @DataSource("oa")
    public List<OaAttExceptionEntity> selectAttExcetion() {
        return baseMapper.selectAttExcetion();
    }

    @Override
    public void deleteTbAttExcetion() {
        baseMapper.deleteTbAttExcetion();
    }

    @Override
    public List<OaAttExceptionEntity> listAttExcetion() {
        return baseMapper.listAttExcetion();
    }
}

package io.sihuan.modules.attendance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.Query;
import io.sihuan.commons.dynamic.datasource.annotation.DataSource;
import io.sihuan.modules.attendance.dao.OaAttLeaveDao;
import io.sihuan.modules.attendance.dao.OaPersonDao;
import io.sihuan.modules.attendance.entity.OaAttLeaveEntity;
import io.sihuan.modules.attendance.entity.OaPersonEntity;
import io.sihuan.modules.attendance.service.OaAttLeaveService;
import io.sihuan.modules.attendance.service.OaPersonService;
import io.sihuan.modules.attendance.vo.OaPersonInfoVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("oaPersonService")
public class OaPersonServiceImpl extends ServiceImpl<OaPersonDao, OaPersonEntity> implements OaPersonService {

    @Override
    @DataSource("oa")
    public List<OaPersonInfoVO> selectOaPerson() {
        return baseMapper.selectOaPerson();
    }

    @Override
    public void truncateTable(String tableName) {
        baseMapper.truncateTable(tableName);
    }
}

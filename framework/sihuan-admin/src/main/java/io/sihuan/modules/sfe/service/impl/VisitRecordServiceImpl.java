package io.sihuan.modules.sfe.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.Query;

import io.sihuan.modules.sfe.dao.VisitRecordDao;
import io.sihuan.modules.sfe.entity.VisitRecordEntity;
import io.sihuan.modules.sfe.service.VisitRecordService;


@Service("visitRecordService")
public class VisitRecordServiceImpl extends ServiceImpl<VisitRecordDao, VisitRecordEntity> implements VisitRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<VisitRecordEntity> page = this.page(
                new Query<VisitRecordEntity>().getPage(params),
                new QueryWrapper<VisitRecordEntity>()
        );

        return new PageUtils(page);
    }

}

package io.sihuan.modules.sfe.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.Query;

import io.sihuan.modules.sfe.dao.MarketingActivitiesDetailDao;
import io.sihuan.modules.sfe.entity.MarketingActivitiesDetailEntity;
import io.sihuan.modules.sfe.service.MarketingActivitiesDetailService;


@Service("marketingActivitiesDetailService")
public class MarketingActivitiesDetailServiceImpl extends ServiceImpl<MarketingActivitiesDetailDao, MarketingActivitiesDetailEntity> implements MarketingActivitiesDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MarketingActivitiesDetailEntity> page = this.page(
                new Query<MarketingActivitiesDetailEntity>().getPage(params),
                new QueryWrapper<MarketingActivitiesDetailEntity>()
        );

        return new PageUtils(page);
    }

}

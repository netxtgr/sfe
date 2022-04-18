package io.sihuan.modules.sfe.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.Query;

import io.sihuan.modules.sfe.dao.MarketingActivitiesDao;
import io.sihuan.modules.sfe.entity.MarketingActivitiesEntity;
import io.sihuan.modules.sfe.service.MarketingActivitiesService;


@Service("marketingActivitiesService")
public class MarketingActivitiesServiceImpl extends ServiceImpl<MarketingActivitiesDao, MarketingActivitiesEntity> implements MarketingActivitiesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MarketingActivitiesEntity> page = this.page(
                new Query<MarketingActivitiesEntity>().getPage(params),
                new QueryWrapper<MarketingActivitiesEntity>()
        );

        return new PageUtils(page);
    }

}

package io.sihuan.modules.sfe.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.Query;

import io.sihuan.modules.sfe.dao.FollowUpEvaluateDetailDao;
import io.sihuan.modules.sfe.entity.FollowUpEvaluateDetailEntity;
import io.sihuan.modules.sfe.service.FollowUpEvaluateDetailService;


@Service("followUpEvaluateDetailService")
public class FollowUpEvaluateDetailServiceImpl extends ServiceImpl<FollowUpEvaluateDetailDao, FollowUpEvaluateDetailEntity> implements FollowUpEvaluateDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FollowUpEvaluateDetailEntity> page = this.page(
                new Query<FollowUpEvaluateDetailEntity>().getPage(params),
                new QueryWrapper<FollowUpEvaluateDetailEntity>()
        );

        return new PageUtils(page);
    }

}

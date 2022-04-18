package io.sihuan.modules.sfe.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.Query;

import io.sihuan.modules.sfe.dao.FollowUpEvaluateDao;
import io.sihuan.modules.sfe.entity.FollowUpEvaluateEntity;
import io.sihuan.modules.sfe.service.FollowUpEvaluateService;


@Service("followUpEvaluateService")
public class FollowUpEvaluateServiceImpl extends ServiceImpl<FollowUpEvaluateDao, FollowUpEvaluateEntity> implements FollowUpEvaluateService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FollowUpEvaluateEntity> page = this.page(
                new Query<FollowUpEvaluateEntity>().getPage(params),
                new QueryWrapper<FollowUpEvaluateEntity>()
        );

        return new PageUtils(page);
    }

}

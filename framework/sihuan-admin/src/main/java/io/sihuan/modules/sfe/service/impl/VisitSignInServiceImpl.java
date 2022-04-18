package io.sihuan.modules.sfe.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.Query;

import io.sihuan.modules.sfe.dao.VisitSignInDao;
import io.sihuan.modules.sfe.entity.VisitSignInEntity;
import io.sihuan.modules.sfe.service.VisitSignInService;


@Service("visitSignInService")
public class VisitSignInServiceImpl extends ServiceImpl<VisitSignInDao, VisitSignInEntity> implements VisitSignInService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<VisitSignInEntity> page = this.page(
                new Query<VisitSignInEntity>().getPage(params),
                new QueryWrapper<VisitSignInEntity>()
        );

        return new PageUtils(page);
    }

}

package io.sihuan.modules.sfe.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.Query;

import io.sihuan.modules.sfe.dao.VisitRecordDetailDao;
import io.sihuan.modules.sfe.entity.VisitRecordDetailEntity;
import io.sihuan.modules.sfe.service.VisitRecordDetailService;


@Service("visitRecordDetailService")
public class VisitRecordDetailServiceImpl extends ServiceImpl<VisitRecordDetailDao, VisitRecordDetailEntity> implements VisitRecordDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<VisitRecordDetailEntity> page = this.page(
                new Query<VisitRecordDetailEntity>().getPage(params),
                new QueryWrapper<VisitRecordDetailEntity>()
        );

        return new PageUtils(page);
    }

}

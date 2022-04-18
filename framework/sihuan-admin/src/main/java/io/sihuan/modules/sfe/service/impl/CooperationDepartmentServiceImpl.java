package io.sihuan.modules.sfe.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.Query;

import io.sihuan.modules.sfe.dao.CooperationDepartmentDao;
import io.sihuan.modules.sfe.entity.CooperationDepartmentEntity;
import io.sihuan.modules.sfe.service.CooperationDepartmentService;


@Service("cooperationDepartmentService")
public class CooperationDepartmentServiceImpl extends ServiceImpl<CooperationDepartmentDao, CooperationDepartmentEntity> implements CooperationDepartmentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CooperationDepartmentEntity> page = this.page(
                new Query<CooperationDepartmentEntity>().getPage(params),
                new QueryWrapper<CooperationDepartmentEntity>()
        );

        return new PageUtils(page);
    }

}

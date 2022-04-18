package io.sihuan.modules.sfe.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.Query;

import io.sihuan.modules.sfe.dao.KnowledgeBaseDao;
import io.sihuan.modules.sfe.entity.KnowledgeBaseEntity;
import io.sihuan.modules.sfe.service.KnowledgeBaseService;


@Service("knowledgeBaseService")
public class KnowledgeBaseServiceImpl extends ServiceImpl<KnowledgeBaseDao, KnowledgeBaseEntity> implements KnowledgeBaseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<KnowledgeBaseEntity> page = this.page(
                new Query<KnowledgeBaseEntity>().getPage(params),
                new QueryWrapper<KnowledgeBaseEntity>()
        );

        return new PageUtils(page);
    }

}

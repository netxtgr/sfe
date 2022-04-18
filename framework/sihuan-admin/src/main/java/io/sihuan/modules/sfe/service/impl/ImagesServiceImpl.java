package io.sihuan.modules.sfe.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.Query;

import io.sihuan.modules.sfe.dao.ImagesDao;
import io.sihuan.modules.sfe.entity.ImagesEntity;
import io.sihuan.modules.sfe.service.ImagesService;


@Service("imagesService")
public class ImagesServiceImpl extends ServiceImpl<ImagesDao, ImagesEntity> implements ImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ImagesEntity> page = this.page(
                new Query<ImagesEntity>().getPage(params),
                new QueryWrapper<ImagesEntity>()
        );

        return new PageUtils(page);
    }

}

package io.sihuan.modules.sfe.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.Query;

import io.sihuan.modules.sfe.dao.TerminalLinkmanDao;
import io.sihuan.modules.sfe.entity.TerminalLinkmanEntity;
import io.sihuan.modules.sfe.service.TerminalLinkmanService;


@Service("terminalLinkmanService")
public class TerminalLinkmanServiceImpl extends ServiceImpl<TerminalLinkmanDao, TerminalLinkmanEntity> implements TerminalLinkmanService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TerminalLinkmanEntity> page = this.page(
                new Query<TerminalLinkmanEntity>().getPage(params),
                new QueryWrapper<TerminalLinkmanEntity>()
        );

        return new PageUtils(page);
    }

}

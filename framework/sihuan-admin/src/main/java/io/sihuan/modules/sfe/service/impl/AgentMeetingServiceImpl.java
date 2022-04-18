package io.sihuan.modules.sfe.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.Query;

import io.sihuan.modules.sfe.dao.AgentMeetingDao;
import io.sihuan.modules.sfe.entity.AgentMeetingEntity;
import io.sihuan.modules.sfe.service.AgentMeetingService;


@Service("agentMeetingService")
public class AgentMeetingServiceImpl extends ServiceImpl<AgentMeetingDao, AgentMeetingEntity> implements AgentMeetingService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AgentMeetingEntity> page = this.page(
                new Query<AgentMeetingEntity>().getPage(params),
                new QueryWrapper<AgentMeetingEntity>()
        );

        return new PageUtils(page);
    }

}

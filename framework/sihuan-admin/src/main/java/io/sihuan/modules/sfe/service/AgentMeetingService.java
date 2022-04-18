package io.sihuan.modules.sfe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.modules.sfe.entity.AgentMeetingEntity;

import java.util.Map;

/**
 * 
 *
 * @author Tiger Cheung
 * @email Tiger.Chang@outlook.com
 * @date 2022-04-18 15:24:25
 */
public interface AgentMeetingService extends IService<AgentMeetingEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


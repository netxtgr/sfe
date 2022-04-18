package io.sihuan.modules.attendance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.modules.attendance.entity.OaAttCcsqEntity;

import java.util.List;
import java.util.Map;

/**
 * OA出差表
 *
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-12 08:47:45
 */
public interface OaAttCcsqService extends IService<OaAttCcsqEntity> {

    List<OaAttCcsqEntity> selectAttCcsq();
    void deleteTbAttCcsq();
    List<OaAttCcsqEntity> listAttCcsq();
}


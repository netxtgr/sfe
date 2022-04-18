package io.sihuan.modules.attendance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.sihuan.modules.attendance.entity.OaAttJbEntity;
import java.util.List;

/**
 * OA加班表
 *
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-12 10:21:42
 */
public interface OaAttJbService extends IService<OaAttJbEntity> {
    List<OaAttJbEntity> selectAttJb();
    void deleteTbAttJb();
    List<OaAttJbEntity> listAttJb();
}


package io.sihuan.modules.attendance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.sihuan.modules.attendance.entity.OaAttWcsqEntity;
import java.util.List;

/**
 * OA外出表
 *
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-12 09:49:23
 */
public interface OaAttWcsqService extends IService<OaAttWcsqEntity> {
    List<OaAttWcsqEntity> selectAttWcsq();
    void deleteTbAttWcsq();
    List<OaAttWcsqEntity> listAttWcsq();
}


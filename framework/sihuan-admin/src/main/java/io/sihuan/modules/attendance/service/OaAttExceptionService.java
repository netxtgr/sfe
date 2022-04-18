package io.sihuan.modules.attendance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.sihuan.modules.attendance.entity.OaAttExceptionEntity;
import java.util.List;

/**
 * OA异常考勤表
 *
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-11 16:13:52
 */
public interface OaAttExceptionService extends IService<OaAttExceptionEntity> {
    List<OaAttExceptionEntity> selectAttExcetion();
    void deleteTbAttExcetion();
    List<OaAttExceptionEntity> listAttExcetion();
}


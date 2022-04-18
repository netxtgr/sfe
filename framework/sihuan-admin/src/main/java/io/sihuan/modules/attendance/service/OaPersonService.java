package io.sihuan.modules.attendance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.sihuan.modules.attendance.entity.OaPersonEntity;
import io.sihuan.modules.attendance.vo.OaPersonInfoVO;

import java.util.List;

/**
 * 
 *
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-10 13:38:39
 */
public interface OaPersonService extends IService<OaPersonEntity> {
    List<OaPersonInfoVO> selectOaPerson();
    void truncateTable(String tableName);
}


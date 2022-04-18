package io.sihuan.modules.attendance.dao;

import io.sihuan.modules.attendance.entity.OaAttWcsqEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * OA外出表
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-12 09:49:23
 */
@Mapper
public interface OaAttWcsqDao extends BaseMapper<OaAttWcsqEntity> {
    List<OaAttWcsqEntity> selectAttWcsq();
    void deleteTbAttWcsq();
    List<OaAttWcsqEntity> listAttWcsq();
}

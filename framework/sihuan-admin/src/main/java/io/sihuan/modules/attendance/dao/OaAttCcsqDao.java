package io.sihuan.modules.attendance.dao;

import io.sihuan.modules.attendance.entity.OaAttCcsqEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * OA出差表
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-12 08:47:45
 */
@Mapper
public interface OaAttCcsqDao extends BaseMapper<OaAttCcsqEntity> {
    List<OaAttCcsqEntity> selectAttCcsq();
    void deleteTbAttCcsq();
    List<OaAttCcsqEntity> listAttCcsq();
}

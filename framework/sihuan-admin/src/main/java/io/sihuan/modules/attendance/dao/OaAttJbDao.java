package io.sihuan.modules.attendance.dao;

import io.sihuan.modules.attendance.entity.OaAttJbEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * OA加班表
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-12 10:21:42
 */
@Mapper
public interface OaAttJbDao extends BaseMapper<OaAttJbEntity> {
    List<OaAttJbEntity> selectAttJb();
    void deleteTbAttJb();
    List<OaAttJbEntity> listAttJb();
}

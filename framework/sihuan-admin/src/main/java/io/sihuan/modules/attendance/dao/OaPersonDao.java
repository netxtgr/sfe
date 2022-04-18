package io.sihuan.modules.attendance.dao;

import io.sihuan.modules.attendance.entity.OaPersonEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.sihuan.modules.attendance.vo.OaPersonInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-10 13:38:39
 */
@Mapper
public interface OaPersonDao extends BaseMapper<OaPersonEntity> {
    List<OaPersonInfoVO> selectOaPerson();
    void truncateTable(@Param(value = "tableName") String tableName);
}

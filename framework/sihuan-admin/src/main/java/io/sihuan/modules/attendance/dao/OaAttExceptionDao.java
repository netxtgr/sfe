package io.sihuan.modules.attendance.dao;

import io.sihuan.modules.attendance.entity.OaAttExceptionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * OA异常考勤表
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-11 16:13:52
 */
@Mapper
public interface OaAttExceptionDao extends BaseMapper<OaAttExceptionEntity> {
    List<OaAttExceptionEntity> selectAttExcetion();
    void deleteTbAttExcetion();
    List<OaAttExceptionEntity> listAttExcetion();
}

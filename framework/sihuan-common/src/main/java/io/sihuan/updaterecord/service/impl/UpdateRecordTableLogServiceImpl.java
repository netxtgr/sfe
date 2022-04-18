package io.sihuan.updaterecord.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.updaterecord.dao.UpdateRecordTableLogDao;
import io.sihuan.updaterecord.entity.UpdateRecordTableLogEntity;
import io.sihuan.updaterecord.service.UpdateRecordTableLogService;
import org.springframework.stereotype.Service;



@Service("updateRecordTableLogService")
public class UpdateRecordTableLogServiceImpl extends ServiceImpl<UpdateRecordTableLogDao, UpdateRecordTableLogEntity> implements UpdateRecordTableLogService {


}

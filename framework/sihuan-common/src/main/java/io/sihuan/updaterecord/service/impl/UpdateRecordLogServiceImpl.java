package io.sihuan.updaterecord.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.updaterecord.dao.UpdateRecordLogDao;
import io.sihuan.updaterecord.entity.UpdateRecordLogEntity;
import io.sihuan.updaterecord.service.UpdateRecordLogService;
import org.springframework.stereotype.Service;



@Service("updateRecordLogService")
public class UpdateRecordLogServiceImpl extends ServiceImpl<UpdateRecordLogDao, UpdateRecordLogEntity> implements UpdateRecordLogService {

}

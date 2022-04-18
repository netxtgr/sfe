package io.sihuan.updaterecord.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.sihuan.updaterecord.dao.UpdateRecordColumnLogDao;
import io.sihuan.updaterecord.entity.UpdateRecordColumnLogEntity;
import io.sihuan.updaterecord.service.UpdateRecordColumnLogService;
import org.springframework.stereotype.Service;


@Service("updateRecordColumnLogService")
public class UpdateRecordColumnLogServiceImpl extends ServiceImpl<UpdateRecordColumnLogDao, UpdateRecordColumnLogEntity> implements UpdateRecordColumnLogService {


}

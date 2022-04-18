/**
 * 
 *
 * 
 *
 * 版权所有，侵权必究！
 */

package io.sihuan.modules.attendance.task;

import io.sihuan.modules.attendance.entity.*;
import io.sihuan.modules.attendance.service.*;
import io.sihuan.modules.attendance.vo.OaPersonInfoVO;
import io.sihuan.modules.job.task.ITask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * OA数据同步定时任务
 * attendanceGroupUserTask为spring bean的名称
 * @author ljt liujiangtao@sihuanpharm.com
 */
@Component("attendanceOaPersonTask")
public class AttendanceOaPersonTask implements ITask {
	@Autowired
	private OaPersonService oaPersonService;
	@Autowired
	private OaAttLeaveService oaAttLeaveService;
	@Autowired
	private OaAttExceptionService oaAttExceptionService;
	@Autowired
	private OaAttCcsqService oaAttCcsqService;
	@Autowired
	private OaAttWcsqService oaAttWcsqService;
	@Autowired
	private OaAttJbService oaAttJbService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void run(String params){
		logger.info("attendanceOaPersonTask定时任务正在执行 开始......，参数为：{}", params);
		//同步OA用户数据
		syncOaPerson();
		//同步OA请假数据
		syncOaAttLeave();
		//同步OA异常考勤数据
		syncOaAttException();
		//同步OA出差数据
		syncOaAttCcsq();
		//同步OA外出数据
		syncOaAttWcsq();
		//同步OA加班数据
		syncOaAttJb();
		logger.info("attendanceOaPersonTask定时任务正在执行 结束......，参数为：{}", params);
	}

	private void syncOaPerson() {
		oaPersonService.truncateTable("tb_oa_person");
		List<OaPersonInfoVO> persons = oaPersonService.selectOaPerson();
		List<OaPersonEntity> savePersons = new ArrayList<OaPersonEntity>();
		for (OaPersonInfoVO p : persons) {
			OaPersonEntity pe = new OaPersonEntity();
			BeanUtils.copyProperties(p, pe);
			pe.setCreated(new Date());
			savePersons.add(pe);
		}
		oaPersonService.saveBatch(savePersons);
	}

	private void syncOaAttLeave() {
		oaAttLeaveService.deleteTbAttLeave();
		List<OaAttLeaveEntity> leaves = oaAttLeaveService.selectAttLeave();
		List<OaAttLeaveEntity> saveLeaves = new ArrayList<OaAttLeaveEntity>();
		for (OaAttLeaveEntity p : leaves) {
			OaAttLeaveEntity pe = new OaAttLeaveEntity();
			BeanUtils.copyProperties(p, pe);
			pe.setCreated(new Date());
			saveLeaves.add(pe);
		}
		oaAttLeaveService.saveBatch(saveLeaves);
	}

	private void syncOaAttException() {
		oaAttExceptionService.deleteTbAttExcetion();
		List<OaAttExceptionEntity> ls = oaAttExceptionService.selectAttExcetion();
		List<OaAttExceptionEntity> saveLs = new ArrayList<OaAttExceptionEntity>();
		for (OaAttExceptionEntity p : ls) {
			OaAttExceptionEntity pe = new OaAttExceptionEntity();
			BeanUtils.copyProperties(p, pe);
			pe.setCreated(new Date());
			saveLs.add(pe);
		}
		oaAttExceptionService.saveBatch(saveLs);
	}

	private void syncOaAttCcsq() {
		oaAttCcsqService.deleteTbAttCcsq();
		List<OaAttCcsqEntity> ls = oaAttCcsqService.selectAttCcsq();
		List<OaAttCcsqEntity> saveLs = new ArrayList<OaAttCcsqEntity>();
		for (OaAttCcsqEntity p : ls) {
			OaAttCcsqEntity pe = new OaAttCcsqEntity();
			BeanUtils.copyProperties(p, pe);
			pe.setCreated(new Date());
			saveLs.add(pe);
		}
		oaAttCcsqService.saveBatch(saveLs);
	}

	private void syncOaAttWcsq() {
		oaAttWcsqService.deleteTbAttWcsq();
		List<OaAttWcsqEntity> ls = oaAttWcsqService.selectAttWcsq();
		List<OaAttWcsqEntity> saveLs = new ArrayList<OaAttWcsqEntity>();
		for (OaAttWcsqEntity p : ls) {
			OaAttWcsqEntity pe = new OaAttWcsqEntity();
			BeanUtils.copyProperties(p, pe);
			pe.setCreated(new Date());
			saveLs.add(pe);
		}
		oaAttWcsqService.saveBatch(saveLs);
	}

	private void syncOaAttJb() {
		oaAttJbService.deleteTbAttJb();
		List<OaAttJbEntity> ls = oaAttJbService.selectAttJb();
		List<OaAttJbEntity> saveLs = new ArrayList<OaAttJbEntity>();
		for (OaAttJbEntity p : ls) {
			OaAttJbEntity pe = new OaAttJbEntity();
			BeanUtils.copyProperties(p, pe);
			pe.setCreated(new Date());
			saveLs.add(pe);
		}
		oaAttJbService.saveBatch(saveLs);
	}


}

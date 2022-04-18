/**
 * 
 *
 * 
 *
 * 版权所有，侵权必究！
 */

package io.sihuan.modules.attendance.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.sihuan.modules.attendance.entity.AttendanceGroupEntity;
import io.sihuan.modules.attendance.service.AttendanceGroupService;
import io.sihuan.modules.attendance.utils.DingtalkSyncJobUtils;
import io.sihuan.modules.attendance.utils.DingtalkUtils;
import io.sihuan.modules.job.task.ITask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 钉钉考勤组同步定时任务
 * attendanceGroupTask为spring bean的名称
 * @author ljt liujiangtao@sihuanpharm.com
 */
@Component("attendanceGroupUserTask")
public class AttendanceGroupUserTask implements ITask {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public void run(String params){
		logger.info("同步考勤组 开始.................................{}", params);
		DingtalkSyncJobUtils.syncAttGroup();
		logger.info("同步考勤组 结束.................................{}", params);
		logger.info("同步考勤组用户 开始.................................{}", params);
		DingtalkSyncJobUtils.syncAttGroupUser();
		logger.info("同步考勤组用户 结束.................................{}", params);
	}
}

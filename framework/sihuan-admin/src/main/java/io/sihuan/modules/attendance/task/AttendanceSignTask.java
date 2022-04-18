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
import io.sihuan.common.utils.DateUtils;
import io.sihuan.modules.attendance.entity.AttendanceSignEntity;
import io.sihuan.modules.attendance.entity.AttendanceUserEntity;
import io.sihuan.modules.attendance.service.AttendanceSignService;
import io.sihuan.modules.attendance.service.AttendanceUserService;
import io.sihuan.modules.attendance.utils.DingtalkUtils;
import io.sihuan.modules.job.task.ITask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 钉钉签到信息同步定时任务
 * attendanceGroupUserTask为spring bean的名称
 * @author ljt liujiangtao@sihuanpharm.com
 */
@Component("attendanceSignTask")
public class AttendanceSignTask implements ITask {
	@Autowired
	private AttendanceSignService attendanceSignService;
	@Autowired
	private AttendanceUserService attendanceUserService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void run(String params){
		logger.info("attendanceSignTask定时任务正在执行 开始......，参数为：{}", params);
		Date now = new Date();
		String startDate = DateUtils.format(DateUtils.addDateDays(now,-1));
		String endDate = DateUtils.format(now);
		if (params != null) {
			JSONObject j = JSONObject.parseObject(params);
			startDate = j.getString("startDate");
			endDate = j.getString("endDate");
		}
		List<AttendanceUserEntity> users = attendanceUserService.list();
		if (users != null && users.size() > 0) {
			for (AttendanceUserEntity user : users) {
				postSignDetail(user, startDate, endDate);
			}
		}
	}

	private void postSignDetail(AttendanceUserEntity user, String startDate, String endDate) {
		try {
			JSONObject result = JSONObject.parseObject(DingtalkUtils.getAttendanceSign(user.getUserId(), startDate, endDate));
			if (result != null) {
				JSONArray arr = result.getJSONArray("page_list");
				if (arr != null && arr.size() > 0) {
					List<AttendanceSignEntity> signLst = new ArrayList<AttendanceSignEntity>();
					for (int i = 0; i < arr.size(); i++) {
						JSONObject obj = arr.getJSONObject(i);
						AttendanceSignEntity e = obj.toJavaObject(AttendanceSignEntity.class);
						e.setCreated(new Date());
						signLst.add(e);
					}
					attendanceSignService.removeByUserIdDate(user.getUserId(), DateUtils.stringToDate(startDate, "yyyy-MM-dd"), DateUtils.stringToDate(endDate, "yyyy-MM-dd"));
					attendanceSignService.saveBatch(signLst);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}

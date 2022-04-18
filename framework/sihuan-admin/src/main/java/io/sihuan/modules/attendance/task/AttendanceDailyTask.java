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
import io.sihuan.modules.attendance.entity.AttendanceDailyEntity;
import io.sihuan.modules.attendance.entity.AttendanceReportColumnEntity;
import io.sihuan.modules.attendance.entity.AttendanceUserEntity;
import io.sihuan.modules.attendance.service.AttendanceDailyService;
import io.sihuan.modules.attendance.service.AttendanceReportColumnService;
import io.sihuan.modules.attendance.service.AttendanceUserService;
import io.sihuan.modules.attendance.utils.DingtalkUtils;
import io.sihuan.modules.job.task.ITask;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 钉钉每日考勤情况定时任务
 * attendanceGroupUserTask为spring bean的名称
 * @author ljt liujiangtao@sihuanpharm.com
 */
@Component("attendanceDailyTask")
public class AttendanceDailyTask implements ITask {
	@Autowired
	private AttendanceReportColumnService attendanceReportColumnService;

	@Autowired
	private AttendanceUserService attendanceUserService;

	@Autowired
	private AttendanceDailyService attendanceDailyService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void run(String params){
		logger.info("attendanceDailyTask定时任务正在执行 开始......，参数为：{}", params);
		List<AttendanceReportColumnEntity> colParams = attendanceReportColumnService.list();
		HashMap<Integer, String[]> colPropMap = new HashMap<Integer, String[]>();
		StringBuilder cols = new StringBuilder();
		for (AttendanceReportColumnEntity col : colParams) {
			cols.append(",").append(col.getColId());
			colPropMap.put(col.getColId(), new String[] {col.getEntityName(), col.getEntityType()});
		}
		Date now = new Date();
		String startDate = DateUtils.format(DateUtils.addDateDays(now,-2));
		String endDate = DateUtils.format(now);
		if (params != null && !"".equals(params)) {
			JSONObject j = JSONObject.parseObject(params);
			startDate = j.getString("startDate");
			endDate = j.getString("endDate");
		}
		List<AttendanceUserEntity> users = attendanceUserService.list();
		for (AttendanceUserEntity user : users) {
			postAttDaily(user.getUserId(), startDate, endDate, cols.substring(1), colPropMap);
		}
		logger.info("attendanceDailyTask定时任务正在执行 结束......，参数为：{}", params);
	}

	private void postAttDaily(String userId, String startDate, String endDate, String cols, HashMap<Integer, String[]> colPropMap) {
		List<AttendanceDailyEntity> saveAtts = new ArrayList<AttendanceDailyEntity>();
		JSONObject result = JSONObject.parseObject(DingtalkUtils.getAttendanceDaily(cols, userId, startDate, endDate));
		JSONArray arr = result.getJSONArray("column_vals");
		HashMap<String, HashMap<Integer, String>> userDateMap = new HashMap<>();
		if (arr != null && arr.size() > 0) {
			for (int i = 0; i < arr.size(); i++) {
				JSONArray columnVals = arr.getJSONObject(i).getJSONArray("column_vals");
				int colId = arr.getJSONObject(i).getJSONObject("column_vo").getInteger("id");
				for (int j = 0; j < columnVals.size(); j++) {
					String date = columnVals.getJSONObject(j).getString("date");
					String value = columnVals.getJSONObject(j).getString("value");
					String key = userId + "@" + date;
					HashMap<Integer, String> m = userDateMap.get(key);
					if (m == null) {
						m = new HashMap<>();
					}
					m.put(colId, value);
					userDateMap.put(key, m);
				}
			}
		}
		addDaily(userDateMap, colPropMap, saveAtts);
		attendanceDailyService.saveOrUpdateBatch(saveAtts);

	}

	private void addDaily(HashMap<String, HashMap<Integer, String>> userDateMap, HashMap<Integer, String[]> colPropMap, List<AttendanceDailyEntity> saveAtts) {
		for (String key : userDateMap.keySet()) {
			HashMap<Integer, String> colValMap = userDateMap.get(key);
			String userId = key.split("@")[0];
			String attDate = key.split("@")[1];
			AttendanceDailyEntity dailyEntity = attendanceDailyService.getByUserIdAttDate(userId, DateUtils.stringToDate(attDate, "yyyy-MM-dd HH:mm:ss"));
			if (dailyEntity == null) {
				dailyEntity = new AttendanceDailyEntity();
				dailyEntity.setAttDate(DateUtils.stringToDate(attDate, "yyyy-MM-dd HH:mm:ss"));
				dailyEntity.setCreated(new Date());
				dailyEntity.setUserId(userId);
			}
			dailyEntity.setUpdated(new Date());
			for (int colId : colValMap.keySet()) {
				String value = colValMap.get(colId);
				String colProp = colPropMap.get(colId)[0];
				String colPropType = colPropMap.get(colId)[1];

				if (value != null && value != "") {
					if ("String".equals(colPropType)) {
						try {
							BeanUtils.setProperty(dailyEntity, colProp, value);
						} catch (Exception e) {
							logger.error(e.getMessage(), e);
						}
					}
					if ("Integer".equals(colPropType)) {
						int v = 0;
						if (value.indexOf(".") == -1) {
							v = Integer.parseInt(value);
						} else {
							String[] s = value.split("[.]");
							v = Integer.parseInt(s[0]);
						}
						try {
							BeanUtils.setProperty(dailyEntity, colProp, v);
						} catch (Exception e) {
							logger.error(e.getMessage(), e);
						}
					}
				}
			}
			setAttClassInfo(dailyEntity);
			saveAtts.add(dailyEntity);
		}
	}

	private void setAttClassInfo(AttendanceDailyEntity dailyEntity) {
		if (dailyEntity.getAttClass() != null && !"".equals(dailyEntity.getAttClass())) {
			String[] cInfo = dailyEntity.getAttClass().split(" ");
			String dateStr = DateUtils.format(dailyEntity.getAttDate());
			if (cInfo.length == 2) {
				String[] onOff = cInfo[1].split("-");
				dailyEntity.setAttClassOnTime(DateUtils.stringToDate(dateStr + " " + onOff[0] + ":00", "yyyy-MM-dd HH:mm:ss"));
				dailyEntity.setAttClassOffTime(DateUtils.stringToDate(dateStr + " " + onOff[1] + ":00", "yyyy-MM-dd HH:mm:ss"));
			} else if (cInfo.length == 3) {
				String[] onOff1 = cInfo[1].split("-");
				String[] onOff2 = cInfo[2].split("-");
				dailyEntity.setAttClassOnTime(DateUtils.stringToDate(dateStr + " " + onOff1[0] + ":00", "yyyy-MM-dd HH:mm:ss"));
				dailyEntity.setAttClassOffTime(DateUtils.stringToDate(dateStr + " " + onOff2[1] + ":00", "yyyy-MM-dd HH:mm:ss"));
			}
		}
	}
}

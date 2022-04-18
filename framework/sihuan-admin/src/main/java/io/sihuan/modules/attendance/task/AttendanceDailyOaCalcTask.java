/**
 * 
 *
 * 
 *
 * 版权所有，侵权必究！
 */

package io.sihuan.modules.attendance.task;

import io.sihuan.common.utils.DateUtils;
import io.sihuan.modules.attendance.constant.AttConstant;
import io.sihuan.modules.attendance.entity.*;
import io.sihuan.modules.attendance.service.*;
import io.sihuan.modules.job.task.ITask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * 钉钉每日考勤结合OA计算定时任务
 * attendanceGroupUserTask为spring bean的名称
 * @author ljt liujiangtao@sihuanpharm.com
 */
@Component("attendanceDailyOaCalcTask")
public class AttendanceDailyOaCalcTask implements ITask {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private AttendanceDailyService attendanceDailyService;
	@Autowired
	private OaAttCcsqService oaAttCcsqService;
	@Autowired
	private AttendanceSignService attendanceSignService;
	@Autowired
	private OaAttExceptionService oaAttExceptionService;
	@Autowired
	private OaAttJbService oaAttJbService;
	@Autowired
	private OaAttLeaveService oaAttLeaveService;
	@Autowired
	private OaAttWcsqService oaAttWcsqService;
	private final String START_DATE_KEY = "startDate";
	private final String END_DATE_KEY = "endDate";
	private final String ATT_DATE_KEY = "attDate";
	private final String LEAVE_TYPE_KEY = "attDate";
	private HashMap<String, List<HashMap<String, Date>>> ccsqMap = new HashMap<>();
	private HashMap<String, List<HashMap<String, Date>>> expMap = new HashMap<>();
	private HashMap<String, List<HashMap<String, Date>>> jbMap = new HashMap<>();
	private HashMap<String, List<HashMap<String, Object>>> leaveMap = new HashMap<>();
	private HashMap<String, List<HashMap<String, Date>>> wcsqMap = new HashMap<>();
	//private HashMap<String, List<Date>> signMap = new HashMap<>();

	private void initMap() {
		initCcsq();
		initExp();
		initLeave();
		initJb();
		initWcsq();
		//initSign();
	}

	@Override
	public void run(String params){
		logger.info("AttendanceDailyOaCalcTask定时任务正在执行 开始......，参数为：{}", params);
		initMap();
		Date now = new Date();
		String startDate = DateUtils.format(DateUtils.addDateDays(now,-49));
		String endDate = DateUtils.format(now);
		Map<String, String> map = new HashMap<>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
//		String d = DateUtils.format(DateUtils.addDateDays(new Date(),-1));
//		Date[] months = DateUtils.getFirstAndLastDayOfMonth(d);
//		Map<String, String> map = new HashMap<>();
//		map.put("startDate", DateUtils.format(months[0], "yyyy-MM-dd HH:mm:ss"));
//		map.put("endDate", DateUtils.format(months[1], "yyyy-MM-dd HH:mm:ss"));
//		map.put("startDate", "2022-01-12 00:00:00");
//		map.put("endDate", "2022-01-12 00:00:00");
		List<AttendanceDailyEntity> atts = attendanceDailyService.selectMonthAtts(map);
		if (atts != null && atts.size() > 0) {
			for (AttendanceDailyEntity entity : atts) {
				calc(entity);
				entity.setUpdated(new Date());
			}
			attendanceDailyService.saveOrUpdateBatch(atts);
		}
		logger.info("AttendanceDailyOaCalcTask定时任务正在执行 结束......，参数为：{}", params);
	}

	private void calc(AttendanceDailyEntity entity) {
		//休息日
		if (entity.getShouldAttendance() == 0) {
			entity.setCalcType(AttConstant._CALC_TYPE_XX);
			entity.setCalcResult(AttConstant._ZC);
			//加班
			if (checkJb(entity)) {
				entity.setCalcType(AttConstant._CALC_TYPE_JB);
				entity.setCalcResult(AttConstant._ZC);
			}
			return;
		}
		if (entity.getAttResult() == null || entity.getAttResult() == "") {
			return;
		}
		if (AttConstant._ZC.equals(entity.getAttResult())) {
			entity.setCalcType(AttConstant._CALC_TYPE_ZC);
			entity.setCalcResult(AttConstant._ZC);
			return;
		}
		checkExcetionAtt(entity);
		//不在OA异常考勤、外出申请、出差申请、请假申请范围内，未计算出结果的
		if (entity.getCalcType() == null || "".equals(entity.getCalcType())) {
			checkAttOther(entity);
		}
	}

	/**
	 * 加班申请：
	 * 		只校验休息日（包含节假日）加班，只要OA审批通过了就认为加班了，OA加班流程中包含确认打卡信息
	 * 		没有半天加班场景
	 * @param entity
	 * @return
	 */
	private boolean checkJb(AttendanceDailyEntity entity) {
		List<HashMap<String, Date>> lst = jbMap.get(entity.getOaUserId());
		if (lst != null && lst.size() > 0) {
			for (HashMap<String, Date> m : lst) {
				Date jbSday = DateUtils.stringToDate(DateUtils.format(m.get(START_DATE_KEY), DateUtils.DATE_PATTERN), DateUtils.DATE_PATTERN);
				Date jbEday = DateUtils.stringToDate(DateUtils.format(m.get(END_DATE_KEY), DateUtils.DATE_PATTERN), DateUtils.DATE_PATTERN);
				if (entity.getAttDate().getTime() >= jbSday.getTime() && entity.getAttDate().getTime() <= jbEday.getTime()) {
					entity.setJbTimes(AttConstant.DAY_OURS);
					return true;
				}
			}
		}
		return false;
	}

	private void checkAttOther(AttendanceDailyEntity entity) {
		//旷工
		if (entity.getIsAbsenteeism() == 1) {
			entity.setCalcType(AttConstant._CALC_TYPE_KG);
			entity.setCalcResult(AttConstant._KG);
			return;
		}
		//旷工迟到
		if (entity.getIsAbsenteeismLate() == 1) {
			entity.setCalcType(AttConstant._CALC_TYPE_KGCD);
			entity.setCalcResult(AttConstant._KGCD);
			return;
		}
		//严重迟到
		if (entity.getIsSeriousLate() == 1) {
			entity.setCalcType(AttConstant._CALC_TYPE_YZCD);
			entity.setCalcResult(AttConstant._YZCD);
			return;
		}
		//上班缺卡或下班缺卡
		if (!"".equals(AttConstant.getAttOnOffType(entity))) {
			entity.setCalcType(AttConstant._CALC_TYPE_QK);
			entity.setCalcResult(AttConstant._QK);
			return;
		}
		//迟到
		if (entity.getIsLate() == 1) {
			entity.setCalcType(AttConstant._CALC_TYPE_CD);
			entity.setCalcResult(AttConstant._CD);
			return;
		}
		//早退
		if (entity.getIsLeaveEarly() == 1) {
			entity.setCalcType(AttConstant._CALC_TYPE_ZT);
			entity.setCalcResult(AttConstant._ZT);
			return;
		}
		//以上情况都不存在,视为正常
		entity.setCalcType(AttConstant._CALC_TYPE_NO);
		entity.setCalcResult(AttConstant._ZC);
	}

	private void checkExcetionAtt(AttendanceDailyEntity entity) {
		if (entity.getIsAbsenteeism() == 1 || entity.getIsAbsenteeismLate() == 1
				|| entity.getOnDutyUserCheckTime() == null || entity.getOffDutyUserCheckTime() == null
				|| "".equals(entity.getOnDutyUserCheckTime()) || "".equals(entity.getOffDutyUserCheckTime())
				|| entity.getIsLate() == 1 || entity.getIsLeaveEarly() == 1) {
			//异常考勤
			if (checkInExcp(entity.getOaUserId(), entity.getAttDate())) {
				entity.setCalcType(AttConstant._CALC_TYPE_EXCP);
				entity.setCalcResult(AttConstant._ZC);
				return;
			}
			//签到
			/*if (checkSign(entity.getUserId(), entity.getAttDate())) {
				entity.setCalcType(AttConstant._CALC_TYPE_SIGN);
				entity.setCalcResult(AttConstant._ZC);
				return;
			}*/
			//外出
			if(checkInWc(entity)) {
				entity.setCalcType(AttConstant._CALC_TYPE_WC);
				entity.setCalcResult(AttConstant._ZC);
				return;
			}
			//出差
			if(checkInCc(entity)) {
				entity.setCalcType(AttConstant._CALC_TYPE_CC);
				entity.setCalcResult(AttConstant._ZC);
				return;
			}
			if(checkInLeave(entity)) {
				entity.setCalcType(AttConstant._CALC_TYPE_LEAVE);
				entity.setCalcResult(AttConstant._ZC);
				return;
			}
		}
	}

	/**
	 * 外出申请校验，以下几种情况
	 * 	1、上下班时间都为空（整天外出）
	 * 		外出开始时间 <= 排班上班时间 && 外出结束时间 >= 排班下班时间
	 * 	2、上班时间为空，下班时间不为空（上午半天外出）
	 * 		外出开始时间 <= 排班上班时间 && 外出结束时间 >= 排班上班时间
	 *  3、上班时间不为空， 下班时间为空 （下午半天外出）
	 *  	外出开始时间 <= 排班下班时间 && 外出结束时间 >= 排班下班时间
	 *  4、上下班时间都不为空（迟到或早退）
	 *  	这种情况不应该提外出，不考虑
	 * @param entity
	 * @return
	 */
	private boolean checkInWc(AttendanceDailyEntity entity) {
		List<HashMap<String, Date>> lst = wcsqMap.get(entity.getOaUserId());
		if (lst != null && lst.size() > 0) {
			String onOffType = AttConstant.getAttOnOffType(entity);
			for (HashMap<String, Date> m : lst) {
				Date wcStartTime = m.get(START_DATE_KEY);
				Date wcEndTime = m.get(END_DATE_KEY);
				if (AttConstant.ON_OFF_NULL.equals(onOffType)){
					if (wcStartTime.getTime() <= entity.getAttClassOnTime().getTime()
							&& wcEndTime.getTime() >= entity.getAttClassOffTime().getTime()) {
						entity.setWcTimes(AttConstant.DAY_OURS);
						return true;
					}
					if (wcStartTime.getTime() <= entity.getAttClassOnTime().getTime()
							&& wcEndTime.getTime() >= entity.getAttClassOnTime().getTime()) {
						entity.setWcTimes(getTimesSwInfos(entity, wcEndTime));
						entity.setOaLeaveTimes(null);
						return true;
					}
					if (wcStartTime.getTime() <= entity.getAttClassOffTime().getTime()
							&& wcEndTime.getTime() >= entity.getAttClassOffTime().getTime()) {
						entity.setWcTimes(getTimesXwInfos(entity, wcStartTime));
						entity.setOaLeaveTimes(null);
						return true;
					}
				} else if (AttConstant.ON_NULL_OFF_NOT_NULL.equals(onOffType) || entity.getAttResult().indexOf(AttConstant._CD) != -1) {
					if (wcStartTime.getTime() <= entity.getAttClassOnTime().getTime()
							&& wcEndTime.getTime() >= entity.getAttClassOnTime().getTime()) {
						entity.setWcTimes(getTimesSwInfos(entity, wcEndTime));
						entity.setOaLeaveTimes(null);
						return true;
					}
				} else if (AttConstant.ON_NOT_NULL_OFF_NULL.equals(onOffType) || entity.getAttResult().indexOf(AttConstant._ZT) != -1) {
					if (wcStartTime.getTime() <= entity.getAttClassOffTime().getTime()
							&& wcEndTime.getTime() >= entity.getAttClassOffTime().getTime()) {
						entity.setWcTimes(getTimesXwInfos(entity, wcStartTime));
						entity.setOaLeaveTimes(null);
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 出差申请校验：
	 * 	  从数据中看，有很多都是选的时分秒为00:00:00，例如2022-01-10 00:00:00~2022-01-14 00:00:00
	 * 	  那么出差日期应包含2022-01-14
	 * 	  不对比具体时间段，没有半天出差，考勤日期在出差天阶段内，则算为出差，避免OA未选择具体时间段的情况导致考勤异常
	 * 	  出差1天=8.0小时
	 * @param entity
	 * @return
	 */
	private boolean checkInCc(AttendanceDailyEntity entity) {
		List<HashMap<String, Date>> lst = ccsqMap.get(entity.getOaUserId());
		if (lst != null && lst.size() > 0) {
			for (HashMap<String, Date> m : lst) {
				Date ccSday = DateUtils.stringToDate(DateUtils.format(m.get(START_DATE_KEY), DateUtils.DATE_PATTERN), DateUtils.DATE_PATTERN);
				Date ccEday = DateUtils.stringToDate(DateUtils.format(m.get(END_DATE_KEY), DateUtils.DATE_PATTERN), DateUtils.DATE_PATTERN);
				if (entity.getAttDate().getTime() >= ccSday.getTime() && entity.getAttDate().getTime() <= ccEday.getTime()) {
					entity.setCcTimes(AttConstant.DAY_OURS);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 请假申请校验：
	 * 	  请假类型在OA分为：事假|01、年假|06、丧假|08、加班调休|0A、病假*|02、产假*|03、产检*|09、节育假*|0B、工伤假*|07、婚假*|04、陪产假|0C、哺乳假|05
	 * 	1、上下班时间都为空（整天请假）
	 * 		请假开始时间 <= 排班上班时间 && 请假结束时间 >= 排班下班时间
	 * 		上午半天请假：
	 * 			请假开始时间 <= 排班上班时间 && 请假结束时间 >= 排班上班时间
	 * 		下午半天请假：
	 * 			请假开始时间 <= 排班下班时间 && 请假结束时间 >= 排班下班时间
	 * 		请假时长：8.0
	 * 	2、上班时间为空，下班时间不为空（上午半天请假） 或者迟到
	 * 		请假开始时间 <= 排班上班时间 && 请假结束时间 >= 排班上班时间
	 * 		请假时长：
	 *		请假结束时间 >= 排班下班时间
	 *			请假时长 = 8.0
	 *		else
	 *			请假时长 = 请假结束时间 - 排班上班时间
	 *			if (请假结束时间 >= 13:00) {
	 *			    请假时长 = 请假时长 - 0.5
	 *			}
	 *  3、上班时间不为空， 下班时间为空 （下午半天请假） 或者早退
	 *  	请假开始时间 <= 排班下班时间 && 请假结束时间 >= 排班下班时间
	 *  	请假时长：
	 *  	请假开始时间 <= 排班上班时间
	 *  		请假时长 = 8.0
	 *  	else
	 *  		请假时长 = 排班下班时间 - 请假开始时间
	 *			if (请假开始时间 <= 12:30) {
	 *			    请假时长 = 请假时长 - 0.5
	 *			}
	 *  4、上下班时间都不为空（迟到或早退）
	 *  	这种情况不应该提请假，不考虑
	 * @param entity
	 * @return
	 */
	private boolean checkInLeave(AttendanceDailyEntity entity) {
		List<HashMap<String, Object>> lst = leaveMap.get(entity.getOaUserId());
		if (lst != null && lst.size() > 0) {
			String onOffType = AttConstant.getAttOnOffType(entity);
			for (HashMap<String, Object> m : lst) {
				Date leaveStartDate = (Date) m.get(START_DATE_KEY);
				Date leaveEndDate = (Date) m.get(END_DATE_KEY);
				String leaveType = m.get(LEAVE_TYPE_KEY) + "";
				if (AttConstant.ON_OFF_NULL.equals(onOffType)){
					if (leaveStartDate.getTime() <= entity.getAttClassOnTime().getTime()
							&& leaveEndDate.getTime() >= entity.getAttClassOffTime().getTime()) {
						entity.setOaLeaveType(leaveType);
						entity.setOaLeaveTimes(AttConstant.DAY_OURS);
						return true;
					}
					if (leaveStartDate.getTime() <= entity.getAttClassOnTime().getTime()
							&& leaveEndDate.getTime() >= entity.getAttClassOnTime().getTime()) {
						entity.setOaLeaveType(leaveType);
						entity.setOaLeaveTimes(getTimesSwInfos(entity, leaveEndDate));
						return true;
					}
					if (leaveStartDate.getTime() <= entity.getAttClassOffTime().getTime()
							&& leaveEndDate.getTime() >= entity.getAttClassOffTime().getTime()) {
						entity.setOaLeaveType(leaveType);
						entity.setOaLeaveTimes(getTimesXwInfos(entity, leaveStartDate));
						return true;
					}
				} else if (AttConstant.ON_NULL_OFF_NOT_NULL.equals(onOffType) || entity.getAttResult().indexOf(AttConstant._CD) != -1) {
					if (leaveStartDate.getTime() <= entity.getAttClassOnTime().getTime()
							&& leaveEndDate.getTime() >= entity.getAttClassOnTime().getTime()) {
						entity.setOaLeaveType(leaveType);
						entity.setOaLeaveTimes(getTimesSwInfos(entity, leaveEndDate));
						return true;
					}
				} else if (AttConstant.ON_NOT_NULL_OFF_NULL.equals(onOffType) || entity.getAttResult().indexOf(AttConstant._ZT) != -1) {
					if (leaveStartDate.getTime() <= entity.getAttClassOffTime().getTime()
							&& leaveEndDate.getTime() >= entity.getAttClassOffTime().getTime()) {
						entity.setOaLeaveType(leaveType);
						entity.setOaLeaveTimes(getTimesXwInfos(entity, leaveStartDate));
						return true;
					}
				}
			}
		}
		return false;
	}



	/**
	 *  外出/请假 时长计算
	 * 	计算请假时长(上午)
	 * 		请假时长：
	 *		请假结束时间 >= 排班下班时间
	 *			请假时长 = 8.0
	 *		else
	 *			请假时长 = 请假结束时间 - 排班上班时间
	 *			if (请假结束时间 >= 13:00) {
	 *			    请假时长 = 请假时长 - 0.5
	 *			}
	 * @param entity
	 * @param endDate
	 */
	private double getTimesSwInfos(AttendanceDailyEntity entity, Date endDate) {
		Double times = null;
		if (endDate.getTime() >= entity.getAttClassOffTime().getTime()) {
			times = AttConstant.DAY_OURS;
		} else {
			times = DateUtils.calcDateOurs(entity.getAttClassOnTime(), endDate);
			String ld = DateUtils.format(new Date(), DateUtils.DATE_PATTERN) + " " + DateUtils.format(endDate, "HH:mm:ss");
			String l13 = DateUtils.format(new Date(), DateUtils.DATE_PATTERN) + AttConstant._13_OURS;
			if (DateUtils.stringToDate(ld, DateUtils.DATE_TIME_PATTERN).getTime() >= DateUtils.stringToDate(l13, DateUtils.DATE_TIME_PATTERN).getTime()) {
				times = times - 0.5d;
			}
		}
		return times;
	}

	/**	下午请假：
	 	请假时长：
	 *  	请假开始时间 <= 排班上班时间
	 *  		请假时长 = 8.0
	 *  	else
	 *  		请假时长 = 排班下班时间 - 请假开始时间
	 *			if (请假开始时间 <= 12:30) {
	 *			    请假时长 = 请假时长 - 0.5
	 *			}
	 * @param entity
	 * @param startDate
	 */
	private double getTimesXwInfos(AttendanceDailyEntity entity, Date startDate) {
		Double times = null;
		if (startDate.getTime() <= entity.getAttClassOnTime().getTime()) {
			times = AttConstant.DAY_OURS;
		} else {
			times = DateUtils.calcDateOurs(startDate, entity.getAttClassOffTime());
			String ls = DateUtils.format(new Date(), DateUtils.DATE_PATTERN) + " " + DateUtils.format(startDate, "HH:mm:ss");
			String l1230 = DateUtils.format(new Date(), DateUtils.DATE_PATTERN) + AttConstant._1230_OURS;
			if (DateUtils.stringToDate(ls, DateUtils.DATE_TIME_PATTERN).getTime() <= DateUtils.stringToDate(l1230, DateUtils.DATE_TIME_PATTERN).getTime()) {
				times = times - 0.5d;
			}

		}
		return times;
	}

	/**
	 * 异常考勤在申请时上下班时间都是必填成对的
	 * 所以只要有申请并且通过了就视为当天已打卡，不校验申请的具体上下班时间
	 * @param oaUserId
	 * @param attDate
	 * @return
	 */
	private boolean checkInExcp(String oaUserId, Date attDate) {
		List<HashMap<String, Date>> lst = expMap.get(oaUserId);
		if (lst != null && lst.size() > 0) {
			for (HashMap<String, Date> m : lst) {
				//Date onTime = m.get(START_DATE_KEY);
				//Date offTime = m.get(END_DATE_KEY);
				Date expAttDate = m.get(ATT_DATE_KEY);
				if (DateUtils.format(attDate).equals(DateUtils.format(expAttDate))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 钉钉签到为特殊人员准备，没有固定上下班签到时间
	 * 所以只要当天有签到就认为正常打卡
	 * @return
	 */
	/*private boolean checkSign(String userId, Date attDate) {
		List<Date> lst = signMap.get(userId);
		if (lst != null && lst.size() > 0) {
			for (Date d : lst) {
				if (DateUtils.format(attDate).equals(DateUtils.format(d))) {
					return true;
				}
			}
		}
		return false;
	}*/

	private void initCcsq() {
		ccsqMap = new HashMap<>();
		List<OaAttCcsqEntity> ccsqList = oaAttCcsqService.listAttCcsq();
		if (ccsqList != null && ccsqList.size() > 0) {
			for (OaAttCcsqEntity e : ccsqList) {
				HashMap<String, Date> m = new HashMap<>();
				m.put(START_DATE_KEY, e.getStartTime());
				m.put(END_DATE_KEY, e.getEndTime());
				List<HashMap<String, Date>> l = null;
				if (ccsqMap.containsKey(e.getOaUserId())) {
					l = ccsqMap.get(e.getOaUserId());
				} else {
					l = new ArrayList<>();
				}
				l.add(m);
				ccsqMap.put(e.getOaUserId(), l);
			}
		}
	}

	private void initExp() {
		expMap = new HashMap<>();
		List<OaAttExceptionEntity> lst = oaAttExceptionService.listAttExcetion();
		if (lst != null && lst.size() > 0) {
			for (OaAttExceptionEntity e : lst) {
				HashMap<String, Date> m = new HashMap<>();
				m.put(START_DATE_KEY, e.getOnTime());
				m.put(END_DATE_KEY, e.getOffTime());
				m.put(ATT_DATE_KEY, e.getAttDate());
				List<HashMap<String, Date>> l = null;
				if (expMap.containsKey(e.getOaUserId())) {
					l = expMap.get(e.getOaUserId());
				} else {
					l = new ArrayList<>();
				}
				l.add(m);
				expMap.put(e.getOaUserId(), l);
			}
		}
	}

	private void initLeave() {
		leaveMap = new HashMap<>();
		List<OaAttLeaveEntity> lst = oaAttLeaveService.listAttLeave();
		if (lst != null && lst.size() > 0) {
			for (OaAttLeaveEntity e : lst) {
				HashMap<String, Object> m = new HashMap<>();
				m.put(START_DATE_KEY, e.getStartTime());
				m.put(END_DATE_KEY, e.getEndTime());
				m.put(LEAVE_TYPE_KEY, e.getLeaveType());
				List<HashMap<String, Object>> l = null;
				if (leaveMap.containsKey(e.getOaUserId())) {
					l = leaveMap.get(e.getOaUserId());
				} else {
					l = new ArrayList<>();
				}
				l.add(m);
				leaveMap.put(e.getOaUserId(), l);
			}
		}
	}

	private void initJb() {
		jbMap = new HashMap<>();
		List<OaAttJbEntity> lst = oaAttJbService.listAttJb();
		if (lst != null && lst.size() > 0) {
			for (OaAttJbEntity e : lst) {
				HashMap<String, Date> m = new HashMap<>();
				m.put(START_DATE_KEY, e.getStartTime());
				m.put(END_DATE_KEY, e.getEndTime());
				List<HashMap<String, Date>> l = null;
				if (jbMap.containsKey(e.getOaUserId())) {
					l = jbMap.get(e.getOaUserId());
				} else {
					l = new ArrayList<>();
				}
				l.add(m);
				jbMap.put(e.getOaUserId(), l);
			}
		}
	}

	private void initWcsq() {
		wcsqMap = new HashMap<>();
		List<OaAttWcsqEntity> lst = oaAttWcsqService.listAttWcsq();
		if (lst != null && lst.size() > 0) {
			for (OaAttWcsqEntity e : lst) {
				HashMap<String, Date> m = new HashMap<>();
				m.put(START_DATE_KEY, e.getStartTime());
				m.put(END_DATE_KEY, e.getEndTime());
				List<HashMap<String, Date>> l = null;
				if (wcsqMap.containsKey(e.getOaUserId())) {
					l = wcsqMap.get(e.getOaUserId());
				} else {
					l = new ArrayList<>();
				}
				l.add(m);
				wcsqMap.put(e.getOaUserId(), l);
			}
		}
	}

	/*private void initSign() {
		signMap = new HashMap<>();
		List<AttendanceSignEntity> lst = attendanceSignService.listAttSign();
		if (lst != null && lst.size() > 0) {
			for (AttendanceSignEntity e : lst) {
				List<Date> l = null;
				if (signMap.containsKey(e.getUserId())) {
					l = signMap.get(e.getUserId());
				} else {
					l = new ArrayList<>();
				}
				l.add(e.getCheckinTime());
				signMap.put(e.getUserId(), l);
			}
		}
	}*/
}

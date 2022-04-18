package io.sihuan.modules.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 钉钉每日考勤结果
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-05 10:06:06
 */
@Data
@TableName("tb_attendance_daily")
public class AttendanceDailyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Long id;
	/**
	 * 钉钉用户ID
	 */
	private String userId;
	/**
	 * 考勤日期
	 */
	private Date attDate;
	/**
	 * 考勤排班
	 */
	private String attClass;
	/**
	 * 排班上班时间
	 */
	private Date attClassOnTime;

	/**
	 * 排班下班时间
	 */
	private Date attClassOffTime;

	/**
	 * 应出勤 1：是 0：否
	 */
	private Integer shouldAttendance;
	/**
	 * 上班打卡时间
	 */
	private String onDutyUserCheckTime;
	/**
	 * 上班打卡结果
	 */
	private String onDutyUserCheckResult;
	/**
	 * 下班打卡时间
	 */
	private String offDutyUserCheckTime;
	/**
	 * 下班打卡结果
	 */
	private String offDutyUserCheckResult;
	/**
	 * 是否旷工 1：是 0：否
	 */
	private Integer isAbsenteeism;
	/**
	 * 是否迟到 1：是 0：否
	 */
	private Integer isLate;
	/**
	 * 是否早退 1：是 0：否
	 */
	private Integer isLeaveEarly;
	/**
	 * 是否严重迟到 1：是 0：否
	 */
	private Integer isSeriousLate;

	/**
	 * 是否旷工迟到 1：是 0：否
	 */
	private Integer isAbsenteeismLate;

	/**
	 * 考勤结果
	 */
	private String attResult;

	private String oaUserId;

	private String oaLeaveType;

	private Double oaLeaveTimes;
	private Double wcTimes;
	private Double ccTimes;
	private Double jbTimes;

	private String calcType;

	private String calcResult;
	/**
	 * 创建时间
	 */
	private Date created;
	/**
	 * 更新时间
	 */
	private Date updated;

}

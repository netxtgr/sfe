package io.sihuan.modules.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 需统计考勤组
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-12 14:41:41
 */
@Data
@TableName("tb_attendance_report_group")
public class AttendanceReportGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Integer id;
	/**
	 * 考勤组ID
	 */
	private Integer groupId;
	/**
	 * 考勤组名称
	 */
	private String groupName;
	/**
	 * 创建时间
	 */
	private Date created;

}

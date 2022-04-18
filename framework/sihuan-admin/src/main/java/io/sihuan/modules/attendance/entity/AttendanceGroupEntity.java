package io.sihuan.modules.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-05 10:06:06
 */
@Data
@TableName("tb_attendance_group")
public class AttendanceGroupEntity implements Serializable {
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
	 * 考勤组名字
	 */
	private String groupName;
	/**
	 * 创建时间
	 */
	private Date created;
	/**
	 * 更新时间
	 */
	private Date updated;

}

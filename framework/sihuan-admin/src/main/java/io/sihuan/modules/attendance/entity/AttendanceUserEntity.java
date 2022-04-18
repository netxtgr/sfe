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
@TableName("tb_attendance_user")
public class AttendanceUserEntity implements Serializable {
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
	 * 考勤组ID
	 */
	private Integer groupId;
	/**
	 * 姓名
	 */
	private String empName;
	/**
	 * 手机号
	 */
	private String mobilePhone;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 创建时间
	 */
	private Date created;
	/**
	 * 更新时间
	 */
	private Date updated;

}

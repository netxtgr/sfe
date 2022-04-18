package io.sihuan.modules.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 钉钉签到信息
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-06 09:23:17
 */
@Data
@TableName("tb_attendance_sign")
public class AttendanceSignEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Long id;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 签到时间
	 */
	private Date checkinTime;
	/**
	 * 签到位置
	 */
	private String place;
	/**
	 * 签到详细地址
	 */
	private String detailPlace;
	/**
	 * 创建时间
	 */
	private Date created;

}

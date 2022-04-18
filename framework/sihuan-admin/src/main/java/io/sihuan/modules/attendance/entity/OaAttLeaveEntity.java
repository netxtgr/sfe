package io.sihuan.modules.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * OA请假数据
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-10 14:54:56
 */
@Data
@TableName("tb_oa_att_leave")
public class OaAttLeaveEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Long id;
	/**
	 * OA用户ID
	 */
	private String oaUserId;
	/**
	 * 请假类型：
		事假|01
		年假|06
		丧假|08
		加班调休|0A
		病假*|02
		产假*|03
		产检*|09
		节育假*|0B
		工伤假*|07
		婚假*|04
		陪产假|0C
		哺乳假|05
	 */
	private String leaveType;
	/**
	 * 请假开始时间
	 */
	private Date startTime;
	/**
	 * 请假结束时间
	 */
	private Date endTime;
	/**
	 * 请假时长
	 */
	private Double times;
	/**
	 * 请假说明
	 */
	private String remark;
	/**
	 * oa流程id
	 */
	private String wfId;

	/**
	 * 创建时间
	 */
	private Date created;

	private String oaWfType;
}

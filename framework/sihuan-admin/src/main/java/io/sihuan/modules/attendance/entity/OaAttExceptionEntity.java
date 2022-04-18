package io.sihuan.modules.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * OA异常考勤表
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-11 16:13:52
 */
@Data
@TableName("tb_oa_att_exception")
public class OaAttExceptionEntity implements Serializable {
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
	 * OA异常考勤流程ID
	 */
	private String wfId;
	/**
	 * 异常考勤日期
	 */
	private Date attDate;
	/**
	 * 上班时间
	 */
	private Date onTime;
	/**
	 * 下班时间
	 */
	private Date offTime;
	/**
	 * 异常考勤说明
	 */
	private String remark;
	/**
	 * 数据创建时间
	 */
	private Date created;

	private String oaWfType;

}

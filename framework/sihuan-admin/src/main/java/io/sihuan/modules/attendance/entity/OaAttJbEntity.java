package io.sihuan.modules.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * OA加班表
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-12 10:21:42
 */
@Data
@TableName("tb_oa_att_jb")
public class OaAttJbEntity implements Serializable {
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
	 * OA加班流程ID
	 */
	private String wfId;
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;

	/**
	 * 加班时长
	 */
	private Double times;

	/**
	 * 加班类型 10:周末加班 11:节假日加班 12:平时加班
	 */
	private String jbType;
	/**
	 * 加班原因
	 */
	private String remark;
	/**
	 * 数据创建时间
	 */
	private Date created;

	private String oaWfType;
}

package io.sihuan.modules.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * OA外出表
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-12 09:49:23
 */
@Data
@TableName("tb_oa_att_wcsq")
public class OaAttWcsqEntity implements Serializable {
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
	 * OA外出流程ID
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
	 * 平时考勤地点
	 */
	private String attAddr;
	/**
	 * 外出原因
	 */
	private String remark;
	/**
	 * 数据创建时间
	 */
	private Date created;

	private String oaWfType;

}

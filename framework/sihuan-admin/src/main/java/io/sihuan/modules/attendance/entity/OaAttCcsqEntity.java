package io.sihuan.modules.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * OA出差表
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-12 08:47:45
 */
@Data
@TableName("tb_oa_att_ccsq")
public class OaAttCcsqEntity implements Serializable {
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
	 * OA出差流程ID
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
	 * 出差说明
	 */
	private String remark;
	/**
	 * 数据创建时间
	 */
	private Date created;

	private String oaWfType;

}

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
 * @date 2022-01-10 13:38:39
 */
@Data
@TableName("tb_oa_person")
public class OaPersonEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Integer id;

	/**
	 * OA用户ID
	 */
	private String fdId;
	/**
	 * 姓名
	 */
	private String empName;
	/**
	 * 手机号码
	 */
	private String mobilePhone;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 部门
	 */
	private String deptName;

	/**
	 * 公司
	 */
	private String companyName;

	/**
	 * 创建时间
	 */
	private Date created;

}

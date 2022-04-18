package io.sihuan.modules.attendance.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 钉钉每日考勤结果
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-10 10:06:06
 */
@Data
public class OaPersonInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * OA用户ID
	 */
	private String fdId;
	private String empName;
	private String mobilePhone;
	private String email;
	private String deptName;
	private String companyName;
}

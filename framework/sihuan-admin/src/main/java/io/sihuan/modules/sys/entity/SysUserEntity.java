/**
 * 版权所有，侵权必究！
 */

package io.sihuan.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sihuan.common.validator.group.AddGroup;
import io.sihuan.common.validator.group.UpdateGroup;
import io.sihuan.dynamicexcel.common.*;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户
 *
 * @author ljt liujiangtao@sihuanpharm.com
 */
@Data
@TableName("sys_user")
/*@Tab(   name = "用户表",//导出的excel文件的名称
		version = Constant.EXCEL_VERSION_07,//导出excel的版本，默认03版本扩展名xls结尾
		sheetRowNum = 65536,//导出excel的每个sheet存储导出内容数据的行数（不包含表头行数）
		openMerger=false,//导出excel的内容是否开启同一列连续相同内容自动合并，默认false
		openSubtitle=true,//导出excel的内容是否开启自动填充需要导出的每个属性字段作为表头，默认false
		headerFont = HeaderFont.DEFAULT,//导出excel的表头字体 默认样式，可不写
		headerStyle= HeaderStyle.DEFAULT,//导出excel的表头样式 默认样式，可不写
		contextFont= ContextFont.DEFAULT,//导出excel的内容字体 默认样式，可不写
		contextStyle= ContextStyle.DEFAULT,//导出excel的内容样式 默认样式，可不写
		autoSizeColumn=true//自动扩宽列，默认自动扩展，可不写
)*/
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	@TableId
	private Long userId;

	/**
	 * 用户名
	 */
	@NotBlank(message="用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String username;

	/**
	 * 密码
	 */
	@NotBlank(message="密码不能为空", groups = AddGroup.class)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	/**
	 * 盐
	 */
	private String salt;

	/**
	 * 邮箱
	 */
	@NotBlank(message="邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@Email(message="邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
	private String email;

	/**
	 * 手机号
	 */
	@NotBlank(message="手机号不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String mobile;
	/**
	 * 姓名
	 */
	@NotBlank(message="姓名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String empName;

	/**
	 * 状态  0：禁用   1：正常
	 */
	private Integer status;
	
	/**
	 * 角色ID列表
	 */
	@TableField(exist=false)
	private List<Long> roleIdList;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 部门ID
	 */
	@NotNull(message="部门不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private Long deptId;

	/**
	 * 部门名称
	 */
	@TableField(exist=false)
	private String deptName;

	private String createdBy;
	private Date updateTime;
	private String updatedBy;

}

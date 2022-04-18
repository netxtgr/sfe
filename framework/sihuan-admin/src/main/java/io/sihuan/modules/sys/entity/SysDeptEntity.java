/**
 * 版权所有，侵权必究！
 */

package io.sihuan.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.sihuan.common.validator.group.AddGroup;
import io.sihuan.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 部门管理
 *
 * @author ljt liujiangtao@sihuanpharm.com
 */
@Data
@TableName("sys_dept")
public class SysDeptEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 部门ID
	 */
	@TableId
	private Long deptId;
	/**
	 * 上级部门ID，一级部门为0
	 */
	private Long parentId;
	/**
	 * 部门名称
	 */
	@NotBlank(message="部门名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	/**
	 * 部门编码
	 */
	@NotBlank(message="部门编码不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String deptCode;
	/**
	 * 上级部门名称
	 */
	@TableField(exist=false)
	private String parentName;
	private Integer orderNum;
	@TableLogic
	private Integer delFlag;
	/**
	 * ztree属性
	 */
	@TableField(exist=false)
	private Boolean open;
	@TableField(exist=false)
	private List<?> list;

	/**
	 * 创建时间
	 */
	private Date createTime;
	private String createdBy;
	private Date updateTime;
	private String updatedBy;
}

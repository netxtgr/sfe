package io.sihuan.updaterecord.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ${comments}
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-03-17 14:21:33
 */
@Data
@TableName("update_record_log")
public class UpdateRecordLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId(type = IdType.UUID)
	private String id;
	/**
	 * $column.comments
	 */
	private String commitId;
	/**
	 * $column.comments
	 */
	private String userId;
	/**
	 * $column.comments
	 */
	private String userName;
	/**
	 * $column.comments
	 */
	private String name;
	/**
	 * $column.comments
	 */
	private String ip;
	/**
	 * $column.comments
	 */
	private String area;
	/**
	 * $column.comments
	 */
	private String path;
	/**
	 * $column.comments
	 */
	private String url;
	/**
	 * $column.comments
	 */
	private String serverName;
	/**
	 * $column.comments
	 */
	private String moduleName;
	/**
	 * $column.comments
	 */
	private String packageName;
	/**
	 * $column.comments
	 */
	private String className;
	/**
	 * $column.comments
	 */
	private String methodName;
	/**
	 * $column.comments
	 */
	private String requestMethod;
	/**
	 * $column.comments
	 */
	private String token;
	/**
	 * $column.comments
	 */
	private String threadName;
	/**
	 * $column.comments
	 */
	private String beforeAllValue;
	/**
	 * $column.comments
	 */
	private String afterAllValue;
	/**
	 * $column.comments
	 */
	private String diffAllValue;
	/**
	 * $column.comments
	 */
	private String updateAllDesc;
	/**
	 * $column.comments
	 */
	private Integer tableTotal;
	/**
	 * $column.comments
	 */
	private Integer columnTotal;
	/**
	 * $column.comments
	 */
	private Integer addModelTotal;
	/**
	 * $column.comments
	 */
	private Integer updateModelTotal;
	/**
	 * $column.comments
	 */
	private Integer deleteModelTotal;
	/**
	 * $column.comments
	 */
	private String remark;
	/**
	 * $column.comments
	 */
	private String version;
	/**
	 * $column.comments
	 */
	private Date createTime;
	/**
	 * $column.comments
	 */
	private Date updateTime;

}

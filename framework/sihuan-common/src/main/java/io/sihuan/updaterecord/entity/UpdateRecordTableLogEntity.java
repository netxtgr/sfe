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
@TableName("update_record_table_log")
public class UpdateRecordTableLogEntity implements Serializable {
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
	private String serverName;
	/**
	 * $column.comments
	 */
	private String moduleName;
	/**
	 * $column.comments
	 */
	private String methodId;
	/**
	 * $column.comments
	 */
	private String tableName;
	/**
	 * $column.comments
	 */
	private String tableDesc;
	/**
	 * $column.comments
	 */
	private String entityName;
	/**
	 * $column.comments
	 */
	private String idColumnName;
	/**
	 * $column.comments
	 */
	private String idPropertyName;
	/**
	 * $column.comments
	 */
	private String idValue;
	/**
	 * $column.comments
	 */
	private String beforeValue;
	/**
	 * $column.comments
	 */
	private String afterValue;
	/**
	 * $column.comments
	 */
	private String diffValue;
	/**
	 * $column.comments
	 */
	private String beforeVersion;
	/**
	 * $column.comments
	 */
	private String afterVersion;
	/**
	 * $column.comments
	 */
	private String updateDesc;
	/**
	 * $column.comments
	 */
	private Integer total;
	/**
	 * $column.comments
	 */
	private Integer addModeCount;
	/**
	 * $column.comments
	 */
	private Integer updateModeCount;
	/**
	 * $column.comments
	 */
	private Integer deleteModeCount;
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

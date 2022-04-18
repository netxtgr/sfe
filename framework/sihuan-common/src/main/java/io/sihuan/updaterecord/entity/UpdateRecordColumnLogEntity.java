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
@TableName("update_record_column_log")
public class UpdateRecordColumnLogEntity implements Serializable {
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
	private String tableName;
	/**
	 * $column.comments
	 */
	private String idValue;
	/**
	 * $column.comments
	 */
	private String columnName;
	/**
	 * $column.comments
	 */
	private String columnDesc;
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
	private Integer mode;
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

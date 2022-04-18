package io.sihuan.modules.attendance.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 钉钉考勤报表接口列定义
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-05 10:06:06
 */
@Data
@TableName("tb_attendance_report_column")
public class AttendanceReportColumnEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Integer id;
	/**
	 * 考勤报表接口列ID
	 */
	private Integer colId;
	/**
	 * 考勤报表列名称
	 */
	private String colName;
	/**
	 * 对应实体字段名
	 */
	private String entityName;

	/**
	 * 对应实体字段类型
	 */
	private String entityType;
}

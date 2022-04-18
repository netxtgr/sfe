package io.sihuan.modules.sfe.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.sihuan.updaterecord.annotation.UpdateRecordColumn;
import io.sihuan.updaterecord.annotation.UpdateRecordId;
import io.sihuan.updaterecord.annotation.UpdateRecordTable;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author Tiger Cheung
 * @email Tiger.Chang@outlook.com
 * @date 2022-04-18 15:24:26
 */
@Data
@TableName("sfe_terminal_linkman")
@UpdateRecordTable(name = "sfe_terminal_linkman", value = "")
public class TerminalLinkmanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一标识
	 */
	@TableId
@UpdateRecordId
			private Integer id;
	/**
	 * 姓名
	 */
		@UpdateRecordColumn(name = "linkman_name", value = "姓名")
		private String linkmanName;
	/**
	 * 性别
	 */
		@UpdateRecordColumn(name = "gender", value = "性别")
		private Integer gender;
	/**
	 * 电话
	 */
		@UpdateRecordColumn(name = "mobile", value = "电话")
		private String mobile;
	/**
	 * 籍贯
	 */
		@UpdateRecordColumn(name = "native_place", value = "籍贯")
		private String nativePlace;
	/**
	 * 机构编号
	 */
		@UpdateRecordColumn(name = "division_number", value = "机构编号")
		private String divisionNumber;
	/**
	 * 机构名称
	 */
		@UpdateRecordColumn(name = "division_name", value = "机构名称")
		private String divisionName;
	/**
	 * 职位
	 */
		@UpdateRecordColumn(name = "position", value = "职位")
		private String position;
	/**
	 * 状态
	 */
		@UpdateRecordColumn(name = "on_job", value = "状态")
		private Integer onJob;
	/**
	 * 创建人编号
	 */
		@UpdateRecordColumn(name = "creator_code", value = "创建人编号")
		private String creatorCode;
	/**
	 * 创建人姓名
	 */
		@UpdateRecordColumn(name = "creator_name", value = "创建人姓名")
		private String creatorName;
	/**
	 * 创建时间
	 */
		@UpdateRecordColumn(name = "created", value = "创建时间")
		private Date created;
	/**
	 * 数据有效
	 */
		@UpdateRecordColumn(name = "valid", value = "数据有效")
		private Integer valid;

}

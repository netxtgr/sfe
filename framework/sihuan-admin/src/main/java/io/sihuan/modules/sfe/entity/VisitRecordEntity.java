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
 * @date 2022-04-18 15:24:25
 */
@Data
@TableName("sfe_visit_record")
@UpdateRecordTable(name = "sfe_visit_record", value = "")
public class VisitRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 记录标识
	 */
	@TableId
@UpdateRecordId
			private Long id;
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
	 * 客户编号
	 */
		@UpdateRecordColumn(name = "linkman_code", value = "客户编号")
		private String linkmanCode;
	/**
	 * 客户姓名
	 */
		@UpdateRecordColumn(name = "linkman_name", value = "客户姓名")
		private String linkmanName;
	/**
	 * 客户电话
	 */
		@UpdateRecordColumn(name = "linkman_mobile", value = "客户电话")
		private String linkmanMobile;
	/**
	 * 拜访时间
	 */
		@UpdateRecordColumn(name = "visit_time", value = "拜访时间")
		private Date visitTime;
	/**
	 * 拜访说明
	 */
		@UpdateRecordColumn(name = "description", value = "拜访说明")
		private String description;
	/**
	 * 拜访人编号
	 */
		@UpdateRecordColumn(name = "creator_code", value = "拜访人编号")
		private String creatorCode;
	/**
	 * 拜访人姓名
	 */
		@UpdateRecordColumn(name = "creator_name", value = "拜访人姓名")
		private String creatorName;

}

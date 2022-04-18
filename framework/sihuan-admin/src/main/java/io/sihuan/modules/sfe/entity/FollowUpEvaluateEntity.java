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
@TableName("sfe_follow_up_evaluate")
@UpdateRecordTable(name = "sfe_follow_up_evaluate", value = "")
public class FollowUpEvaluateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据标识
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
	 * 被随访人编号
	 */
		@UpdateRecordColumn(name = "examinee_code", value = "被随访人编号")
		private String examineeCode;
	/**
	 * 被随访人姓名
	 */
		@UpdateRecordColumn(name = "examinee_name", value = "被随访人姓名")
		private String examineeName;
	/**
	 * 随访人编号
	 */
		@UpdateRecordColumn(name = "director_number", value = "随访人编号")
		private String directorNumber;
	/**
	 * 随访人姓名
	 */
		@UpdateRecordColumn(name = "director_name", value = "随访人姓名")
		private String directorName;
	/**
	 * 改善工作的描述
	 */
		@UpdateRecordColumn(name = "description", value = "改善工作的描述")
		private String description;
	/**
	 * 随访总结
	 */
		@UpdateRecordColumn(name = "summary", value = "随访总结")
		private String summary;
	/**
	 * 记录随访时间
	 */
		@UpdateRecordColumn(name = "created", value = "记录随访时间")
		private Date created;
	/**
	 * 下次随访时间
	 */
		@UpdateRecordColumn(name = "next_time", value = "下次随访时间")
		private Date nextTime;

}

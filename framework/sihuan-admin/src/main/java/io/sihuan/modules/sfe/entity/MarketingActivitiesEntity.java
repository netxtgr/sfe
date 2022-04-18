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
@TableName("sfe_marketing_activities")
@UpdateRecordTable(name = "sfe_marketing_activities", value = "")
public class MarketingActivitiesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 活动标识
	 */
	@TableId
@UpdateRecordId
			private Integer id;
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
	 * 活动名称
	 */
		@UpdateRecordColumn(name = "activity_name", value = "活动名称")
		private String activityName;
	/**
	 * 活动时间
	 */
		@UpdateRecordColumn(name = "activity_time", value = "活动时间")
		private Date activityTime;
	/**
	 * 活动目的
	 */
		@UpdateRecordColumn(name = "activity_intent", value = "活动目的")
		private String activityIntent;
	/**
	 * 客户类型
	 */
		@UpdateRecordColumn(name = "consumer_type", value = "客户类型")
		private String consumerType;
	/**
	 * 事项内容
	 */
		@UpdateRecordColumn(name = "material", value = "事项内容")
		private String material;
	/**
	 * 活动说明
	 */
		@UpdateRecordColumn(name = "description", value = "活动说明")
		private String description;
	/**
	 * 活动开始时间
	 */
		@UpdateRecordColumn(name = "start_time", value = "活动开始时间")
		private Date startTime;
	/**
	 * 活动结束时间
	 */
		@UpdateRecordColumn(name = "end_time", value = "活动结束时间")
		private Date endTime;
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
	 * 数据有效（1：数据有效|0：逻辑删除）
	 */
		@UpdateRecordColumn(name = "valid", value = "数据有效（1：数据有效|0：逻辑删除）")
		private Integer valid;

}

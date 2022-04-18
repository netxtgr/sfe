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
@TableName("sfe_marketing_activities_detail")
@UpdateRecordTable(name = "sfe_marketing_activities_detail", value = "")
public class MarketingActivitiesDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据标识
	 */
	@TableId
@UpdateRecordId
			private Integer id;
	/**
	 * 活动标识
	 */
		@UpdateRecordColumn(name = "activity_id", value = "活动标识")
		private Integer activityId;
	/**
	 * 活动名称
	 */
		@UpdateRecordColumn(name = "activity_name", value = "活动名称")
		private String activityName;
	/**
	 * 活动状态（未开始|进行中|已结束）
	 */
		@UpdateRecordColumn(name = "status", value = "活动状态（未开始|进行中|已结束）")
		private Integer status;
	/**
	 * 活动反馈
	 */
		@UpdateRecordColumn(name = "feedback", value = "活动反馈")
		private String feedback;
	/**
	 * 反馈人编号
	 */
		@UpdateRecordColumn(name = "creator_code", value = "反馈人编号")
		private String creatorCode;
	/**
	 * 反馈人姓名
	 */
		@UpdateRecordColumn(name = "creator_name", value = "反馈人姓名")
		private String creatorName;
	/**
	 * 反馈时间
	 */
		@UpdateRecordColumn(name = "created", value = "反馈时间")
		private Date created;
	/**
	 * 关联图片标识
	 */
		@UpdateRecordColumn(name = "image_id", value = "关联图片标识")
		private String imageId;

}

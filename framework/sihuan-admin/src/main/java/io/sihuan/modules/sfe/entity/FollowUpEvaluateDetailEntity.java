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
@TableName("sfe_follow_up_evaluate_detail")
@UpdateRecordTable(name = "sfe_follow_up_evaluate_detail", value = "")
public class FollowUpEvaluateDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据标识
	 */
	@TableId
@UpdateRecordId
			private Long id;
	/**
	 * 关联随访记录
	 */
		@UpdateRecordColumn(name = "follow_up_evaluate_id", value = "关联随访记录")
		private Long followUpEvaluateId;
	/**
	 * 评价类型（计分|改进），在字典表维护code和title
	 */
		@UpdateRecordColumn(name = "evaluate_type", value = "评价类型（计分|改进），在字典表维护code和title")
		private String evaluateType;
	/**
	 * 评价类型代码
	 */
		@UpdateRecordColumn(name = "item_code", value = "评价类型代码")
		private String itemCode;
	/**
	 * 评价类型的标题
	 */
		@UpdateRecordColumn(name = "item_name", value = "评价类型的标题")
		private String itemName;
	/**
	 * 评价得分
	 */
		@UpdateRecordColumn(name = "score", value = "评价得分")
		private Double score;
	/**
	 * 改进工作的描述
	 */
		@UpdateRecordColumn(name = "description", value = "改进工作的描述")
		private String description;
	/**
	 * 记录时间
	 */
		@UpdateRecordColumn(name = "created", value = "记录时间")
		private Date created;

}

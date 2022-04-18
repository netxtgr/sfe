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
@TableName("sfe_cooperation_department")
@UpdateRecordTable(name = "sfe_cooperation_department", value = "")
public class CooperationDepartmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据标识
	 */
	@TableId
@UpdateRecordId
			private Integer id;
	/**
	 * 协作主题
	 */
		@UpdateRecordColumn(name = "topic", value = "协作主题")
		private String topic;
	/**
	 * 涉及部门
	 */
		@UpdateRecordColumn(name = "department", value = "涉及部门")
		private String department;
	/**
	 * 反馈内容
	 */
		@UpdateRecordColumn(name = "feedback", value = "反馈内容")
		private String feedback;
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

}

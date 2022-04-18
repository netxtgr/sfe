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
@TableName("sfe_visit_record_detail")
@UpdateRecordTable(name = "sfe_visit_record_detail", value = "")
public class VisitRecordDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 明细记录标识
	 */
	@TableId
@UpdateRecordId
			private Long id;
	/**
	 * 主记录标识
	 */
		@UpdateRecordColumn(name = "visit_record_id", value = "主记录标识")
		private Long visitRecordId;
	/**
	 * 标准内容项代码
	 */
		@UpdateRecordColumn(name = "item_code", value = "标准内容项代码")
		private String itemCode;
	/**
	 * 标准内容项名称
	 */
		@UpdateRecordColumn(name = "item_name", value = "标准内容项名称")
		private String itemName;
	/**
	 * 拜访说明
	 */
		@UpdateRecordColumn(name = "description", value = "拜访说明")
		private String description;
	/**
	 * 记录时间
	 */
		@UpdateRecordColumn(name = "created", value = "记录时间")
		private Date created;

}

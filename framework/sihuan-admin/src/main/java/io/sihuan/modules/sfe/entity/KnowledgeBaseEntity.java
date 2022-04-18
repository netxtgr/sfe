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
@TableName("sfe_knowledge_base")
@UpdateRecordTable(name = "sfe_knowledge_base", value = "")
public class KnowledgeBaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据标识
	 */
	@TableId
@UpdateRecordId
			private Integer id;
	/**
	 * 文档或问答名称、主题
	 */
		@UpdateRecordColumn(name = "topic", value = "文档或问答名称、主题")
		private String topic;
	/**
	 * 内容简述或常见问题回复
	 */
		@UpdateRecordColumn(name = "description", value = "内容简述或常见问题回复")
		private String description;
	/**
	 * 如果是文件形式，则保存获取文件的方式
	 */
		@UpdateRecordColumn(name = "uri", value = "如果是文件形式，则保存获取文件的方式")
		private String uri;
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

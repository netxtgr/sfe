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
@TableName("sfe_images")
@UpdateRecordTable(name = "sfe_images", value = "")
public class ImagesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 图片标识
	 */
	@TableId
@UpdateRecordId
			private Long id;
	/**
	 * 关联图片标识
	 */
		@UpdateRecordColumn(name = "image_id", value = "关联图片标识")
		private String imageId;
	/**
	 * 图片名称（如果有）
	 */
		@UpdateRecordColumn(name = "image_name", value = "图片名称（如果有）")
		private String imageName;
	/**
	 * 文件形式保存的具体位置
	 */
		@UpdateRecordColumn(name = "uri", value = "文件形式保存的具体位置")
		private String uri;
	/**
	 * 创建时间
	 */
		@UpdateRecordColumn(name = "created", value = "创建时间")
		private Date created;

}

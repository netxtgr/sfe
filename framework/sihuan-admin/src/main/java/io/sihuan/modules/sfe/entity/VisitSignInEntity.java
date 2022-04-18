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
@TableName("sfe_visit_sign_in")
@UpdateRecordTable(name = "sfe_visit_sign_in", value = "")
public class VisitSignInEntity implements Serializable {
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
	 * 机构地址（参照：mykj_terminal_detail.terminal_address）
	 */
		@UpdateRecordColumn(name = "terminal_address", value = "机构地址（参照：mykj_terminal_detail.terminal_address）")
		private String terminalAddress;
	/**
	 * 位置坐标（例如，经纬度：39.916527,116.397128）
	 */
		@UpdateRecordColumn(name = "coordinate", value = "位置坐标（例如，经纬度：39.916527,116.397128）")
		private String coordinate;
	/**
	 * 打卡时间
	 */
		@UpdateRecordColumn(name = "sign_in_time", value = "打卡时间")
		private Date signInTime;
	/**
	 * 签到人编号
	 */
		@UpdateRecordColumn(name = "creator_code", value = "签到人编号")
		private String creatorCode;
	/**
	 * 签到人姓名
	 */
		@UpdateRecordColumn(name = "creator_name", value = "签到人姓名")
		private String creatorName;
	/**
	 * 手机号码
	 */
		@UpdateRecordColumn(name = "mobile", value = "手机号码")
		private String mobile;
	/**
	 * 关联图片标识
	 */
		@UpdateRecordColumn(name = "image_id", value = "关联图片标识")
		private String imageId;

}

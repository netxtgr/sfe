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
@TableName("sfe_agent_meeting")
@UpdateRecordTable(name = "sfe_agent_meeting", value = "")
public class AgentMeetingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
@UpdateRecordId
			private Integer id;
	/**
	 * 代理商编号
	 */
		@UpdateRecordColumn(name = "agent_number", value = "代理商编号")
		private String agentNumber;
	/**
	 * 代理商名称
	 */
		@UpdateRecordColumn(name = "agent_name", value = "代理商名称")
		private String agentName;
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
	 * 会议名称
	 */
		@UpdateRecordColumn(name = "meeting_name", value = "会议名称")
		private String meetingName;
	/**
	 * 会议内容
	 */
		@UpdateRecordColumn(name = "topic", value = "会议内容")
		private String topic;
	/**
	 * 会议人数
	 */
		@UpdateRecordColumn(name = "number", value = "会议人数")
		private Integer number;
	/**
	 * 会议时间
	 */
		@UpdateRecordColumn(name = "meeting_time", value = "会议时间")
		private Date meetingTime;
	/**
	 * 会议总结
	 */
		@UpdateRecordColumn(name = "summary", value = "会议总结")
		private String summary;
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

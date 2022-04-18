package io.sihuan.modules.attendance.vo;

import io.sihuan.dynamicexcel.annotation.ExportTit;
import io.sihuan.dynamicexcel.annotation.Tab;
import io.sihuan.dynamicexcel.common.Constant;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 钉钉考勤统计
 * 
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-17 14:06:06
 */
@Data
/*@Tab(   name = "考勤统计表",//导出的excel文件的名称
		version = Constant.EXCEL_VERSION_07,//导出excel的版本，默认03版本扩展名xls结尾
		sheetRowNum = 65536,//导出excel的每个sheet存储导出内容数据的行数（不包含表头行数）
		openMerger=false,//导出excel的内容是否开启同一列连续相同内容自动合并，默认false
		openSubtitle=true,//导出excel的内容是否开启自动填充需要导出的每个属性字段作为表头，默认false
		headerFont = HeaderFont.DEFAULT,//导出excel的表头字体 默认样式，可不写
		headerStyle= HeaderStyle.DEFAULT,//导出excel的表头样式 默认样式，可不写
		contextFont= ContextFont.DEFAULT,//导出excel的内容字体 默认样式，可不写
		contextStyle= ContextStyle.DEFAULT,//导出excel的内容样式 默认样式，可不写
		autoSizeColumn=true//自动扩宽列，默认自动扩展，可不写
)*/
@Tab(   name = "考勤明细表",//导出的excel文件的名称
		version = Constant.EXCEL_VERSION_07,//导出excel的版本，默认03版本扩展名xls结尾
		sheetRowNum = 65536, //导出excel的每个sheet存储导出内容数据的行数（不包含表头行数）
		openSubtitle = true //导出excel的内容是否开启自动填充需要导出的每个属性字段作为表头，默认false
)

public class AttendanceReportDetailVO implements Serializable {
	private static final long serialVersionUID = 1L;
	@ExportTit(name = "考勤统计单位名称", index = 0)
	private String companyName;
	@ExportTit(name = "部门名称", index = 1)
	private String deptName;
	@ExportTit(name = "姓名", index = 2)
	private String empName;
	@ExportTit(name = "考勤日期", index = 3)
	private String attDate;
	@ExportTit(name = "是否应出勤", index = 4)
	private String shouldAttendance;
	@ExportTit(name = "上班打卡时间", index = 5)
	private String onDutyTime;
	@ExportTit(name = "下班打卡时间", index = 6)
	private String ofDutyTime;
	@ExportTit(name = "考勤情况", index = 7)
	private String calcType;
	@ExportTit(name = "请假类型", index = 8)
	private String oaLeaveType;
	@ExportTit(name = "请假时长", index = 9)
	private Double oaLeaveTimes;
	@ExportTit(name = "外出时长", index = 10)
	private Double wcTimes;
	@ExportTit(name = "出差时长", index = 11)
	private Double ccTimes;
	@ExportTit(name = "加班时长", index = 12)
	private Double jbTimes;

}

package io.sihuan.modules.attendance.vo;

import io.sihuan.dynamicexcel.annotation.Tab;
import io.sihuan.dynamicexcel.annotation.ExportTit;
import io.sihuan.dynamicexcel.common.*;
import lombok.Data;

import java.io.Serializable;

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
@Tab(   name = "考勤统计表",//导出的excel文件的名称
		version = Constant.EXCEL_VERSION_07,//导出excel的版本，默认03版本扩展名xls结尾
		sheetRowNum = 65536, //导出excel的每个sheet存储导出内容数据的行数（不包含表头行数）
		openSubtitle = true //导出excel的内容是否开启自动填充需要导出的每个属性字段作为表头，默认false
)

public class AttendanceReportVO implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * OA用户ID
	 */
	private String oaUserId;
	private String userId;
	@ExportTit(name = "考勤统计单位名称", index = 0)
	private String companyName;
	@ExportTit(name = "部门名称", index = 1)
	private String deptName;
	@ExportTit(name = "姓名", index = 2)
	private String empName;
	@ExportTit(name = "应出勤天数", index = 3)
	private Double yccSum;
	@ExportTit(name = "实际出勤天数", index = 4)
	private Double sjccSum;
	@ExportTit(name = "旷工天数", index = 5)
	private Double kgSum;
	@ExportTit(name = "缺卡天数", index = 6)
	private Double qkSum;
	@ExportTit(name = "迟到早退次数", index = 7)
	private Double cdZtSum;
	@ExportTit(name = "调休（小时）", index = 8)
	private Double txOurs;
	@ExportTit(name = "年假（天）", index = 9)
	private Double njDays;
	@ExportTit(name = "病假（小时）", index = 10)
	private Double bjOurs;
	@ExportTit(name = "病假（天）", index = 11)
	private Double bjDays;
	@ExportTit(name = "事假（小时）", index = 12)
	private Double shijOurs;
	@ExportTit(name = "事假（天）", index = 13)
	private Double shijDays;
	@ExportTit(name = "婚假（天）", index = 14)
	private Double hjDays;
	@ExportTit(name = "产前检查假（天）", index = 15)
	private Double cjjDays;
	@ExportTit(name = "产假（天）", index = 16)
	private Double cjDays;
	@ExportTit(name = "节育假（天）", index = 17)
	private Double jyjDays;
	@ExportTit(name = "陪产假（天）", index = 18)
	private Double pcjDays;
	@ExportTit(name = "丧假（天）", index = 19)
	private Double shangjDays;
	@ExportTit(name = "工伤假（天）", index = 20)
	private Double gsjDays;
	@ExportTit(name = "请假合计（天）", index = 21)
	private Double sumLeaveDays;
	@ExportTit(name = "请假合计（小时）", index = 22)
	private Double sumLeaveOurs;
	@ExportTit(name = "加班合计（小时）", index = 23)
	private Double jbOurs;
	@ExportTit(name = "出差合计（天）", index = 24)
	private Double ccDays;

}

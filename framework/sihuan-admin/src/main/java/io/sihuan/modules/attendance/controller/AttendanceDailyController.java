package io.sihuan.modules.attendance.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.sihuan.common.utils.Constant;
import io.sihuan.common.validator.ValidatorUtils;
import io.sihuan.dynamicexcel.utils.ExcelUtils;
import io.sihuan.modules.attendance.vo.AttendanceReportDetailVO;
import io.sihuan.modules.attendance.vo.AttendanceReportVO;
import io.sihuan.modules.sys.entity.SysUserEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import io.sihuan.modules.attendance.entity.AttendanceDailyEntity;
import io.sihuan.modules.attendance.service.AttendanceDailyService;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 钉钉每日考勤结果
 *
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-05 10:06:06
 */
@Controller
@RequestMapping("attendance/attendancedaily")
public class AttendanceDailyController {
    @Autowired
    private AttendanceDailyService attendanceDailyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("attendance:attendancedaily:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attendanceDailyService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 考勤结果统计
     */
    @RequestMapping("/report")
    @ResponseBody
    @RequiresPermissions("attendance:attendancedaily:report")
    public R report(@RequestParam Map<String, Object> params){
        PageUtils page = attendanceDailyService.queryPageReport(params);

        return R.ok().put("page", page);
    }

    /**
     * 考勤明细结果统计
     */
    @RequestMapping("/reportdetail")
    @ResponseBody
    @RequiresPermissions("attendance:attendancedaily:reportdetail")
    public R reportDetail(@RequestParam Map<String, Object> params){
        PageUtils page = attendanceDailyService.queryPageReportDetail(params);

        return R.ok().put("page", page);
    }

    /**
     * 考勤结果统计导出
     */
    @RequestMapping("/export")
    public void report(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response){
        ExcelUtils.export(attendanceDailyService.exportReport(params), request, response, AttendanceReportVO.class);
    }

    /**
     * 考勤结果统计导出
     */
    @RequestMapping("/exportdetail")
    public void exportDetail(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response){
        ExcelUtils.export(attendanceDailyService.exportReportDetail(params), request, response, AttendanceReportDetailVO.class);
    }


    /**
     * 信息
     */
    @RequestMapping("/info")
    @ResponseBody
    public R info(@RequestParam Map<String, Object> params){
        return R.ok().put("details", attendanceDailyService.exportReportDetail(params));
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("attendance:attendancedaily:save")
    @ResponseBody
    public R save(@RequestBody AttendanceDailyEntity attendanceDaily){
        attendanceDailyService.save(attendanceDaily);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("attendance:attendancedaily:update")
    @ResponseBody
    public R update(@RequestBody AttendanceDailyEntity attendanceDaily){
        ValidatorUtils.validateEntity(attendanceDaily);
        attendanceDailyService.updateById(attendanceDaily);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("attendance:attendancedaily:delete")
    @ResponseBody
    public R delete(@RequestBody Long[] ids){
        attendanceDailyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

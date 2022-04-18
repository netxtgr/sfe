package io.sihuan.modules.attendance.controller;

import java.util.Arrays;
import java.util.Map;

import io.sihuan.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.sihuan.modules.attendance.entity.AttendanceReportColumnEntity;
import io.sihuan.modules.attendance.service.AttendanceReportColumnService;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.R;



/**
 * 钉钉考勤报表接口列定义
 *
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-05 10:06:06
 */
@RestController
@RequestMapping("attendance/attendancereportcolumn")
public class AttendanceReportColumnController {
    @Autowired
    private AttendanceReportColumnService attendanceReportColumnService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("attendance:attendancereportcolumn:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attendanceReportColumnService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("attendance:attendancereportcolumn:info")
    public R info(@PathVariable("id") Integer id){
        AttendanceReportColumnEntity attendanceReportColumn = attendanceReportColumnService.getById(id);

        return R.ok().put("attendanceReportColumn", attendanceReportColumn);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("attendance:attendancereportcolumn:save")
    public R save(@RequestBody AttendanceReportColumnEntity attendanceReportColumn){
        attendanceReportColumnService.save(attendanceReportColumn);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("attendance:attendancereportcolumn:update")
    public R update(@RequestBody AttendanceReportColumnEntity attendanceReportColumn){
        ValidatorUtils.validateEntity(attendanceReportColumn);
        attendanceReportColumnService.updateById(attendanceReportColumn);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("attendance:attendancereportcolumn:delete")
    public R delete(@RequestBody Integer[] ids){
        attendanceReportColumnService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

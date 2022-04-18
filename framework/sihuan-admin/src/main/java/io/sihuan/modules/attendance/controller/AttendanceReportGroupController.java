package io.sihuan.modules.attendance.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.sihuan.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.sihuan.modules.attendance.entity.AttendanceReportGroupEntity;
import io.sihuan.modules.attendance.service.AttendanceReportGroupService;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.R;



/**
 * 需统计考勤组
 *
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-12 14:41:41
 */
@RestController
@RequestMapping("attendance/attendancereportgroup")
public class AttendanceReportGroupController {
    @Autowired
    private AttendanceReportGroupService attendanceReportGroupService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("attendance:attendancereportgroup:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attendanceReportGroupService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("attendance:attendancereportgroup:info")
    public R info(@PathVariable("id") Integer id){
        AttendanceReportGroupEntity attendanceReportGroup = attendanceReportGroupService.getById(id);

        return R.ok().put("attendanceReportGroup", attendanceReportGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("attendance:attendancereportgroup:save")
    public R save(@RequestBody AttendanceReportGroupEntity attendanceReportGroup){
        if (attendanceReportGroup.getCreated() == null) {
            attendanceReportGroup.setCreated(new Date());
        }
        attendanceReportGroupService.save(attendanceReportGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("attendance:attendancereportgroup:update")
    public R update(@RequestBody AttendanceReportGroupEntity attendanceReportGroup){
        ValidatorUtils.validateEntity(attendanceReportGroup);
        attendanceReportGroupService.updateById(attendanceReportGroup);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("attendance:attendancereportgroup:delete")
    public R delete(@RequestBody Integer[] ids){
        attendanceReportGroupService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

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

import io.sihuan.modules.attendance.entity.AttendanceSignEntity;
import io.sihuan.modules.attendance.service.AttendanceSignService;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.R;



/**
 * 钉钉签到信息
 *
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-06 09:23:17
 */
@RestController
@RequestMapping("attendance/attendancesign")
public class AttendanceSignController {
    @Autowired
    private AttendanceSignService attendanceSignService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("attendance:attendancesign:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attendanceSignService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("attendance:attendancesign:info")
    public R info(@PathVariable("id") Long id){
        AttendanceSignEntity attendanceSign = attendanceSignService.getById(id);

        return R.ok().put("attendanceSign", attendanceSign);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("attendance:attendancesign:save")
    public R save(@RequestBody AttendanceSignEntity attendanceSign){
        attendanceSignService.save(attendanceSign);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("attendance:attendancesign:update")
    public R update(@RequestBody AttendanceSignEntity attendanceSign){
        ValidatorUtils.validateEntity(attendanceSign);
        attendanceSignService.updateById(attendanceSign);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("attendance:attendancesign:delete")
    public R delete(@RequestBody Long[] ids){
        attendanceSignService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

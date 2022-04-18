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

import io.sihuan.modules.attendance.entity.AttendanceGroupEntity;
import io.sihuan.modules.attendance.service.AttendanceGroupService;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.R;



/**
 * 
 *
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-05 10:06:06
 */
@RestController
@RequestMapping("attendance/attendancegroup")
public class AttendanceGroupController {
    @Autowired
    private AttendanceGroupService attendanceGroupService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("attendance:attendancegroup:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attendanceGroupService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("attendance:attendancegroup:info")
    public R info(@PathVariable("id") Integer id){
        AttendanceGroupEntity attendanceGroup = attendanceGroupService.getById(id);

        return R.ok().put("attendanceGroup", attendanceGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("attendance:attendancegroup:save")
    public R save(@RequestBody AttendanceGroupEntity attendanceGroup){
        attendanceGroupService.save(attendanceGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("attendance:attendancegroup:update")
    public R update(@RequestBody AttendanceGroupEntity attendanceGroup){
        ValidatorUtils.validateEntity(attendanceGroup);
        attendanceGroupService.updateById(attendanceGroup);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("attendance:attendancegroup:delete")
    public R delete(@RequestBody Integer[] ids){
        attendanceGroupService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

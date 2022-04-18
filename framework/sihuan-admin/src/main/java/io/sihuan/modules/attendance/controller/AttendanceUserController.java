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

import io.sihuan.modules.attendance.entity.AttendanceUserEntity;
import io.sihuan.modules.attendance.service.AttendanceUserService;
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
@RequestMapping("attendance/attendanceuser")
public class AttendanceUserController {
    @Autowired
    private AttendanceUserService attendanceUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("attendance:attendanceuser:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attendanceUserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("attendance:attendanceuser:info")
    public R info(@PathVariable("id") Integer id){
        AttendanceUserEntity attendanceUser = attendanceUserService.getById(id);

        return R.ok().put("attendanceUser", attendanceUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("attendance:attendanceuser:save")
    public R save(@RequestBody AttendanceUserEntity attendanceUser){
        attendanceUserService.save(attendanceUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("attendance:attendanceuser:update")
    public R update(@RequestBody AttendanceUserEntity attendanceUser){
        ValidatorUtils.validateEntity(attendanceUser);
        attendanceUserService.updateById(attendanceUser);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("attendance:attendanceuser:delete")
    public R delete(@RequestBody Integer[] ids){
        attendanceUserService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

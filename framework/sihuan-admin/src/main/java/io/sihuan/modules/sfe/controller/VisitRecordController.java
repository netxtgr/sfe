package io.sihuan.modules.sfe.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.Date;

import io.sihuan.common.validator.ValidatorUtils;
import io.sihuan.common.validator.group.AddGroup;
import io.sihuan.common.validator.group.UpdateGroup;
import io.sihuan.dynamicexcel.utils.ExcelUtils;
import org.apache.shiro.SecurityUtils;
import io.sihuan.modules.sys.entity.SysUserEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.sihuan.common.utils.Constant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.sihuan.modules.sfe.entity.VisitRecordEntity;
import io.sihuan.modules.sfe.service.VisitRecordService;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.R;


/**
 * 
 *
 * @author Tiger Cheung
 * @email Tiger.Chang@outlook.com
 * @date 2022-04-18 15:24:25
 */
@Controller
@RequestMapping("sfe/visitrecord")
public class VisitRecordController {
    @Autowired
    private VisitRecordService visitRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions("sfe:visitrecord:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = visitRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @ResponseBody
    @RequiresPermissions("sfe:visitrecord:info")
    public R info(@PathVariable("id") Long id){
        VisitRecordEntity visitRecord = visitRecordService.getById(id);

        return R.ok().put("visitRecord", visitRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @ResponseBody
    @RequiresPermissions("sfe:visitrecord:save")
    public R save(@RequestBody VisitRecordEntity visitRecord){
        ValidatorUtils.validateEntity(visitRecord, AddGroup.class);
        Date d = new Date();
        SysUserEntity user = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal());

        visitRecordService.save(visitRecord);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    @RequiresPermissions("sfe:visitrecord:update")
    public R update(@RequestBody VisitRecordEntity visitRecord){
        ValidatorUtils.validateEntity(visitRecord, UpdateGroup.class);
        SysUserEntity user = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal());

        visitRecordService.updateById(visitRecord);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions("sfe:visitrecord:delete")
    public R delete(@RequestBody Long[] ids){
        visitRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 导出
     */
    @RequestMapping("/export")
    public void export(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response){
        params.put(Constant.LIMIT, "9999999");
        PageUtils page = visitRecordService.queryPage(params);
        ExcelUtils.export(page.getList(), request, response, VisitRecordEntity.class);
    }

}

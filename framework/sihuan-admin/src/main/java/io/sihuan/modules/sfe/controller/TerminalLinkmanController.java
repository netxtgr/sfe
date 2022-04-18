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

import io.sihuan.modules.sfe.entity.TerminalLinkmanEntity;
import io.sihuan.modules.sfe.service.TerminalLinkmanService;
import io.sihuan.common.utils.PageUtils;
import io.sihuan.common.utils.R;


/**
 * 
 *
 * @author Tiger Cheung
 * @email Tiger.Chang@outlook.com
 * @date 2022-04-18 15:24:26
 */
@Controller
@RequestMapping("sfe/terminallinkman")
public class TerminalLinkmanController {
    @Autowired
    private TerminalLinkmanService terminalLinkmanService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions("sfe:terminallinkman:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = terminalLinkmanService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @ResponseBody
    @RequiresPermissions("sfe:terminallinkman:info")
    public R info(@PathVariable("id") Integer id){
        TerminalLinkmanEntity terminalLinkman = terminalLinkmanService.getById(id);

        return R.ok().put("terminalLinkman", terminalLinkman);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @ResponseBody
    @RequiresPermissions("sfe:terminallinkman:save")
    public R save(@RequestBody TerminalLinkmanEntity terminalLinkman){
        ValidatorUtils.validateEntity(terminalLinkman, AddGroup.class);
        Date d = new Date();
        SysUserEntity user = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal());

        terminalLinkmanService.save(terminalLinkman);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    @RequiresPermissions("sfe:terminallinkman:update")
    public R update(@RequestBody TerminalLinkmanEntity terminalLinkman){
        ValidatorUtils.validateEntity(terminalLinkman, UpdateGroup.class);
        SysUserEntity user = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal());

        terminalLinkmanService.updateById(terminalLinkman);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions("sfe:terminallinkman:delete")
    public R delete(@RequestBody Integer[] ids){
        terminalLinkmanService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 导出
     */
    @RequestMapping("/export")
    public void export(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response){
        params.put(Constant.LIMIT, "9999999");
        PageUtils page = terminalLinkmanService.queryPage(params);
        ExcelUtils.export(page.getList(), request, response, TerminalLinkmanEntity.class);
    }

}

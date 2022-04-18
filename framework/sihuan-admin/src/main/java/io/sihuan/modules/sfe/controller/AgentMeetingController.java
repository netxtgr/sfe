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

import io.sihuan.modules.sfe.entity.AgentMeetingEntity;
import io.sihuan.modules.sfe.service.AgentMeetingService;
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
@RequestMapping("sfe/agentmeeting")
public class AgentMeetingController {
	@Autowired
	private AgentMeetingService agentMeetingService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@ResponseBody
	@RequiresPermissions("sfe:agentmeeting:list")
	public R list(@RequestParam Map<String, Object> params) {
		PageUtils page = agentMeetingService.queryPage(params);

		return R.ok().put("page", page);
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@ResponseBody
	@RequiresPermissions("sfe:agentmeeting:info")
	public R info(@PathVariable("id") Integer id) {
		AgentMeetingEntity agentMeeting = agentMeetingService.getById(id);

		return R.ok().put("agentMeeting", agentMeeting);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@ResponseBody
	@RequiresPermissions("sfe:agentmeeting:save")
	public R save(@RequestBody AgentMeetingEntity agentMeeting) {
		ValidatorUtils.validateEntity(agentMeeting, AddGroup.class);
		Date d = new Date();
		SysUserEntity user = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal());

		agentMeetingService.save(agentMeeting);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@ResponseBody
	@RequiresPermissions("sfe:agentmeeting:update")
	public R update(@RequestBody AgentMeetingEntity agentMeeting) {
		ValidatorUtils.validateEntity(agentMeeting, UpdateGroup.class);
		SysUserEntity user = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal());
		agentMeetingService.updateById(agentMeeting);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@RequiresPermissions("sfe:agentmeeting:delete")
	public R delete(@RequestBody Integer[] ids) {
		agentMeetingService.removeByIds(Arrays.asList(ids));

		return R.ok();
	}

	/**
	 * 导出
	 */
	@RequestMapping("/export")
	public void export(@RequestParam Map<String, Object> params, HttpServletRequest request,
			HttpServletResponse response) {
		params.put(Constant.LIMIT, "9999999");
		PageUtils page = agentMeetingService.queryPage(params);
		ExcelUtils.export(page.getList(), request, response, AgentMeetingEntity.class);
	}

}

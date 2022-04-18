package io.sihuan.modules.attendance.controller;

import io.sihuan.common.utils.R;
import io.sihuan.modules.attendance.service.OaPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


/**
 * 
 *
 * @author ljt
 * @email liujiangtao@sihuanpharm.com
 * @date 2022-01-05 10:06:06
 */
@RestController
@RequestMapping("attendance/oaperson")
public class OAPersonController {
    @Autowired
    private OaPersonService oaPersonService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        return R.ok().put("data", oaPersonService.selectOaPerson());
    }

}

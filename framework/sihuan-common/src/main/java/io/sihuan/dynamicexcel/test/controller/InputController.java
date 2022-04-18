package io.sihuan.dynamicexcel.test.controller;

import io.sihuan.dynamicexcel.test.entity.SysUser;
import io.sihuan.dynamicexcel.utils.ExcelUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author： liujiangtao
 **/
@RequestMapping("import")
@RestController
public class InputController {


    /**
     * excel导入
     * 解析excel生成数据，
     *
     * @param file
     */
    @PostMapping("/test")
    public void importData(MultipartFile file) {
        List<SysUser> list = null;
        try {
            list = ExcelUtils.getList(file, SysUser.class, 1);
        } catch (Throwable e) {

        }
        System.out.println(list);

    }



}

package io.sihuan.dynamicexcel.test.controller;

import io.sihuan.dynamicexcel.entity.Header;
import io.sihuan.dynamicexcel.test.entity.SysUser;
import io.sihuan.dynamicexcel.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author： liujiangtao
 **/
@RequestMapping("export")
@RestController
public class ExportController {

    @Value("${import.templatePath}")
    private String path;

    @GetMapping(value = "/downloadTemplate")
    public void downImportTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletOutputStream out = null;
        InputStream inputStream = null;
        try {
            String templatePath = request.getParameter("templatePath");
            String fileName = request.getParameter("fileName")  + ".xlsx";
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");
            String userAgent = request.getHeader("User-Agent");
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            } else {
                // 非IE浏览器的处理：
                fileName = new String((fileName).getBytes("UTF-8"), "ISO-8859-1");
            }
            String filePath = path + templatePath + ".xlsx";
            filePath = URLDecoder.decode(filePath, "UTF-8");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
            inputStream = this.getClass().getResourceAsStream(filePath);
            out = response.getOutputStream();
            int b = 0;
            byte[] buffer = new byte[1024];
            while ((b = inputStream.read(buffer)) != -1) {
                //写到输出流(out)中
                out.write(buffer, 0, b);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }


    /**
     * excel导出
     * @param request
     * @param response
     */
    @GetMapping("/test1")
    public void exportByTitName(HttpServletRequest request, HttpServletResponse response) {

        List<SysUser> list = getUserList();

        ExcelUtils.export(list, request, response, SysUser.class);


    }


    private List<SysUser> getUserList() {
        List<SysUser> list = new ArrayList<>();
        SysUser user = new SysUser("牛逼公司", "大佬部门", "小菜鸟科室", "大帅哥", "男", "23", new Date());

        for (int i = 0; i < 100; i++) {
            list.add(user);
        }
        return list;
    }

    /**
     * 复杂表头 excel导出
     * @param request
     * @param response
     */
    @GetMapping("/test2")
    public void exportByHeader(HttpServletRequest request, HttpServletResponse response) {

        List<SysUser> list = getUserList();


        List<Header> headers = getHeaders();

        ExcelUtils.export(headers,list, request, response, SysUser.class);


    }

    private List<Header> getHeaders() {

        List<Header> headers = new ArrayList<>();
        Header header1 = new Header();
        header1.setName("合并单元格");
        header1.setStartCol(0);
        header1.setEndCol(6);
        header1.setStartRow(0);
        header1.setEndRow(0);
        headers.add(header1);

        Header header2 = new Header();
        header2.setName("人员");
        header2.setStartCol(0);
        header2.setEndCol(1);
        header2.setStartRow(1);
        header2.setEndRow(2);
        headers.add(header2);


        Header header3 = new Header();
                header3.setName("技工");
                header3.setStartCol(2);
                header3.setEndCol(4);
                header3.setStartRow(1);
                header3.setEndRow(1);
        headers.add(header3);


        return headers;
    }
}

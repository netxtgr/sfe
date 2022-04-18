package io.sihuan.dynamicexcel.utils;

import io.sihuan.dynamicexcel.core.Export;
import io.sihuan.dynamicexcel.core.Import;
import io.sihuan.dynamicexcel.entity.Header;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author： liujiangtao
 **/
@Slf4j
public class ExcelUtils {


    /**
     * 表头设置（A2:B3:表头内容）
     *
     * @param merges   merges
     * @param list     list
     * @param request  request
     * @param response response
     * @param clazz    clazz
     * @param <T>      <T>
     */
    public static <T> void exportByStrMerge(List<String> merges, List<T> list, HttpServletRequest request, HttpServletResponse response, Class clazz) {

        List<Header> headers = Export.getHeaders(merges);

        export(headers, list, request, response, clazz);
    }


    /**
     * 导出文件名称由系统自动生成
     *
     * @param headers  表头
     * @param list     导出数据
     * @param request  request
     * @param response response
     * @param <T>      导出数据类型
     */
    public static <T> void export(List<Header> headers, List<T> list, HttpServletRequest request, HttpServletResponse response, Class clazz) {
        Workbook wb = null;
        try {
            //获取工作簿
            wb = Export.getWorkBook(headers, list, clazz);

            if (wb != null) {
                //获取文件名称
                String fileName = Export.getFileName();
                if (fileName == null) {
                    fileName = "导出数据.xls";
                }
                downloadExcel(request, response, fileName, wb);
            }
        } catch (Exception e) {
            String message = e.getMessage();
            log.error(message);
            e.printStackTrace();
        } finally {
            try {
                Export.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public static <T> Workbook getWorkbook(List<Header> headers, List<T> list, HttpServletRequest request, HttpServletResponse response, Class clazz) throws Exception {
        return Export.getWorkBook(headers, list, clazz);
    }

    /**
     * 不需要复杂表头导出 且使用注解生成文件名称
     *
     * @param list     导出数据
     * @param request  请求对象
     * @param response 响应对象
     * @param <T>      导出类型
     */
    public static <T> void export(List<T> list, HttpServletRequest request, HttpServletResponse response, Class clazz) {
        Workbook wb = null;
        try {
            //获取工作簿
            wb = Export.getWorkBook(null, list, clazz);

            if (wb != null) {
                //获取文件名称
                String fileName = Export.getFileName();
                if (fileName == null) {
                    fileName = "导出数据.xls";
                }
                downloadExcel(request, response, fileName, wb);
            }
        } catch (Exception e) {
            String message = e.getMessage();
            log.error(message);
            e.printStackTrace();
        } finally {
            try {
                Export.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static <T> Workbook getWorkbook(List<T> list, HttpServletRequest request, HttpServletResponse response, Class clazz) throws Exception {
        return Export.getWorkBook(null, list, clazz);
    }

    /**
     * 根据复杂表头和文件名称导出数据
     *
     * @param headers  复杂表头
     * @param list     导出数据
     * @param request  request
     * @param response response
     * @param fileName 文件名称
     * @param <T>      导出类型
     */
    public static <T> void export(List<Header> headers, List<T> list, HttpServletRequest request, HttpServletResponse response, String fileName, Class clazz) {
        try {
            Workbook wb = Export.getWorkBook(headers, list, clazz);

            if (wb != null) {
                downloadExcel(request, response, fileName, wb);
            }
        } catch (Exception e) {
            String message = e.getMessage();
            log.error(message);
            e.printStackTrace();
        } finally {
            try {
                Export.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static <T> Workbook getWorkbook(List<Header> headers, List<T> list, HttpServletRequest request, HttpServletResponse response, String fileName, Class clazz) throws Exception {
        return Export.getWorkBook(headers, list, clazz);
    }

    /**
     * 导出方法
     *
     * @param request  request
     * @param response response
     * @param fileName fileName
     * @param wb       wb
     */
    private static void downloadExcel(HttpServletRequest request, HttpServletResponse response, String fileName, Workbook wb) {
        OutputStream op = null;
        response.reset();
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.addHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        response.addHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/octet-stream");

        try {
            response.setHeader("Content-disposition", "attachment; filename=" +
                    new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1"));
            op = response.getOutputStream();
            wb.write(op);
        } catch (UnsupportedEncodingException e) {
            log.error("excel导出编码不支持异常", e);
        } catch (IOException e) {
            log.error("流关闭异常", e);
        } finally {
            try {
                op.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 上传文件--指定内容导入开始行
     *
     * @param file     上传文件
     * @param clazz    解析类
     * @param startRow 开始行
     * @param <T>      泛型
     * @return List
     */
    public static <T> List<T> getList(MultipartFile file, Class clazz, int startRow) throws Throwable {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            log.error("错误" + e);
        }
        return Import.createData(inputStream, clazz, startRow);
    }


    /**
     * 上传文件--指定内容导入开始行
     *
     * @param file     上传文件
     * @param clazz    解析类
     * @param startRow 开始行
     * @param <T>      泛型
     * @return List
     */
    public static <T> List<T> getList(MultipartFile file, Class clazz, int startRow, int endRow) throws Throwable {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            log.error("错误" + e);
        }
        return Import.createData(inputStream, clazz, startRow, endRow);
    }

}

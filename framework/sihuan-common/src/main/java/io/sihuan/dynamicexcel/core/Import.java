package io.sihuan.dynamicexcel.core;

import io.sihuan.common.utils.ImportUtils;
import io.sihuan.dynamicexcel.annotation.ImportTit;
import io.sihuan.dynamicexcel.annotation.Tab;
import io.sihuan.dynamicexcel.common.Constant;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author： liujiangtao
 **/
public class Import {
    private static Logger logger = LoggerFactory.getLogger(Import.class);

    public static <T> List<T> createData(InputStream in, Class clazz, int startRow, int endRow) throws Throwable {

        List<T> result = new ArrayList<>();

        Workbook wb = getWorkBook(clazz, in);

        if (wb != null) {

            int numberOfSheets = wb.getNumberOfSheets();

            for (int i = 0; i < numberOfSheets; i++) {

                Sheet sheet = wb.getSheetAt(i);

                int lastRowNum = sheet.getLastRowNum();

                if (endRow > lastRowNum) {
                    endRow = lastRowNum;
                }

                List<T> tempData = getSheetData(sheet, clazz, startRow, endRow);

                if (tempData != null && tempData.size() > 0) {
                    result.addAll(tempData);
                }

            }
        }


        return result;
    }

    public static <T> List<T> createData(InputStream in, Class clazz, int startRow) throws Throwable {

        List<T> result = new ArrayList<>();


        Workbook wb = getWorkBook(clazz, in);

        if (wb != null) {

            int numberOfSheets = wb.getNumberOfSheets();

            for (int i = 0; i < numberOfSheets; i++) {

                Sheet sheet = wb.getSheetAt(i);

                List<T> tempData = getSheetData(sheet, clazz, startRow);

                if (tempData.size() > 0) {
                    result.addAll(tempData);
                }

            }
        }


        return result;
    }


    private static <T> List<T> getSheetData(Sheet sheet, Class clazz, int startRow, int endRow) throws Throwable {
        List<T> dataList = new ArrayList<>();

        try {

            setDataByRow(sheet, clazz, startRow, endRow, dataList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }


        return dataList;
    }

    private static <T> void setDataByRow(Sheet sheet, Class clazz, int startRow, int endRow, List<T> dataList) throws Throwable {
        for (int i = startRow; i <= endRow; i++) {

            Row row = sheet.getRow(i);
            T data = (T) clazz.newInstance();
            short firstCellNum = row.getFirstCellNum();
            short lastCellNum = row.getLastCellNum();

            for (int j = firstCellNum; j < lastCellNum; j++) {

                Cell cell = row.getCell(j);

                //当前列是否是导入字段，位置对不对
                try {
                    setData(clazz, data, j, cell);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    //throw new Exception("第" + i + "行，" + "第" + (j + 1) + "列，填写的数据类型不正确，请修改！");
                    throw new Exception(ImportUtils.errorMsg(i, j + 1, "填写的数据类型不正确，请修改！"));

                }


            }

            dataList.add(data);

        }
    }

    private static <T> List<T> getSheetData(Sheet sheet, Class clazz, int startRow) throws Throwable {
        List<T> dataList = new ArrayList<>();

        try {
            int rowNum = sheet.getLastRowNum();
            setDataByRow(sheet, clazz, startRow, rowNum, dataList);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }


        return dataList;
    }

    private static <T> void setData(Class clazz, T data, int j, Cell cell) throws IllegalAccessException, ParseException {
        //当前列是否是导入字段，位置对不对
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {

            ImportTit tit = field.getAnnotation(ImportTit.class);

            if (tit != null) {

                int index = tit.index();
                boolean open = tit.open();

                boolean date = tit.isDate();

                String sdf = tit.sdf();

                if (open && index == j) {

                    String value = getCellValue(cell);
                    if (StringUtils.isBlank(value)) {
                        break;
                    }
                    Class<?> type = field.getType();
                    field.setAccessible(true);


                    if (type == Date.class || date) {

                        SimpleDateFormat format = new SimpleDateFormat(sdf);

                        if (type == Date.class){

                            try {
                                Date javaDate = DateUtil.getJavaDate(Double.parseDouble(value));
                                field.set(data, javaDate);
                            } catch (Exception e) {

                                /*try {*/

                                    Date parse = format.parse(value);
                                    field.set(data, parse);

                                /*} catch (ParseException pe) {
                                    pe.printStackTrace();
                                }*/

                            }

                        }else {

                            String v;

                            try {
                                Date javaDate = DateUtil.getJavaDate(Double.parseDouble(value));
                                v=format.format(javaDate);

                            } catch (NumberFormatException e) {
                                v=value;
                            }

                            field.set(data, v);

                        }

                        break;
                    }

                    if (type == String.class) {
                        field.set(data, value);
                        break;
                    }
                    if (type == Boolean.class) {
                        Boolean input = Boolean.valueOf(value);
                        field.set(data, input);
                        break;
                    }

                    if (type == Integer.class) {
                        Integer input = Integer.valueOf(value.replace(".0", ""));
                        field.set(data, input);
                        break;
                    }

                    if (type == Double.class) {
                        Double input = Double.valueOf(value);
                        field.set(data, input);
                        break;
                    }

                    if (type == Long.class) {
                        Long input = Long.valueOf(value.replace(".0", ""));
                        field.set(data, input);
                        break;
                    }

                    if (type == Short.class) {
                        Short input = Short.valueOf(value);
                        field.set(data, input);
                        break;
                    }

                    if (type == BigDecimal.class) {
//                        BigDecimal input = new BigDecimal(value).setScale(2, BigDecimal.ROUND_UP);
                        BigDecimal input = new BigDecimal(value);
                        field.set(data, input);
                    }

                }

            }


        }
    }


    private static String getCellValue(Cell cell) {
        String temp = "";
        if (cell == null) {
            return temp;
        }

//        if (cell.getCellType() == CellType.STRING) {
//            temp = cell.getStringCellValue();
//        } else if (cell.getCellType() == CellType.BOOLEAN) {
//            boolean value = cell.getBooleanCellValue();
//            temp = String.valueOf(value);
//        } else if (cell.getCellType() == CellType.FORMULA) {
//            temp = cell.getCellFormula();
//        } else if (cell.getCellType() == CellType.NUMERIC) {
//            double value = cell.getNumericCellValue();
//            temp = String.valueOf(value);
//        } else if (cell.getCellType() == CellType.BLANK) {
//            temp = "";
//        } else if (cell.getCellType() == CellType.ERROR) {
//            temp = "警告！！字段错误！！";
//        } else {
//            throw new IllegalArgumentException();
//        }


        if (cell.getCellType() == 1) {
            temp = cell.getStringCellValue();

        } else if (cell.getCellType() == 4) {

            boolean value = cell.getBooleanCellValue();
            temp = String.valueOf(value);

        } else if (cell.getCellType() == 2) {

            temp = cell.getCellFormula();
        } else if (cell.getCellType() ==0) {
            double value = cell.getNumericCellValue();
            temp = String.valueOf(value);
        } else if (cell.getCellType() == 3) {
            temp = "";
        } else if (cell.getCellType() == 5) {
            temp = "警告！！字段错误！！";
        } else {
            throw new IllegalArgumentException();
        }

        return temp;

    }

    private static Workbook getWorkBook(Class clazz, InputStream in) {

        Workbook workbook = null;

        try {
            Tab tab = (Tab) clazz.getAnnotation(Tab.class);

            String version = tab.version();

            if (Constant.EXCEL_VERSION_03.equals(version)) {
                workbook = new HSSFWorkbook(in);
            } else {
                workbook = new XSSFWorkbook(in);
            }

        } catch (IOException e) {
            try {
                workbook = new HSSFWorkbook(in);
            } catch (IOException ee) {
                try {
                    workbook = new XSSFWorkbook(in);
                } catch (IOException eee) {
                    eee.printStackTrace();
                    return null;
                }
            }

        }

        return workbook;
    }
}

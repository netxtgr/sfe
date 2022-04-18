package io.sihuan.dynamicexcel.core;

import io.sihuan.dynamicexcel.annotation.Tab;
import io.sihuan.dynamicexcel.annotation.ExportTit;
import io.sihuan.dynamicexcel.common.*;
import io.sihuan.dynamicexcel.entity.Merge;
import io.sihuan.dynamicexcel.entity.MergerData;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import io.sihuan.dynamicexcel.entity.Header;
/**
 * @author： liujiangtao
 **/
public class Export {


    /**
     * 当前工作簿
     */
    private static Workbook wb;

    /**
     * 存放当前wb的所有sheet
     */
    private static List<Sheet> sheets = new ArrayList<>();

    /**
     * 当前导出类类型
     */
    private static Class clazz;

    /**
     * 导出的表格名称
     */
    private static String tabName;
    /**
     * excel的版本 03或07版本
     */
    private static String tabVersion;

    /**
     * 每个sheet存储的行数
     */
    private static double tabSheetRowNum;

    /**
     * 数据总行数
     */
    private static double dataNums;

    /**
     * 表头结束行位置
     */
    private static int headerEndRowIndex;
    /**
     * 表头最后列位置
     */
    private static int headerEndColIndex;

    /**
     * 存放副表头
     */
    private static HashMap<Integer, String> subtitleMap = new HashMap<>();

    /**
     * 需要合并的数据
     */
    private static List<MergerData> mergerData = new ArrayList<>();

    /**
     * 表头字体样式
     */
    private static Font headerFont;
    private static HeaderFont headerFontEnum;

    /**
     * 正文字体样式
     */
    private static Font contextFont;
    private static ContextFont contextFontEnum;

    /**
     * 表头样式
     */
    private static CellStyle headerStyle;
    private static HeaderStyle headerStyleEnum;

    /**
     * 正文样式
     */
    private static CellStyle contextStyle;
    private static ContextStyle contextStyleEnum;

    /**
     * 导出文件名称
     */
    private static String fileName;

    /**
     * 是否开启把属性字段的内容作为表头  @Tit (name="A") 则A字段将会被导出作为表头
     */
    private static boolean openSubtitle;

    /**
     * 自动扩展列宽
     */
    private static boolean autoSizeColumn;


    /**
     * 是否开启正文内容同一列内容相同自动合并
     */
    private static boolean openMerge;


    private static List<Merge> headerMerges = new ArrayList<>();

    public static String getFileName() {
        return fileName;
    }


    private static void headerProcess(List<Header> headers) {

        if (headers == null || headers.size() == 0) {
            return;
        }

        for (Header header : headers) {
            //列
            Integer endCol = header.getEndCol();

            //行
            Integer endRow = header.getEndRow();
            headerEndRowIndex = Math.max(headerEndRowIndex, endRow);
            headerEndColIndex = Math.max(headerEndColIndex, endCol);
        }
        headerEndRowIndex++;

        for (Sheet sheet : sheets) {

            for (int i = 0; i <= headerEndRowIndex; i++) {
                Row row = sheet.createRow(i);

                for (int j = 0; j <= headerEndColIndex; j++) {
                    Cell cell = row.createCell(j);
                    try {
                        if (headerStyle != null) {
                            cell.setCellStyle(headerStyle);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    cell.setCellValue("");
                }

            }

        }

        for (int i = 0; i < sheets.size(); i++) {

            Sheet sheet = sheets.get(i);

            for (Header header : headers) {

                //列
                Integer startCol = header.getStartCol();
                Integer endCol = header.getEndCol();

                //行
                Integer startRow = header.getStartRow();
                Integer endRow = header.getEndRow();
                String name = header.getName();

                Row row = sheet.getRow(startRow);
                if (row == null) {
                    row = sheet.createRow(startRow);
                }

                Cell cell = row.createCell(startCol);
                cell.setCellValue(name);

                if (headerStyle != null) {
                    cell.setCellStyle(headerStyle);
                }

                if (endCol >= 0 && endRow >= 0) {
                    CellRangeAddress region = new CellRangeAddress(startRow, endRow, startCol, endCol);
                    sheet.addMergedRegion(region);

                    Merge merge = new Merge();
                    merge.setSheetId(i);
                    merge.setStartRow(startRow);
                    merge.setEndRow(endRow);
                    merge.setStartCol(startCol);
                    merge.setEndCol(endCol);

                    headerMerges.add(merge);
                }
            }

        }


    }

    /**
     * 存入数据
     *
     * @param list 数据
     * @param <T>  类型
     */
    private static <T> void saveData(List<T> list) {

        if (list != null && list.size() > 0) {
            int start = 0;
            int end = (int) tabSheetRowNum;
            int size = list.size();
            int rowNums;
            if (size <= tabSheetRowNum) {
                end = size;

            }

            for (int sheetIndex = 0; sheetIndex < sheets.size(); sheetIndex++) {

                Sheet sheet = sheets.get(sheetIndex);

                List<T> rowDataList = list.subList(start, end);

                rowNums = headerEndRowIndex + rowDataList.size();

                for (int rowIndex = headerEndRowIndex; rowIndex < rowNums; rowIndex++) {

                    Row row = sheet.createRow(rowIndex);

                    Object data = rowDataList.get(rowIndex - headerEndRowIndex);

                    for (int colIndex = 0; colIndex <= headerEndColIndex; colIndex++) {

                        Cell cell = row.createCell(colIndex);

                        mySetCellValue(data, cell, colIndex, rowIndex, sheetIndex);
                    }
                }


                start = (int) (start + tabSheetRowNum);
                end = (int) (end + tabSheetRowNum);

                if (end >= size) {
                    end = size;
                }

            }
        }


    }

    /**
     * @param data       当前对象值
     * @param cell       单元格
     * @param colIndex   列位置
     * @param sheetIndex sheet位置
     */
    private static void mySetCellValue(Object data, Cell cell, int colIndex, int rowIndex, int sheetIndex) {

        Class<?> clazz = data.getClass();

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {

            field.setAccessible(true);

            ExportTit tit = field.getAnnotation(ExportTit.class);

            Class<?> type = field.getType();

            if (tit != null) {
                //当前列导出所在列位置
                int indexCol = tit.index();
                //当前列是否需要导出
                boolean open = tit.open();
                //是否开启当前列单元值内容自动合并
                boolean openMerger = tit.openMerger();
                if (open && colIndex == indexCol) {
                    try {
                        Object obj = field.get(data);
                        String value;
                        if (obj != null) {
                            value = obj.toString();

                            if (type == Date.class && obj instanceof Date) {
                                String sdf = tit.sdf();
                                Date date = (Date) obj;
                                SimpleDateFormat format = new SimpleDateFormat(sdf);
                                value = format.format(date);
                            }
                        } else {
                            value = "";
                        }

                        if (contextStyle != null) {
                            cell.setCellStyle(contextStyle);
                        }
                        if (obj instanceof Double) {
                            cell.setCellValue(Double.parseDouble(obj.toString()));
                        } else if (obj instanceof Integer) {
                            cell.setCellValue(Integer.parseInt(obj.toString()));
                        } else {
                            cell.setCellValue(value);
                        }
                        if (openMerger) {
                            mergerData.add(new MergerData(sheetIndex, colIndex, rowIndex, value));
                        }

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }

    public static <T> Workbook getWorkBook(List<Header> headers, List<T> list, Class clz) throws Exception {


        //初始化类信息和数据行数
        initList(list, clz);

        //初始化相关注解
        initTab();

        //初始化workbook
        initWorkbook();

        //初始化sheet
        initSheet();

        //初始化样式
        initStyle();

        //设置需合并的一级表头
        headerProcess(headers);

        //设置每列对应的字段属性作为二级表头
        subtitle();

        //表头自下而上合并
        headerProcessPost();

        //存入数据
        saveData(list);

        //合并同一列内容相同的单元格
        merger();

        autoSizeColumn();

        return wb;
    }

    private static void headerProcessPost() {

        if (openSubtitle) {

            for (Sheet sheet : sheets) {

                //同一列
                for (int col = 0; col <= headerEndColIndex; col++) {

                    int startRow = 0;
                    int endRow = 0;


                    List<MergerData> list = new ArrayList<>();

                    String mergeValue = "";

                    for (int row = headerEndRowIndex - 1; row >= 0; row--) {

                        Row nowRow = sheet.getRow(row);


                        if (nowRow != null) {

                            Cell nowCell = nowRow.getCell(col);

                            String nowValue = nowCell.getStringCellValue();

                            boolean merge = getBoolMerge(row, row, col, col);

                            if ("".equals(nowValue)) {

                                if (!merge) {

                                    nowCell.setCellValue(mergeValue);

                                    MergerData mergerData = new MergerData();
                                    mergerData.setRowIndex(row);
                                    mergerData.setColIndex(col);
                                    mergerData.setValue(mergeValue);
                                    mergerData.setSheetIndex(-1);
                                    list.add(mergerData);
                                }
                            } else {
                                mergeValue = nowValue;

                                if (!merge) {

                                    MergerData mergerData = new MergerData();
                                    mergerData.setRowIndex(row);
                                    mergerData.setColIndex(col);
                                    mergerData.setValue(mergeValue);
                                    mergerData.setSheetIndex(-1);
                                    list.add(mergerData);

                                }

                            }
                        }
                    }

                    List<Merge> merges = new ArrayList<>();


                    List<MergerData> dataList = list.stream().sorted(Comparator.comparing(MergerData::getRowIndex)).collect(Collectors.toList());

                    getMerges(merges, dataList);


                    if (merges.size() > 0) {
                        Integer startCol;
                        Integer endCol;
                        for (Merge merge : merges) {

                            startRow = merge.getStartRow();
                            endRow = merge.getEndRow();
                            startCol = merge.getStartCol();
                            endCol = merge.getEndCol();

                            try {
                                CellRangeAddress region = new CellRangeAddress(startRow, endRow, startCol, endCol);
                                sheet.addMergedRegion(region);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }

                }

            }

        }

    }

    /**
     * 判断当前小单元格是否是合并单元格
     *
     * @param startRow 开始行
     * @param endRow   结束行
     * @param startCol 开始列
     * @param endCol   结束列
     * @return
     */
    private static boolean getBoolMerge(int startRow, int endRow, int startCol, int endCol) {

        for (Merge merge : headerMerges) {

            Integer startRow1 = merge.getStartRow();

            Integer endRow1 = merge.getEndRow();

            Integer startCol1 = merge.getStartCol();

            Integer endCol1 = merge.getEndCol();

            boolean b = (startRow >= startRow1 && startRow1 <= endRow1)
                    && (endRow >= startRow1 && endRow <= endRow1)
                    && (startCol >= startCol1 && startCol1 <= endCol1)
                    && (endCol >= startCol1 && endCol <= endCol1);

            if (b) {
                return true;
            }

        }

        return false;
    }

    /**
     * 每一列自动列款
     */
    private static void autoSizeColumn() {

        if (autoSizeColumn) {

            for (Sheet sheet : sheets) {
                for (int i = 0; i <= headerEndColIndex; i++) {
                    //设置字段列宽
                    sheet.autoSizeColumn(i, true);
                }
            }
        }

    }

    /**
     * 同一列自动合并连续相同的值
     */
    private static void merger() {

        List<Merge> merges = new ArrayList<>();

        /**
         * 是否开启自动合并相同内容值
         */
        if (openMerge) {

            if (mergerData.size() == 0) {
                return;
            }

            Map<Integer, List<MergerData>> map = mergerData.stream().collect(Collectors.groupingBy(MergerData::getColIndex));
            Set<Integer> set = map.keySet();

            for (Integer colIndex : set) {
                List<MergerData> mergerData = map.get(colIndex);
                List<MergerData> list = mergerData.stream().sorted(Comparator.comparing(MergerData::getRowIndex)).collect(Collectors.toList());

                getMerges(merges, list);

            }


            if (merges.size() > 0) {
                Sheet sheet;
                Integer startRow;
                Integer endRow;
                Integer startCol;
                Integer endCol;
                for (Merge merge : merges) {

                    int sheetId = merge.getSheetId();

                    startRow = merge.getStartRow();
                    endRow = merge.getEndRow();
                    startCol = merge.getStartCol();
                    endCol = merge.getEndCol();

                    sheet = wb.getSheetAt(sheetId);
                    try {
                        CellRangeAddress region = new CellRangeAddress(startRow, endRow, startCol, endCol);
                        sheet.addMergedRegion(region);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

        }


    }

    /**
     * 获取合并单元格数据
     *
     * @param merges 返回需要合并的数据
     * @param list   合并区间的数据
     */
    private static void getMerges(List<Merge> merges, List<MergerData> list) {
        if (list.size() > 0) {

            int start = 0;
            int end = 0;
            int size = list.size();

            MergerData dataStart = null;
            MergerData dataEnd = null;
            String startValue = "";
            String endValue = "";

            int sheetId = 0;
            int startRow = 0;
            int endRow = 0;
            int startCol = 0;
            int endCol = 0;
            String value = "";

            do {
                dataStart = list.get(start);
                dataEnd = list.get(end);
                startValue = dataStart.getValue();
                endValue = dataEnd.getValue();

                value = dataStart.getValue();
                sheetId = dataStart.getSheetIndex();
                startCol = dataStart.getColIndex();
                endCol = dataStart.getColIndex();
                startRow = dataStart.getRowIndex();
                endRow = dataEnd.getRowIndex();

                if (startValue != null && startValue.equals(endValue)) {
                    end++;
                } else {
                    if (endRow - startRow > 1) {
                        merges.add(new Merge(sheetId, startRow, endRow - 1, startCol, endCol, value));
                    }
                    start = end;
                }

                if (end == size) {
                    if (endRow - startRow >= 1) {
                        merges.add(new Merge(sheetId, startRow, endRow, startCol, endCol, value));
                    }
                }

            } while (end < size);

        }
    }

    /**
     * 开启属性是字段生成作为表头
     */
    private static void subtitle() {

        if (openSubtitle) {


            int numberOfSheets = wb.getNumberOfSheets();

            for (int i = 0; i < numberOfSheets; i++) {

                Sheet sheet = wb.getSheetAt(i);

                Row row = sheet.createRow(headerEndRowIndex);

                for (int j = 0; j <= headerEndColIndex; j++) {

                    String value = subtitleMap.get(j);

                    if (value != null) {
                        Cell cell = row.createCell(j);
                        cell.setCellValue(value);
                        if (headerStyle != null) {
                            cell.setCellStyle(headerStyle);
                        }
                    }

                }

            }


            headerEndRowIndex++;
        }


    }

    /**
     * 表头样式设置
     * 单元格样式设置
     */
    private static void initStyle() {
        setHeaderStyle();

        setContextFont();

        setContextStyle();
    }

    /**
     * 默认表头样式
     */
    private static void setHeaderStyle() {

        if (headerFontEnum != null) {
            //表头字体
            switch (headerFontEnum) {
                case DEFAULT:
                    headerFontDefault();
                    break;
                case TEST:
                    System.out.println("222");
                    break;

                default:
                    break;
            }
        }


        if (headerStyleEnum != null) {
            switch (headerStyleEnum) {
                case DEFAULT:
                    headerStyleDefault();
                    break;
                case TEST:
                    System.out.println("222");
                    break;
                default:
                    break;
            }

        }

    }


    /**
     * 设置正文字体
     */
    private static void setContextFont() {
        if (contextFontEnum != null) {
            switch (contextFontEnum) {
                case DEFAULT:
                    contextFontDefault();
                    break;
                case TEST:
                    System.out.println("88");
                    break;
                default:
                    break;
            }
        }

    }


    /**
     * 设置正文风格样式
     */
    private static void setContextStyle() {
        if (contextStyleEnum != null) {
            switch (contextStyleEnum) {
                case DEFAULT:
                    contextStyleDefault();
                    break;
                case TEST:
                    System.out.println(33333);
                    break;
                default:
                    break;
            }
        }

    }


    private static void headerStyleDefault() {
        //表头样式，左右上下居中
        if (wb != null) {
            headerStyle = wb.createCellStyle();
        }

        if (headerStyle != null) {
            if (headerFont != null) {
                headerStyle.setFont(headerFont);
            }

//            // 左右居中 4.0+
//            headerStyle.setAlignment(HorizontalAlignment.CENTER);
//            // 上下居中
//            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // 左右居中  poi 3.0+
            headerStyle.setAlignment((short) 2);
            // 上下居中  poi 3.0+
            headerStyle.setVerticalAlignment((short) 1);

            headerStyle.setLocked(true);
            // 自动换行
            headerStyle.setWrapText(false);

//            //下边框 4.0+
//            headerStyle.setBorderBottom(BorderStyle.THIN);
//            //左边框
//            headerStyle.setBorderLeft(BorderStyle.THIN);
//            //上边框
//            headerStyle.setBorderTop(BorderStyle.THIN);
//            //右边框
//            headerStyle.setBorderRight(BorderStyle.THIN);

            //下边框 poi 3.0+
            headerStyle.setBorderBottom((short) 1);
            //左边框
            headerStyle.setBorderLeft((short) 1);
            //上边框
            headerStyle.setBorderTop((short) 1);
            //右边框
            headerStyle.setBorderRight((short) 1);

            headerStyle.setFillForegroundColor(new HSSFColor.PALE_BLUE().getIndex());
            headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        }

    }


    private static void headerFontDefault() {
        if (wb != null) {
            headerFont = wb.createFont();
            headerFont.setFontName("微软雅黑");
            headerFont.setFontHeightInPoints((short) 10);
//            headerFont.setBold(false);//4.0+
            headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);


        }
    }


    private static void contextStyleDefault() {
        //单元格样式，左右上下居中 边框
        if (wb != null) {
            contextStyle = wb.createCellStyle();
        }

        if (contextStyle != null) {

            if (contextFont != null) {
                contextStyle.setFont(contextFont);
            }
//            // 左右居中 4.0+
//            contextStyle.setAlignment(HorizontalAlignment.CENTER);
//            // 上下居中
//            contextStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // 左右居中  poi 3.0+
            contextStyle.setAlignment((short) 2);
            // 上下居中  poi 3.0+
            contextStyle.setVerticalAlignment((short) 1);


            contextStyle.setLocked(true);
            // 自动换行
            contextStyle.setWrapText(false);

//            //下边框 4.0+
//            contextStyle.setBorderBottom(BorderStyle.THIN);
//            //左边框
//            contextStyle.setBorderLeft(BorderStyle.THIN);
//            //上边框
//            contextStyle.setBorderTop(BorderStyle.THIN);
//            //右边框
//            contextStyle.setBorderRight(BorderStyle.THIN);

            //下边框 3.0.+
            contextStyle.setBorderBottom((short) 1);
            //左边框
            contextStyle.setBorderLeft((short) 1);
            //上边框
            contextStyle.setBorderTop((short) 1);
            //右边框
            contextStyle.setBorderRight((short) 1);
        }
    }


    private static void contextFontDefault() {
        //正文字体
        if (wb != null) {
            contextFont = wb.createFont();
            contextFont.setFontName("微软雅黑");
            contextFont.setFontHeightInPoints((short) 9);
        }

    }


    private static <T> void initList(List<T> list, Class clz) {

        if (list != null && list.size() > 0) {
            T t = list.get(0);
            clazz = t.getClass();
            dataNums = list.size();
        } else {
            clazz = clz;
        }

    }


    private static void initWorkbook() {
        if (Constant.EXCEL_VERSION_07.equals(tabVersion)) {
            wb = new XSSFWorkbook();
        } else {
            wb = new HSSFWorkbook();
        }
    }


    private static void initTab() {

        if (clazz != null) {
            Annotation clazzAnnotation = clazz.getAnnotation(Tab.class);
            if (clazzAnnotation instanceof Tab) {
                Tab tab = (Tab) clazzAnnotation;
                tabName = tab.name();
                tabVersion = tab.version();
                tabSheetRowNum = tab.sheetRowNum();
                fileName = tabName + "." + tabVersion;

                openMerge = tab.openMerger();
                openSubtitle = tab.openSubtitle();
                autoSizeColumn = tab.autoSizeColumn();

                headerFontEnum = tab.headerFont();
                contextFontEnum = tab.contextFont();
                headerStyleEnum = tab.headerStyle();
                contextStyleEnum = tab.contextStyle();
            }

            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                ExportTit tit = field.getAnnotation(ExportTit.class);
                if (tit != null) {
                    boolean open = tit.open();
                    if (open) {
                        String name = tit.name();
                        int index = tit.index();
                        subtitleMap.put(index, name);
                        headerEndColIndex = Math.max(index, headerEndColIndex);
                    }
                }

            }
        }
    }


    private static void initSheet() {

        if (dataNums > 0 && tabSheetRowNum > 0) {
            double sheetNums = dataNums / tabSheetRowNum;
            int size = (int) Math.ceil(sheetNums);
            for (int i = 0; i < size; i++) {
                Sheet sheet = wb.createSheet(tabName + (i + 1));
                sheets.add(sheet);
            }
        } else {
            Sheet sheet = wb.createSheet("空表.xls");
            sheets.add(sheet);
        }
    }


    public static List<Header> getHeaders(List<String> list) {

        List<Header> headers = new ArrayList<>();

        list.forEach(item -> {

            String[] split = item.split(":");

            int length = split.length;
            if (length == 3) {
                String cell1 = split[0];
                String cell2 = split[1];
                String v3 = split[2];

                int startRow = row(cell1);
                int startColumn = column(cell1);
                int endRow = row(cell2);
                int endColumn = column(cell2);

                Header header = Header.builder()
                        .startRow(startRow)
                        .endRow(endRow)
                        .startCol(startColumn)
                        .endCol(endColumn)
                        .name(v3)
                        .build();
                headers.add(header);
            }
        });


        return headers;
    }

    public static int row(String cellName) {
        int row = 0;
        char[] c = cellName.toUpperCase().toCharArray();
        //保证了数字之后不能出现字母
        boolean startFlag = false;
        int index = 0;
        while (index < c.length) {
            if (c[index] < '0' || c[index] > '9') {
                index++;
                //容易出错的地方
                if (startFlag) {
                    throw new IllegalArgumentException("单元格名称输入错误,请检查");
                }
                continue;
            }
            row = row * 10 + (c[index] - '0');
            startFlag = true;
            index++;
        }
        return row;
    }

    public static int column(String cellName) {
        int column = 0;
        char[] c = cellName.toUpperCase().toCharArray();
        int index = 0;
        while (index < c.length) {
            if (c[index] < 'A' || c[index] > 'Z')
                break;

            column = column * 26 + (c[index] - 'A' + 1);
            index++;
        }
        return column;
    }


    public static String cellName(int row, int col) {
        ArrayList<Character> list = new ArrayList();
        while (col > 0) {
            list.add((char) (col % 26 + 'A' - 1));
            col /= 26;
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = list.size() - 1; i >= 0; i--)
            buffer.append(list.get(i));
        buffer.append("" + row);
        return buffer.toString();
    }


    public static void clear() {
        wb = null;
        sheets = new ArrayList<>();
        clazz = null;
        tabName = null;
        tabVersion = null;
        tabSheetRowNum = 0;
        dataNums = 0;
        headerEndRowIndex = 0;
        headerEndColIndex = 0;
        subtitleMap = new HashMap<>();
        mergerData = new ArrayList<>();
        headerFont = null;
        contextFont = null;
        headerStyle = null;
        contextStyle = null;
        fileName = null;
        openSubtitle = false;
        openMerge = false;
        headerFontEnum = null;
        contextFontEnum = null;
        headerStyleEnum = null;
        contextStyleEnum = null;
        headerMerges = new ArrayList<>();
        System.gc();
    }
}

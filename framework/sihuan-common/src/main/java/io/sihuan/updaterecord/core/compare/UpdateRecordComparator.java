/*

 */

package io.sihuan.updaterecord.core.compare;

import io.sihuan.updaterecord.api.vo.ColumnVo;
import io.sihuan.updaterecord.core.cache.UpdateRecordThreadLocalCache;
import io.sihuan.updaterecord.core.config.UpdateRecordConfigProperties;
import io.sihuan.updaterecord.core.constant.UpdateRecordConstant;
import io.sihuan.updaterecord.core.enums.ModeEnum;
import io.sihuan.updaterecord.core.util.UpdateRecordUtil;
import io.sihuan.updaterecord.core.vo.FieldCompareVo;
import io.sihuan.updaterecord.core.vo.UpdateRecordItemVo;
import io.sihuan.updaterecord.entity.UpdateRecordColumnLogEntity;
import io.sihuan.updaterecord.entity.UpdateRecordTableLogEntity;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * 修改记录比较器
 *
 * @author liujiangtao
 * @date 2022-03-17
 **/
@Slf4j
public class UpdateRecordComparator {

    /**
     * 两个对象，比较字段内容变更部分
     * 思路：
     * 1. 获取对象的所有可访问字段
     * 2. 使用反射和内省获取字段的值
     * 3. 比较前后对象字段的值
     * 4. 得出修改部分内容
     *
     * @param beforeMap
     * @param afterMap
     */
    public static UpdateRecordItemVo compare(Map<String, Object> beforeMap, Map<String, Object> afterMap, String beforeVersionValue, String afterVersionValue,
                                             UpdateRecordTableLogEntity updateRecordTableLog, Map<String, ColumnVo> columnMap) throws Exception {
        if (beforeMap == null || afterMap == null) {
            return null;
        }
        // 表记录修改信息VO
        UpdateRecordItemVo updateRecordItemVo = new UpdateRecordItemVo();
        // 表记录对象
        updateRecordItemVo.setUpdateRecordTableLog(updateRecordTableLog);
        // 修改列集合
        Set<UpdateRecordColumnLogEntity> updateRecordColumnLogs = new LinkedHashSet<>();
        // 修改描述字符串
        List<String> updateDescList = new ArrayList<>();
        // 获取对象的所有可访问字段
        Set<String> columnNames = beforeMap.keySet();

        // 修改列总数
        int total = 0;
        // 添加模式数量
        int addCount = 0;
        // 修改模式数量
        int updateCount = 0;
        // 删除模式数量
        int deleteCount = 0;

        // 排除字段
        Set<String> excludeColumns = new HashSet<>();
        // 默认排除字段集合
        excludeColumns.addAll(UpdateRecordConstant.DEFAULT_EXCLUDE_FIELDS);
        // 自定义全局排除字段
        excludeColumns.addAll(UpdateRecordConfigProperties.EXCLUDE_COLUMNS);


        // 变更值对象列表
        Set<FieldCompareVo> diffSet = new LinkedHashSet<>();
        int i = 0;
        int size = columnNames.size();
        String columnSeparator = UpdateRecordConfigProperties.COLUMN_SEPARATOR;
        for (String columnName : columnNames) {
            String columnNameLower = columnName.toLowerCase();
            i++;
            if (excludeColumns.contains(columnNameLower)) {
                continue;
            }

            // 如果没在map集合中，则跳过，忽略不需要记录的字段
            if (!columnMap.containsKey(columnNameLower)) {
                continue;
            }

            // 列信息
            ColumnVo columnVo = columnMap.get(columnNameLower);
            // 列描述
            String columnDesc = columnVo.getColumnDesc();
            Class<?> columnClass = columnVo.getTypeClass();

            // 列详细描述对象
            UpdateRecordColumnLogEntity updateRecordColumnLog = new UpdateRecordColumnLogEntity();
            updateRecordColumnLog.setTableName(updateRecordTableLog.getTableName());
            updateRecordColumnLog.setVersion(UpdateRecordConfigProperties.VERSION);
            updateRecordColumnLog.setIdValue(updateRecordTableLog.getIdValue());
            updateRecordColumnLog.setColumnName(columnName);
            updateRecordColumnLog.setColumnDesc(columnDesc);
            updateRecordColumnLog.setCreateTime(new Date());
            updateRecordColumnLog.setBeforeVersion(beforeVersionValue);
            updateRecordColumnLog.setAfterVersion(afterVersionValue);

            Object beforeValue = beforeMap.get(columnName.toUpperCase());
            Object afterValue = afterMap.get(columnNameLower);

            // 判断是否修改，未修改，不记录
            boolean equals = valueIsEquals(beforeValue, afterValue);
            if (equals) {
                continue;
            }
            // 设置修改前后的值
            String beforeValueString = formatColumnValue(beforeValue);
            String afterValueString = formatColumnValue(afterValue);
            if (beforeValueString.equals(afterValueString)) {
                continue;
            }
            if (compareBigDecimal(beforeValue, afterValue)) {
                continue;
            }
            updateRecordColumnLog.setBeforeValue(beforeValueString);
            updateRecordColumnLog.setAfterValue(afterValueString);
            beforeMap.put(columnName,beforeValueString);
            afterMap.put(columnName,afterValueString);

            ModeEnum modeEnum;
            // 3. 比较前后对象字段的值
            if (beforeValue == null) {
                // add
                modeEnum = ModeEnum.ADD;
                addCount++;
                updateDescList.add(String.format(UpdateRecordConfigProperties.ADD_MODEL_FORMAT, columnDesc, afterValueString));
            } else if (afterValue == null || "".equals(afterValue.toString().trim())) {
                // delete
                modeEnum = ModeEnum.DELETE;
                deleteCount++;
                updateDescList.add(String.format(UpdateRecordConfigProperties.DELETE_MODEL_FORMAT, columnDesc));
            } else {
                // 两个字段有值，且不相等，则为修改
                // update
                modeEnum = ModeEnum.UPDATE;
                updateCount++;
                updateDescList.add(String.format(UpdateRecordConfigProperties.UPDATE_MODEL_FORMAT, columnDesc, beforeValueString, afterValueString));
            }

            // 字段比较对象
            FieldCompareVo fieldCompareVo = new FieldCompareVo();
            fieldCompareVo.setName(columnName);
            fieldCompareVo.setBefore(beforeValueString);
            fieldCompareVo.setAfter(afterValueString);

            // 修改模式
            int mode = modeEnum.getCode();
            fieldCompareVo.setMode(mode);

            // 添加到集合中
            diffSet.add(fieldCompareVo);

            updateRecordColumnLog.setMode(mode);
            updateRecordColumnLog.setCommitId(UpdateRecordThreadLocalCache.getCommitId());

            updateRecordColumnLogs.add(updateRecordColumnLog);
        }

        updateRecordItemVo
                .setBeforeMap(beforeMap)
                .setAfterMap(afterMap);


        // 去掉最后一个分隔符
        String updateDesc = UpdateRecordUtil.join(updateDescList,columnSeparator);

        // 4. 得出修改部分内容
        total = addCount + updateCount + deleteCount;
        updateRecordTableLog.setTotal(total);
        updateRecordTableLog.setAddModeCount(addCount);
        updateRecordTableLog.setUpdateModeCount(updateCount);
        updateRecordTableLog.setDeleteModeCount(deleteCount);
        updateRecordTableLog.setUpdateDesc(updateDesc);
        updateRecordTableLog.setBeforeVersion(beforeVersionValue);
        updateRecordTableLog.setAfterVersion(afterVersionValue);

        updateRecordItemVo.setSysUpdateRecordColumnLogs(updateRecordColumnLogs);
        if (!diffSet.isEmpty()) {
            updateRecordItemVo.setDiffSet(diffSet);
        }
        return updateRecordItemVo;
    }

    /**
     * 不同类型的数据格式化
     *
     * @param value
     * @return
     */
    public static String formatColumnValue(Object value) {
        if (value == null) {
            return "";
        }
        try {
            if (value instanceof Date) {
                Date date = (Date) value;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int hour = calendar.get(Calendar.HOUR);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                // 如果时分秒都为空，则使用年月日格式化
                if (hour == 0 && minute == 0 && second == 0) {
                    return UpdateRecordConfigProperties.DATE_FORMAT.format(date);
                } else {
                    // 使用年月日时分秒格式化
                    return UpdateRecordConfigProperties.DATE_TIME_FORMAT.format(date);
                }
            } else if (value instanceof LocalDate) {
                LocalDate localDate = (LocalDate) value;
                long time = localDate.atStartOfDay(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
                return UpdateRecordConfigProperties.DATE_FORMAT.format(new Date(time));
            } else if (value instanceof LocalDateTime) {
                LocalDateTime localDateTime = (LocalDateTime) value;
                long time = localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
                return UpdateRecordConfigProperties.DATE_TIME_FORMAT.format(new Date(time));
            }
        } catch (Exception e) {
            log.error("格式化字符串异常，跳过", e);
        }
        return value.toString();
    }

    private static boolean compareBigDecimal(Object beforeValue, Object afterValue) {
        if (beforeValue instanceof BigDecimal || afterValue instanceof BigDecimal) {
            BigDecimal beforeVal = null;
            if (beforeValue == null || "".equals(beforeValue.toString())) {
                beforeValue = "0.0";
            }
            if (afterValue == null || "".equals(afterValue.toString())) {
                afterValue = "0.0";
            }
            beforeVal = new BigDecimal(beforeValue.toString()).setScale(4, BigDecimal.ROUND_UP);
            BigDecimal afterVal = new BigDecimal(afterValue.toString()).setScale(4, BigDecimal.ROUND_UP);
            if (beforeVal.compareTo(afterVal) == 0) {
                return true;
            }
        }
        return false;
    }

    private static boolean valueIsEquals(Object beforeValue, Object afterValue) {
        // 判断是否相等
        if (beforeValue == null && afterValue == null) {
            return true;
        }
        if (beforeValue == null || afterValue == null) {
            return false;
        }
        return beforeValue.equals(afterValue);
    }

}

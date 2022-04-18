package io.sihuan.modules.attendance.constant;

import io.sihuan.modules.attendance.entity.AttendanceDailyEntity;

public class AttConstant {
    public static final String _ZC = "正常";
    public static final String _QK = "缺卡";
    public static final String _CD = "迟到";
    public static final String _WQ = "外勤";
    public static final String _KGCD = "旷工迟到";
    public static final String _ZT = "早退";
    public static final String _KG = "旷工";
    public static final String _YZCD = "严重迟到";
    //0:休息日 -1：钉钉打卡正常 1：非正常考勤 2：签到 3：出差 4：外出 5：请假 6:旷工 7:旷工迟到 8:严重迟到 9:缺卡（上午缺卡或下午缺卡）10:迟到 11：早退 12: 加班 13：之前场景都不存在，视为正常
    public static final String _CALC_TYPE_XX = "0";
    public static final String _CALC_TYPE_ZC = "-1";
    public static final String _CALC_TYPE_EXCP = "1";
    public static final String _CALC_TYPE_SIGN = "2";
    public static final String _CALC_TYPE_CC = "3";
    public static final String _CALC_TYPE_WC = "4";
    public static final String _CALC_TYPE_LEAVE = "5";
    public static final String _CALC_TYPE_KG = "6";
    public static final String _CALC_TYPE_KGCD = "7";
    public static final String _CALC_TYPE_YZCD = "8";
    public static final String _CALC_TYPE_QK = "9";
    public static final String _CALC_TYPE_CD = "10";
    public static final String _CALC_TYPE_ZT = "11";
    public static final String _CALC_TYPE_JB = "12";
    public static final String _CALC_TYPE_NO = "13";
    //上下班时间为空
    public static final String ON_OFF_NULL = "1";
    //上班时间为空，下班时间不为空
    public static final String ON_NULL_OFF_NOT_NULL = "2";
    //上班时间不为空，下班时间为空
    public static final String ON_NOT_NULL_OFF_NULL = "3";

    public static final Double DAY_OURS = 8.0d;
    public static final String _13_OURS = " 13:00:00";
    public static final String _1230_OURS = " 12:30:00";
    /**
     * 判断上下班打卡情况
     * @param entity
     * @return
     */
    public static String getAttOnOffType(AttendanceDailyEntity entity) {
        if ((entity.getOnDutyUserCheckTime() == null || "".equals(entity.getOnDutyUserCheckTime()))
                && (entity.getOffDutyUserCheckTime() == null || "".equals(entity.getOffDutyUserCheckTime()))) {
            return ON_OFF_NULL;
        }
        if ((entity.getOnDutyUserCheckTime() == null || "".equals(entity.getOnDutyUserCheckTime())
                && entity.getOffDutyUserCheckTime() != null && !"".equals(entity.getOffDutyUserCheckTime()))) {
            return ON_NULL_OFF_NOT_NULL;
        }
        if (entity.getOnDutyUserCheckTime() != null && !"".equals(entity.getOnDutyUserCheckTime())
                && (entity.getOffDutyUserCheckTime() == null || "".equals(entity.getOffDutyUserCheckTime()))) {
            return ON_NOT_NULL_OFF_NULL;
        }
        return "";
    }

}

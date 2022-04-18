package io.sihuan.modules.attendance.utils;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.taobao.api.ApiException;
import com.taobao.api.internal.util.StringUtils;
import io.sihuan.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.UUID;

public class DingtalkUtils {
    private static Logger logger = LoggerFactory.getLogger(DingtalkUtils.class);
    private static final String APP_KEY = "dingnsarycn8zr9rot8h";
    private static final String APP_SECRET = "IYK9-n_IefaEf2l36ZoKb4rTzYcFg2M5Y1lbXocLU9_bSOowzxA-Ced98CTVru8i";
    private static final String OP_USER_ID = "18310110262";
    private static Long cacheTime = 0l;
    private static String token = null;

    /**
     * 获取token接口
     * @return
     */
    synchronized public static String getToken() {
        try {
            Long nowTime = new Date().getTime();
            if (nowTime - cacheTime >= 3600000l || token == null) {
                cacheTime = nowTime;
                DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
                OapiGettokenRequest req = new OapiGettokenRequest();
                req.setAppkey(APP_KEY);
                req.setAppsecret(APP_SECRET);
                req.setHttpMethod("GET");
                OapiGettokenResponse rsp = client.execute(req);
                if (rsp != null && rsp.getBody() != null) {
                    JSONObject result = JSONObject.parseObject(rsp.getBody());
                    token = result.getString("access_token");
                }
            }
        } catch (ApiException e) {
            logger.error(e.getMessage(), e);
        }
        return token;
    }

    /**
     * 考勤组接口
     * @param cursor
     * @return
     */
    public static String getAttendanceGroup(Long cursor) {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/attendance/group/minimalism/list");
            OapiAttendanceGroupMinimalismListRequest req = new OapiAttendanceGroupMinimalismListRequest();
            req.setOpUserId(OP_USER_ID);
            if (cursor > 0) {
                req.setCursor(cursor);
            }
            OapiAttendanceGroupMinimalismListResponse rsp = client.execute(req, getToken());
            return buildReturn(rsp.getBody());
        } catch (ApiException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 考勤组用户清单接口
     * @param groupId
     * @param cursor
     * @return
     */
    public static String getAttendanceGroupUser(Long groupId, Long cursor) {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/attendance/group/memberusers/list");
            OapiAttendanceGroupMemberusersListRequest req = new OapiAttendanceGroupMemberusersListRequest();
            req.setOpUserId(OP_USER_ID);
            req.setGroupId(groupId);
            if (cursor > 0) {
                req.setCursor(cursor);
            }
            OapiAttendanceGroupMemberusersListResponse rsp = client.execute(req, getToken());
            return buildReturn(rsp.getBody());
        } catch (ApiException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 钉钉用户详细信息接口
     * @param userId
     * @return
     */
    public static String getUserDetail(String userId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/get");
            OapiV2UserGetRequest req = new OapiV2UserGetRequest();
            req.setUserid(userId);
            OapiV2UserGetResponse rsp = client.execute(req, getToken());
            return buildReturn(rsp.getBody());
        } catch (ApiException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 钉钉每日考勤结果接口
     * @param cols
     * @param userId
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getAttendanceDaily(String cols, String userId, String startDate, String endDate) {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/attendance/getcolumnval");
            OapiAttendanceGetcolumnvalRequest req = new OapiAttendanceGetcolumnvalRequest();
            req.setUserid(userId);
            req.setColumnIdList(cols);
            req.setFromDate(DateUtils.stringToDate(startDate, "yyyy-MM-dd"));
            req.setToDate(DateUtils.stringToDate(endDate, "yyyy-MM-dd"));
            OapiAttendanceGetcolumnvalResponse rsp = client.execute(req, getToken());
            return buildReturn(rsp.getBody());
        } catch (ApiException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 钉钉签到接口，一次最多取10天的数据
     * @param userId
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getAttendanceSign(String userId, String startDate, String endDate) {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/checkin/record/get");
            OapiCheckinRecordGetRequest req = new OapiCheckinRecordGetRequest();
            req.setUseridList(userId);
            req.setStartTime(DateUtils.stringToDate(startDate, "yyyy-MM-dd").getTime());
            req.setEndTime(DateUtils.stringToDate(endDate, "yyyy-MM-dd").getTime());
            req.setCursor(0L);
            req.setSize(100L);
            OapiCheckinRecordGetResponse rsp = client.execute(req, getToken());
            return buildReturn(rsp.getBody());
        } catch (ApiException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static void postAtt(Date d, String userId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/attendance/record/upload");
            OapiAttendanceRecordUploadRequest req = new OapiAttendanceRecordUploadRequest();
            req.setUserid(userId);
            req.setDeviceName("集团本部张家湾");
            req.setDeviceId("JTZJW");
            req.setUserCheckTime(d.getTime());
            OapiAttendanceRecordUploadResponse rsp = client.execute(req, getToken());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static String buildReturn(String rsp) {
        if (rsp != null) {
            JSONObject result = JSONObject.parseObject(rsp);
            if (result.getInteger("errcode") == 0) {
                return result.get("result") != null ? result.get("result").toString() : null;
            }
        }
        return null;
    }




    public static void main(String[] args) {

        //System.out.println(getToken());
        //System.out.println("1".indexOf("."));
        //System.out.println(UUID.randomUUID());
        postAtt(DateUtils.stringToDate("2022-02-28 08:25:30", DateUtils.DATE_TIME_PATTERN), "18310110262");



    }
}

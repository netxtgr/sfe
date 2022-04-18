package io.sihuan.modules.attendance.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.sihuan.modules.attendance.entity.AttendanceGroupEntity;
import io.sihuan.modules.attendance.entity.AttendanceUserEntity;
import io.sihuan.modules.attendance.service.AttendanceGroupService;
import io.sihuan.modules.attendance.service.AttendanceUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DingtalkSyncJobUtils {
    private static Logger logger = LoggerFactory.getLogger(DingtalkSyncJobUtils.class);

    public static DingtalkSyncJobUtils dingtalkSyncJobUtils;
    @Autowired
    private AttendanceGroupService attendanceGroupService;
    @Autowired
    private AttendanceUserService attendanceUserService;

    @PostConstruct
    public void init(){
        dingtalkSyncJobUtils = this;
        dingtalkSyncJobUtils.attendanceGroupService = this.attendanceGroupService;
        dingtalkSyncJobUtils.attendanceUserService = this.attendanceUserService;
    }
    public static void syncAttGroup() {
        boolean hasMore = true;
        long cursor = 0l;
        while (hasMore) {
            JSONObject result = JSONObject.parseObject(DingtalkUtils.getAttendanceGroup(cursor));
            saveAttendanceGroup(result);
            hasMore = result.getBoolean("has_more");
            if (hasMore) {
                cursor = result.getLong("cursor");
            }
        }
    }
    public static void syncAttGroupUser(){
        List<AttendanceGroupEntity> gls = dingtalkSyncJobUtils.attendanceGroupService.list();
        if (gls != null && gls.size() > 0) {
            for (AttendanceGroupEntity entity : gls) {
                postGroupUser(Long.parseLong(entity.getGroupId().toString()));
            }
        }
    }

   /* public static void syncAttUserDetail(){
        List<AttendanceUserEntity> users = dingtalkSyncJobUtils.attendanceUserService.list();
        if (users != null && users.size() > 0) {
            for (AttendanceUserEntity user : users) {
                postUserDetail(user);
            }
            dingtalkSyncJobUtils.attendanceUserService.saveOrUpdateBatch(users);
        }
    }*/

    private static void saveAttendanceGroup(JSONObject json) {
        JSONArray arr = json.getJSONArray("result");
        if (arr != null && arr.size() > 0) {
            for(int i = 0; i < arr.size(); i++) {
                JSONObject object = arr.getJSONObject(i);
                int id = object.getInteger("id");
                String name = object.getString("name");
                AttendanceGroupEntity groupEntity = dingtalkSyncJobUtils.attendanceGroupService.getGroup(id);
                Date d = new Date();
                if (groupEntity == null) {
                    groupEntity = new AttendanceGroupEntity();
                    groupEntity.setCreated(d);
                }
                groupEntity.setGroupId(id);
                groupEntity.setGroupName(name);
                groupEntity.setUpdated(d);
                dingtalkSyncJobUtils.attendanceGroupService.saveOrUpdate(groupEntity);
            }
        }
    }

    private static void postGroupUser(Long groupId) {
        boolean hasMore = true;
        long cursor = 0l;
        try {
            List<AttendanceUserEntity> users = new ArrayList<>();
            while (hasMore) {
                JSONObject result = JSONObject.parseObject(DingtalkUtils.getAttendanceGroupUser(groupId, cursor));
                //logger.info("result===" + result);
                setAttendanceGroupUser(result, groupId, users);
                hasMore = result.getBoolean("has_more");
                if (hasMore) {
                    cursor = result.getLong("cursor");
                }
            }
            if (users.size() > 0) {
                //boolean del = dingtalkSyncJobUtils.attendanceUserService.removeByGroupId(Integer.parseInt(groupId.toString()));
                if (users != null && users.size() > 0) {
                    for (AttendanceUserEntity user : users) {
                        postUserDetail(user);
                    }
                }
                dingtalkSyncJobUtils.attendanceUserService.saveOrUpdateBatch(users);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private static void setAttendanceGroupUser(JSONObject json, Long groupId, List<AttendanceUserEntity> users) {
        JSONArray arr = json.getJSONArray("result");
        if (arr != null && arr.size() > 0) {
            for(int i = 0; i < arr.size(); i++) {
                String userId = arr.getString(i);
                AttendanceUserEntity user = dingtalkSyncJobUtils.attendanceUserService.getByUserId(userId);
                Date d = new Date();
                if (user == null) {
                    user = new AttendanceUserEntity();
                    user.setCreated(d);
                }
                user.setUpdated(d);
                user.setUserId(userId);
                user.setGroupId(Integer.parseInt(groupId.toString()));
                users.add(user);
            }
        }
    }

    private static void postUserDetail(AttendanceUserEntity user) {
        try {
            JSONObject result = JSONObject.parseObject(DingtalkUtils.getUserDetail(user.getUserId()));
            user.setEmail(result.getString("email"));
            user.setMobilePhone(result.getString("mobile"));
            user.setEmpName(result.getString("name"));
            user.setUpdated(new Date());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}

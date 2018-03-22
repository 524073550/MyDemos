package com.lw.commonlib.base.utils;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 环信管理类，主要用户显示用户昵称和头像
 * Created by lx on 2016/9/13.
 */
public class HXManager {
    private Map<String, HXUser> userMap = new HashMap<>();
    private static HXManager instance;

    private HXManager() {
    }

    public synchronized static HXManager getInstance() {
        if (instance == null) {
            instance = new HXManager();
        }
        return instance;
    }

    public void saveUser(List<HXUser> hxUsers) {
        if (hxUsers == null || hxUsers.size() == 0) {
            return;
        }
        userMap.clear();
        for (int i = 0; i < hxUsers.size(); i++) {
            userMap.put(hxUsers.get(i).userId, hxUsers.get(i));
        }
    }

    public List<HXUser> getUser(List<String> userIds) {
        List<HXUser> list = new ArrayList<>();
        if(userIds == null || userIds.size() == 0){
            return list;
        }
        for (int i = 0; i < userIds.size(); i++) {
            for( String userId : userMap.keySet()){
                if(userId.equals(userIds.get(i))){
                    list.add(userMap.get(userId));
                    break;
                }
            }
        }
        return list;
    }

    public HXUser getUser(String userId){
        List<String> userIds = new ArrayList<>();
        userIds.add(userId);
        List<HXUser> user = getUser(userIds);
        return user == null || user.size() == 0 ? null : user.get(0);
    }

    public class HXUser {
        @SerializedName("APP_USER_ID")
        public String userId;
        @SerializedName("HEAD_PIC")
        public String headUrl;
        @SerializedName("NICK_NAME")
        public String nickname;
    }
}

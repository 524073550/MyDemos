package com.zhuke.yixun.presenter;

import com.apkfuns.logutils.LogUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.zhuke.yixun.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 15653 on 2018/3/27.
 */

public class MessagePresenterImpl implements MessageFragmentView.MessagePresenter {
    private MessageFragmentView.MessageView mMessageView;
    private List<EMConversation> mEMConversations ;

    public MessagePresenterImpl(MessageFragmentView.MessageView messageView) {
        this.mMessageView = messageView;
        mEMConversations = new ArrayList<>();
    }

    @Override
    public void loadAllMessage() {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
                mEMConversations.clear();
                mEMConversations.addAll(conversations.values());
                Collections.sort(mEMConversations, new Comparator<EMConversation>() {
                    @Override
                    public int compare(EMConversation o1, EMConversation o2) {
                        return (int) (o2.getLastMessage().getMsgTime() - o1.getLastMessage().getMsgTime());
                    }
                });
            }
        });

        ThreadUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mMessageView.loadMessageSuccse();
            }
        });
    }


    @Override
    public List<EMConversation> getEMConversations(){
        return mEMConversations;
    }
}

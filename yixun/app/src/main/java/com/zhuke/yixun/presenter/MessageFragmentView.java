package com.zhuke.yixun.presenter;


import com.hyphenate.chat.EMConversation;

import java.util.List;

/**
 * Created by 15653 on 2018/3/26.
 */

public class MessageFragmentView {
    public interface MessageView{
        void loadMessageSuccse();
    }

    public interface MessagePresenter{
        void loadAllMessage();
        List<EMConversation> getEMConversations();
    }
}

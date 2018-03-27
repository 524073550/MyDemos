package com.zhuke.yixun.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.zhuke.comlibrary.base.BaseFragment;
import com.zhuke.yixun.R;
import com.zhuke.yixun.adapter.EMMessageListenerAdapter;
import com.zhuke.yixun.adapter.MessageAdapter;
import com.zhuke.yixun.presenter.MessageFragmentView;
import com.zhuke.yixun.presenter.MessagePresenterImpl;
import com.zhuke.yixun.ui.activity.ChatActivity;
import com.zhuke.yixun.utils.ThreadUtils;

import java.util.List;

/**
 * Created by 15653 on 2018/3/26.
 */

public class MessageFragment extends BaseFragment implements MessageFragmentView.MessageView, MessageAdapter.OnMessageItemClickListener {

    private RecyclerView mRecyclerView;
    private MessageFragmentView.MessagePresenter mMessagePresenter;
    private MessageAdapter mMessageAdapter;

    protected void onLazyLoad() {
        mMessagePresenter = new MessagePresenterImpl(this);
        mMessagePresenter.loadAllMessage();
    }
    @Override
    protected void initEvent() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mMessageAdapter = new MessageAdapter(getContext(), mMessagePresenter.getEMConversations());
        mMessageAdapter.setOnMessageClickListener(this);
        mRecyclerView.setAdapter(mMessageAdapter);
        EMClient.getInstance().chatManager().addMessageListener(mEMMessageListenerAdapter);
    }

    @Override
    protected void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycleview);
    }

    private EMMessageListenerAdapter mEMMessageListenerAdapter = new EMMessageListenerAdapter() {

        @Override
        public void onMessageReceived(List<EMMessage> list) {
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mMessagePresenter.loadAllMessage();
                    mRecyclerView.scrollToPosition(0);
                }
            });
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(mEMMessageListenerAdapter);
    }

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_message,container,false);
    }

    @Override
    public void loadMessageSuccse() {
        mMessageAdapter.notifyDataSetChanged();
    }

    @Override
    public void setOnMessageClick(String messageID) {
        Intent intent = new Intent(getContext(), ChatActivity.class);
        intent.putExtra("user_name", messageID);
        getContext().startActivity(intent);
    }
}

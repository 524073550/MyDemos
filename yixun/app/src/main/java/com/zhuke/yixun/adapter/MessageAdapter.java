package com.zhuke.yixun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.util.DateUtils;
import com.zhuke.yixun.R;
import com.zhuke.yixun.ui.fragment.MessageFragment;

import java.util.Date;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by 15653 on 2018/3/27.
 */

public class MessageAdapter extends RecyclerView.Adapter {

    private List<EMConversation> mEMConversations;
    private Context mContext;

    public MessageAdapter(Context context, List<EMConversation> emConversations) {
        this.mEMConversations = emConversations;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MessageHolder(LayoutInflater.from(mContext).inflate(R.layout.item_message, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MessageHolder messageHolder = (MessageHolder) holder;
        final EMConversation emConversation = mEMConversations.get(position);
//        messageHolder.mAvatar.setImageBitmap();
        messageHolder.mUser_name.setText(emConversation.conversationId());
        EMMessage lastMessage = emConversation.getLastMessage();
        if (lastMessage.getBody() instanceof EMTextMessageBody) {
            messageHolder.mLast_message.setText(((EMTextMessageBody) lastMessage.getBody()).getMessage());
        } else {
            messageHolder.mLast_message.setText("非文本信息");
        }
        messageHolder.mTimestamp.setText(DateUtils.getTimestampString(new Date(lastMessage.getMsgTime())));
        int unreadMsgCount = emConversation.getUnreadMsgCount();
        LogUtils.e(unreadMsgCount);
        if (unreadMsgCount > 0) {
            messageHolder.mUnread_count.setVisibility(View.VISIBLE);
            messageHolder.mUnread_count.setText(String.valueOf(unreadMsgCount));
        } else {
            messageHolder.mUnread_count.setVisibility(View.GONE);
        }
        messageHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("aaa");
                if (mOnMessageItemClickListener != null){
                    mOnMessageItemClickListener.setOnMessageClick(emConversation.conversationId());
                }
            }
        });
    }

    private OnMessageItemClickListener mOnMessageItemClickListener;
    public void setOnMessageClickListener(OnMessageItemClickListener onMessageClickListener ){
        this.mOnMessageItemClickListener = onMessageClickListener;

    }
    public interface OnMessageItemClickListener{
        void setOnMessageClick(String messageID);
    }

    @Override
    public int getItemCount() {
        return mEMConversations == null ? 0 : mEMConversations.size();
    }

    public class MessageHolder extends RecyclerView.ViewHolder {

        private final ImageView mAvatar;
        private final TextView mUser_name;
        private final TextView mLast_message;
        private final TextView mTimestamp;
        private final TextView mUnread_count;

        public MessageHolder(View itemView) {
            super(itemView);
            mAvatar = (ImageView) itemView.findViewById(R.id.avatar);
            mUser_name = (TextView) itemView.findViewById(R.id.user_name);
            mLast_message = (TextView) itemView.findViewById(R.id.last_message);
            mTimestamp = (TextView) itemView.findViewById(R.id.timestamp);
            mUnread_count = (TextView) itemView.findViewById(R.id.unread_count);
        }
    }
}

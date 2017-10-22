package com.sreevidya.opal.screen.home.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sreevidya.opal.R;
import com.sreevidya.opal.model.database.Message;

import java.util.ArrayList;
import java.util.List;


public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int USER = 817;
    private static final int BOT = 239;

    List<Message> messageList = new ArrayList<>();


    public MessageAdapter() {

    }

    @Override
    public int getItemViewType(int position) {
        return messageList.get(position).isUser() ? USER : BOT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        View view;
        switch (viewType) {
            case USER:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_message_right, parent, false);
                viewHolder = new UserMessageHolder(view);
                break;
            case BOT:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_message_left, parent, false);
                viewHolder = new BotMessageHolder(view);
                break;
        }

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message m = messageList.get(position);
        if (m.isUser()) {
            ((UserMessageHolder) holder).messageTextView.setText(m.getText());
            if (m.getPhotoUrl() != null) {
                Glide.with(((UserMessageHolder) holder).photoImageView.getContext())
                        .load(m.getPhotoUrl())
                        .into(((UserMessageHolder) holder).photoImageView);
            }
            ((UserMessageHolder) holder).timeTextView.setText(m.getCreated());
        } else {
            ((BotMessageHolder) holder).messageTextView.setText(m.getText());
            ((BotMessageHolder) holder).timeTextView.setText(m.getCreated());
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public void append(Message m) {
        messageList.add(m);
        notifyDataSetChanged();
    }

    public void setMessages(List<Message> messages) {
        if (messages != null && messages.size() != 0) {
            messageList.clear();
            messageList.addAll(messages);
            notifyDataSetChanged();
        }
    }

    class UserMessageHolder extends RecyclerView.ViewHolder {
        TextView messageTextView;
        ImageView photoImageView;
        TextView timeTextView;

        public UserMessageHolder(View itemView) {
            super(itemView);
            photoImageView = (ImageView) itemView.findViewById(R.id.photoImageView);
            messageTextView = (TextView) itemView.findViewById(R.id.messageTextView);
            timeTextView = (TextView) itemView.findViewById(R.id.timeTextView);

        }
    }

    class BotMessageHolder extends RecyclerView.ViewHolder {

        TextView messageTextView;
        TextView timeTextView;

        public BotMessageHolder(View itemView) {
            super(itemView);
            messageTextView = (TextView) itemView.findViewById(R.id.messageTextView);
            timeTextView = (TextView) itemView.findViewById(R.id.timeTextView);
        }
    }
}

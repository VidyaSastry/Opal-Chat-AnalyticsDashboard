package com.sreevidya.opal.screen.home.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.sreevidya.opal.R;
import com.sreevidya.opal.model.database.Message;
import com.sreevidya.opal.screen.home.MessageAdapter;
import com.sreevidya.opal.screen.home.presenter.chat.ChatContract;
import com.sreevidya.opal.screen.home.presenter.chat.ChatPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatFragment extends Fragment
        implements ChatContract.View {

    @BindView(R.id.messageEditText)
    EditText messageEdt;

    @BindView(R.id.sendButton)
    Button mButtonSend;

    @BindView(R.id.messageListView)
    ListView messageListView;

    private ChatContract.Presenter presenter;

    private MessageAdapter messageAdapter;

    public ChatFragment() {
    }

    public static ChatFragment newInstance() {

        Bundle args = new Bundle();

        ChatFragment fragment = new ChatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Context context = getActivity().getApplicationContext();
        ArrayList<Message> messages = new ArrayList<>();
        messageAdapter = new MessageAdapter(context, R.layout.item_message_left, messages);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (presenter == null) {
            presenter = new ChatPresenter(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        messageListView.setAdapter(messageAdapter);
        presenter.subscribe(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        messageAdapter.clear();
        presenter.unsubscribe();
    }

    @OnClick(R.id.sendButton)
    void onSendClick(View view) {
        presenter.onSendClick();
    }


    @Override
    public String getMessageText() {
        return messageEdt.getText().toString();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void makeToast(@StringRes int strId) {
        Toast.makeText(getActivity(), getString(strId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(ChatContract.Presenter presenter) {
        this.presenter = presenter;
    }

//    private void setAdapter(){
//        if(messageListView.getAdapter() == null){
//            messageListView.setAdapter(messageAdapter);
//        }
//        else{
//            messageAdapter.notifyDataSetChanged();
//        }
//    }
}

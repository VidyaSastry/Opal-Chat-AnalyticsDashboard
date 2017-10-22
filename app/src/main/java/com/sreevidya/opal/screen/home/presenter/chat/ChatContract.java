package com.sreevidya.opal.screen.home.presenter.chat;


import com.sreevidya.opal.model.database.Message;
import com.sreevidya.opal.screen.login.presenter.BaseContract;

import java.util.List;

public interface ChatContract {

    interface View extends BaseContract.View {

        String getMessageText();

        void setPresenter(Presenter presenter);

        void setMessages(List<Message> messages);

        void addMessage(Message m);

        void clearField();
    }

    interface Presenter extends BaseContract.Presenter<ChatContract.View> {

        void onSendClick();
    }
}

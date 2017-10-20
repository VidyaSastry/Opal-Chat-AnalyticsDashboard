package com.sreevidya.opal.screen.home.presenter.chat;


import com.sreevidya.opal.screen.login.presenter.BaseContract;

public interface ChatContract {

    interface View extends BaseContract.View {

        String getMessageText();

        void setPresenter(Presenter presenter);

    }

    interface Presenter extends BaseContract.Presenter<ChatContract.View> {

        void onSendClick();
    }
}

package com.sreevidya.opal.model.database;


import com.sreevidya.opal.screen.home.MessageAdapter;

public interface DatabaseSource {

    void createMessage(Message message, DatabaseCallback<Void> dbCallback);

    void getMessage(MessageAdapter messageAdapter, DatabaseCallback<Void> dbCallback);

    interface DatabaseCallback<C> {
        void onSuccess(C c);

        void onFailure(Exception e);
    }
}

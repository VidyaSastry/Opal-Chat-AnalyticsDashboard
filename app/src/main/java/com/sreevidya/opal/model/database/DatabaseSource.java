package com.sreevidya.opal.model.database;


import com.sreevidya.opal.model.auth.User;

import java.util.List;

public interface DatabaseSource {

    void createMessage(String uid, Message message, DatabaseCallback<Message> dbCallback);

    void addUser(User u, DatabaseCallback<Void> dbCallback);

    void getMessages(User u, DatabaseCallback<List<Message>> dbCallback);

    interface DatabaseCallback<C> {
        void onSuccess(C c);

        void onFailure(Exception e);
    }
}

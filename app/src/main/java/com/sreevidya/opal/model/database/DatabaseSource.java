package com.sreevidya.opal.model.database;


public interface DatabaseSource {

    void createMessage(String message, DatabaseCallback<Void> dbCallback);

    void getMessage(String messageId, DatabaseCallback<Void> dbCallback);

    interface DatabaseCallback<C> {
        void onSuccess(C c);

        void onFailure(Exception e);
    }
}

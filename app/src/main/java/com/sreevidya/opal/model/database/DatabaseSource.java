package com.sreevidya.opal.model.database;


public interface DatabaseSource {
    void createProfile(Profile profile, DbCallback<Void> dbCallback);

    void getProfile(String uid, DbCallback<Profile> dbCallback);

    void deleteProfile(String uid, DbCallback<Void> dbCallback);

    void updateProfile(Profile profile, DbCallback<Profile> dbCallback);

    interface DbCallback<C> {
        void onSuccess(C c);

        void onFailure(String s);
    }
}

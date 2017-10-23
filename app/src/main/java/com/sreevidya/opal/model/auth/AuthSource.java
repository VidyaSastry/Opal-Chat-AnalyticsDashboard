package com.sreevidya.opal.model.auth;


public interface AuthSource {
    void createNewAccount(Credentials credentials, AuthCallback<Void> authCallback);

    void attemptLogin(Credentials credentials, AuthCallback<Void> authCallback);

    void getUser(AuthCallback<User> authCallback);

    interface AuthCallback<C> {
        void onSuccess(C c);

        void onFailure(Exception e);
    }
}

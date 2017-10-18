package com.sreevidya.opal.screen.login.presenter.login;


import com.sreevidya.opal.model.auth.Credentials;
import com.sreevidya.opal.screen.login.presenter.BaseContract;

public interface LoginContract {
    interface View extends BaseContract.View {

        String getEmail();

        String getPassword();

        void showHomeScreen();

        void showRegistrationScreen();

        void showProgressIndicator(boolean show);
    }

    interface Presenter extends BaseContract.Presenter<LoginContract.View> {

        void attemptLogin(Credentials credentials);

        void onLoginClick();

        void onRegisterClick();


    }
}

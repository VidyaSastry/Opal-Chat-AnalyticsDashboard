package com.sreevidya.opal.screen.login.presenter.registration;


import com.sreevidya.opal.screen.login.presenter.BaseContract;

public interface RegistrationContract {
    interface View extends BaseContract.View {
        String getName();

        String getEmail();

        String getPassword();

        void showHomeScreen();

        void showProgressIndicator(boolean show);
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void onRegisterClick();
    }
}

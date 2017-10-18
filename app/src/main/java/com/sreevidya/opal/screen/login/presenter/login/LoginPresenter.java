package com.sreevidya.opal.screen.login.presenter.login;


import android.support.annotation.VisibleForTesting;

import com.sreevidya.opal.R;
import com.sreevidya.opal.model.auth.AuthInjector;
import com.sreevidya.opal.model.auth.AuthSource;
import com.sreevidya.opal.model.auth.Credentials;
import com.sreevidya.opal.model.auth.User;
import com.sreevidya.opal.model.database.DatabaseSource;
import com.sreevidya.opal.model.database.Profile;


public class LoginPresenter implements LoginContract.Presenter {
    public static final String TAG = "LoginPresenter";

    private AuthSource authSource;
    private DatabaseSource databaseSource;

    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.authSource = AuthInjector.getInstance();
    }

    @VisibleForTesting
    public LoginPresenter(LoginContract.View view, AuthSource authSource) {
        this.view = view;
        this.authSource = authSource;
    }

    public void getUser() {
        view.showProgressIndicator(true);

        authSource
                .getUser(new AuthSource.AuthCallback<User>() {
                    @Override
                    public void onSuccess(User user) {
                        view.showProgressIndicator(false);
                        view.showHomeScreen();
                    }

                    @Override
                    public void onFailure(String s) {
                        view.makeToast(s);
                    }
                });

    }

    @Override
    public void attemptLogin(Credentials credentials) {
        view.showProgressIndicator(true);
        authSource
                .attemptLogin(credentials, new AuthSource.AuthCallback<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        if (isViewVisible()) {
                            view.showHomeScreen();
                        }
                    }

                    @Override
                    public void onFailure(String s) {
                        view.makeToast(s);
                    }
                });
    }

    @Override
    public void onLoginClick() {
        String email = view.getEmail();
        String password = view.getPassword();
        if (email.isEmpty()) {
            view.makeToast(R.string.toast_error_email_empty);
        } else if (password.isEmpty()) {
            view.makeToast(R.string.toast_error_password_empty);
        } else if (!email.contains("@")) {
            view.makeToast(R.string.toast_error_invalid_email);
        } else if (password.length() > 19) {
            view.makeToast(R.string.toast_error_long_password);
        } else if (password.length() < 6) {
            view.makeToast(R.string.toast_error_short_password);
        } else
            attemptLogin(new Credentials(email, email, password));
    }


    @Override
    public void onRegisterClick() {
        view.showRegistrationScreen();
    }

    @Override
    public boolean isViewVisible() {
        return view != null;
    }

    @Override
    public void subscribe(LoginContract.View view) {
        this.view = view;
        getUser();
    }

    @Override
    public void unsubscribe() {
        this.view = null;
    }

    private void registerUser(User user) {
        Profile profile = new Profile(user.getUid(), user.getName(), user.getEmail());
    }
}

package com.sreevidya.opal.screen.login.presenter.login;


import com.sreevidya.opal.R;
import com.sreevidya.opal.model.auth.AuthInjector;
import com.sreevidya.opal.model.auth.AuthSource;
import com.sreevidya.opal.model.auth.Credentials;
import com.sreevidya.opal.model.auth.User;


public class LoginPresenter implements LoginContract.Presenter {

    private AuthSource authSource;

    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.authSource = AuthInjector.getInstance();
    }

    public void getUser() {

        authSource
                .getUser(new AuthSource.AuthCallback<User>() {
                    @Override
                    public void onSuccess(User user) {
                        view.showProgressIndicator(false);
                        view.showHomeScreen();
                    }

                    @Override
                    public void onFailure(Exception s) {
                        view.showProgressIndicator(false);
                        view.makeToast(s.getMessage());
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
                    public void onFailure(Exception e) {
                        view.makeToast(R.string.toast_login_wrong_credentials);
                        view.showProgressIndicator(false);
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

}

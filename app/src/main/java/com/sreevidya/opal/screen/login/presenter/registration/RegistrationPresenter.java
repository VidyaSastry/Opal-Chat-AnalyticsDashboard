package com.sreevidya.opal.screen.login.presenter.registration;

import android.support.annotation.VisibleForTesting;

import com.sreevidya.opal.R;
import com.sreevidya.opal.model.auth.AuthInjector;
import com.sreevidya.opal.model.auth.AuthSource;
import com.sreevidya.opal.model.auth.Credentials;
import com.sreevidya.opal.model.auth.User;
import com.sreevidya.opal.model.database.DatabaseSource;
import com.sreevidya.opal.model.database.Profile;

public class RegistrationPresenter implements RegistrationContract.Presenter {
    private AuthSource authSource;
    private DatabaseSource databaseSource;
    private RegistrationContract.View view;

    public RegistrationPresenter(RegistrationContract.View view) {
        this.view = view;
        this.authSource = AuthInjector.getInstance();


    }

    @VisibleForTesting
    RegistrationPresenter(RegistrationContract.View view, AuthSource authSource, DatabaseSource databaseSource) {
        this.view = view;
        this.authSource = authSource;
        this.databaseSource = databaseSource;
    }

    @Override
    public void onLoginClick() {
        view.makeToast("Login clicked");
        view.showLoginScreen();
    }

    @Override
    public void onRegisterClick() {
        if (validateAccountCredentials()) {
            attemptRegistration(new Credentials(
                    view.getName(),
                    view.getEmail(),
                    view.getPassword()
            ));
        }
    }

    private void attemptRegistration(Credentials credentials) {
        view.showProgressIndicator(true);
        authSource
                .createNewAccount(credentials, new AuthSource.AuthCallback<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        if (isViewVisible()) {
                            view.showHomeScreen();
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        view.makeToast(e.getMessage());
                    }
                });
    }

    private void getUser() {
        authSource
                .getUser(new AuthSource.AuthCallback<User>() {
                    @Override
                    public void onSuccess(User user) {
                        view.showHomeScreen();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        view.makeToast(e.getMessage());
                    }
                });
    }

    private void addUserProfileToDatabase(String uid, String email) {

        Profile profile = new Profile(
                uid,
                view.getName(),
                email
        );
    }


    private boolean validateAccountCredentials() {
        if (view.getName().isEmpty()) {
            view.makeToast(R.string.toast_error_name_empty);
            return false;
        } else if (view.getEmail().isEmpty()) {
            view.makeToast(R.string.toast_error_email_empty);
            return false;
        } else if (!view.getEmail().contains("@")) {
            view.makeToast(R.string.toast_error_invalid_email);
            return false;
        } else if (view.getPassword().isEmpty()) {
            view.makeToast(R.string.toast_error_password_empty);
            return false;
        } else if (view.getPassword().length() < 6) {
            view.makeToast(R.string.toast_error_short_password);
            return false;
        } else if (view.getPassword().length() > 19) {
            view.makeToast(R.string.toast_error_long_password);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isViewVisible() {
        return view != null;
    }

    @Override
    public void subscribe(RegistrationContract.View view) {
        this.view = view;
    }

    @Override
    public void unsubscribe() {
        this.view = null;
    }

}

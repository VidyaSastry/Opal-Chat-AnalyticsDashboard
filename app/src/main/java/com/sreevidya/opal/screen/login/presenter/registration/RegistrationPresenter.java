package com.sreevidya.opal.screen.login.presenter.registration;

import android.support.annotation.VisibleForTesting;

import com.sreevidya.opal.R;
import com.sreevidya.opal.model.auth.AuthInjector;
import com.sreevidya.opal.model.auth.AuthSource;
import com.sreevidya.opal.model.auth.Credentials;
import com.sreevidya.opal.model.auth.User;
import com.sreevidya.opal.model.database.DatabaseInjector;
import com.sreevidya.opal.model.database.DatabaseSource;

public class RegistrationPresenter implements RegistrationContract.Presenter {
    private AuthSource authSource;
    private DatabaseSource databaseSource;
    private RegistrationContract.View view;

    public RegistrationPresenter(RegistrationContract.View view) {
        this.view = view;
        this.authSource = AuthInjector.getInstance();
        this.databaseSource = DatabaseInjector.getInstance();
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

    private void attemptRegistration(final Credentials credentials) {
        view.showProgressIndicator(true);
        authSource
                .createNewAccount(credentials, new AuthSource.AuthCallback<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        getUser(credentials.getUsername());
                    }

                    @Override
                    public void onFailure(Exception e) {
                        view.showProgressIndicator(false);
                        view.makeToast(e.getMessage());
                    }
                });
    }

    private void getUser(final String username) {
        authSource
                .getUser(new AuthSource.AuthCallback<User>() {
                    @Override
                    public void onSuccess(User user) {
                        //view.showHomeScreen();
                        view.makeToast(username + " Login success");
                        user.setName(username);
                        adddUserToDb(user);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        view.showProgressIndicator(false);
                        view.makeToast(e.getMessage());
                    }
                });
    }

    private void adddUserToDb(final User user) {
        databaseSource.addUser(user,
                new DatabaseSource.DatabaseCallback<Void>() {

                    @Override
                    public void onSuccess(Void o) {
                        view.makeToast(user.getName() + " created successfully ");
                        view.showHomeScreen();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        view.makeToast("Failed to create User in db");
                        view.showProgressIndicator(false);
                    }
                });
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

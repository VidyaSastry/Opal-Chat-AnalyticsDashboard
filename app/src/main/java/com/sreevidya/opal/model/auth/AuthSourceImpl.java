package com.sreevidya.opal.model.auth;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class AuthSourceImpl implements AuthSource {

    private FirebaseAuth mFirebaseAuth;

    AuthSourceImpl() {
        this.mFirebaseAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void createNewAccount(Credentials credentials, final AuthCallback<Void> authCallback) {
        mFirebaseAuth.createUserWithEmailAndPassword(
                credentials.getEmail(),
                credentials.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            authCallback.onSuccess(null);
                        } else {
                            authCallback.onFailure("Authentication failed");
                        }
                    }
                });
    }

    @Override
    public void attemptLogin(Credentials credentials, AuthCallback<Void> authCallback) {

    }

    @Override
    public void deleteUser(AuthCallback<Void> authCallback) {

    }

    @Override
    public void logoutUser(AuthCallback<Void> authCallback) {
        mFirebaseAuth.signOut();
    }

    @Override
    public void reAuthenticateUser(String password, AuthCallback<User> authCallback) {

    }

    @Override
    public void getUser(AuthCallback<User> authCallback) {
        FirebaseUser fUser = mFirebaseAuth.getCurrentUser();
        if (fUser != null) {
            User user = new User(
                    mFirebaseAuth.getCurrentUser().getUid(),
                    mFirebaseAuth.getCurrentUser().getDisplayName(),
                    mFirebaseAuth.getCurrentUser().getEmail()
            );
            authCallback.onSuccess(user);
        } else {
            authCallback.onFailure("No logged in user available");
        }

    }
}

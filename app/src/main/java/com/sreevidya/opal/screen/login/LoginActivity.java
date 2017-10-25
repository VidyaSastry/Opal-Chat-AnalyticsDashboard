package com.sreevidya.opal.screen.login;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sreevidya.opal.R;
import com.sreevidya.opal.screen.home.HomeActivity;
import com.sreevidya.opal.screen.login.fragments.LoginFragment;
import com.sreevidya.opal.screen.login.fragments.RegistrationFragment;

import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements ScreenSwitcher {

    public static final String TAG = "LoginActivity";
    public static final String LOGIN_FRAGMENT_TAG = "LOGIN_FRAGMENT";
    public static final String REGISTER_FRAGMENT_TAG = "REGISTER_FRAGMENT";


    FragmentManager fragmentManager;

    public static Intent getLauncherIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        fragmentManager = getFragmentManager();
        switchToLogin();
    }

    @Override
    public void switchToLogin() {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        LoginFragment loginFragment = (LoginFragment) fragmentManager.findFragmentByTag(LOGIN_FRAGMENT_TAG);

        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();
        }

        ft.replace(R.id.fragmentContainer, loginFragment, LOGIN_FRAGMENT_TAG);
        ft.commit();
    }

    @Override
    public void switchToRegistration() {
        FragmentTransaction ft = fragmentManager.beginTransaction();

        RegistrationFragment registrationFragment = (RegistrationFragment) fragmentManager.findFragmentByTag(REGISTER_FRAGMENT_TAG);

        if (registrationFragment == null) {
            registrationFragment = RegistrationFragment.newInstance();
        }

        ft.replace(R.id.fragmentContainer, registrationFragment, REGISTER_FRAGMENT_TAG);
        ft.addToBackStack(REGISTER_FRAGMENT_TAG);
        ft.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ");
        LoginFragment loginFragment = (LoginFragment) fragmentManager.findFragmentByTag(LOGIN_FRAGMENT_TAG);
        if (loginFragment != null) {
            loginFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void startHomeActivity() {
        finish();
        Intent homeIntent = new Intent(this, HomeActivity.class);
        startActivity(homeIntent);
    }
}



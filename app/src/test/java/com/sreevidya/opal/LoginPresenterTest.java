package com.sreevidya.opal;

import com.sreevidya.opal.screen.login.presenter.login.LoginContract;
import com.sreevidya.opal.screen.login.presenter.login.LoginPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    LoginContract.View view;

    LoginContract.Presenter presenter;

    @Before
    public void setup() {
        presenter = new LoginPresenter(view);
    }

    @Test
    public void validationTest_invalidEmail() {
        presenter.onLoginClick();

        Mockito.when(view.getEmail()).thenReturn("abc");
        Mockito.when(view.getPassword()).thenReturn("123456");

        Mockito.verify(view).getEmail();
        Mockito.verify(view).getPassword();

        Mockito.verify(view).makeToast(R.string.toast_error_invalid_email);

    }

}

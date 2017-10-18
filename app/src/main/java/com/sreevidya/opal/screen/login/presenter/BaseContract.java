package com.sreevidya.opal.screen.login.presenter;

import android.support.annotation.StringRes;


public interface BaseContract {
    interface View {
        void makeToast(@StringRes int strId);

        void makeToast(String message);
    }

    interface Presenter<T extends BaseContract.View> {
        boolean isViewVisible();

        void subscribe(T t);

        void unsubscribe();
    }
}

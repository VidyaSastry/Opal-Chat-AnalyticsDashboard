package com.sreevidya.opal.screen.home.presenter.dashboard;

public class DashboardPresenter implements DashboardContract.Presenter {

    DashboardContract.View view;

    public DashboardPresenter(DashboardContract.View view) {
        this.view = view;
    }

    @Override
    public boolean isViewVisible() {
        return view != null;
    }

    @Override
    public void subscribe(DashboardContract.View view) {
        this.view = view;
    }

    @Override
    public void unsubscribe() {
        this.view = null;
    }

}

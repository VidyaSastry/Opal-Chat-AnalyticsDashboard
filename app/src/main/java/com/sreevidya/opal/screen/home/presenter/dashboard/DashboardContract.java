package com.sreevidya.opal.screen.home.presenter.dashboard;


import com.sreevidya.opal.screen.login.presenter.BaseContract;

public interface DashboardContract {
    interface View extends BaseContract.View {


        void setAxis();

        void showVerticalBarChart();

        void showHorizontalBarChart();

        void showSingleLineChart();

        void setLineData(int count, float range);

        void showDualLineChart();

        void showPieChart();

    }

    interface Presenter extends BaseContract.Presenter<DashboardContract.View> {


    }
}

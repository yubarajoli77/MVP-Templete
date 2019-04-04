package com.lysoft.mvptemplete.activities.main.mvp;

public class MainPresenter {
    private MainModel mainModel;
    private MainView mainView;

    public MainPresenter(MainModel mainModel, MainView mainView) {
        this.mainModel = mainModel;
        this.mainView = mainView;
    }

    public void onCreate(){

    }

    public void onDestroy(){}
}

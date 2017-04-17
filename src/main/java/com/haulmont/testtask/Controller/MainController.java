package com.haulmont.testtask.Controller;

import com.google.common.eventbus.Subscribe;
import com.haulmont.testtask.Event.BackButtonEvent;
import com.haulmont.testtask.MainEventBus;
import com.haulmont.testtask.View.MainView;

/**
 * Created by Cok on 13.04.2017.
 */
public class MainController {

    private MainView mainView;

    public MainController(MainView mainView, MainEventBus bus) {
        this.mainView = mainView;
        bus.register(this);
    }

    @Subscribe
    public void initMainPage(BackButtonEvent event) {
        mainView.show();
    }
}

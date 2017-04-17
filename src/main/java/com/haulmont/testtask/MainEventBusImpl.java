package com.haulmont.testtask;

import com.google.common.eventbus.EventBus;

/**
 * Created by Cok on 13.04.2017.
 */
public class MainEventBusImpl implements MainEventBus {

    private EventBus eventBus = new EventBus();
    private static MainEventBusImpl instance = new MainEventBusImpl();

    private MainEventBusImpl() {

    }

    public static MainEventBusImpl getInstance() {
        return instance;
    }

    @Override
    public void post(Object object) {
        eventBus.post(object);
    }

    @Override
    public void register(Object object) {
        eventBus.register(object);
    }

    @Override
    public void unregister(Object object) {
        eventBus.unregister(object);
    }

}

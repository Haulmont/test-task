package com.haulmont.testtask;

/**
 * Created by Cok on 13.04.2017.
 */
public interface MainEventBus {

    void post(Object object);
    void register(Object object);
    void unregister(Object object);
}

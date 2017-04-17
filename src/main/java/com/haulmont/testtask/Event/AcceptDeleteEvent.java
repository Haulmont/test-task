package com.haulmont.testtask.Event;

/**
 * Created by Cok on 16.04.2017.
 */
public class AcceptDeleteEvent {
    private final Object obj;

    public AcceptDeleteEvent(Object objectToDelete) {
        this.obj = objectToDelete;
    }

    public Object getObject() {
        return obj;
    }
}

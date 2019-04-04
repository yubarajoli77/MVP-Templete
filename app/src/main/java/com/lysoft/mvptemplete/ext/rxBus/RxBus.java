package com.lysoft.mvptemplete.ext.rxBus;

import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;

import io.reactivex.Observable;


public class RxBus {

    private final Relay<Object> eventBus = PublishRelay.create().toSerialized();

    public void send(Object object) {
        eventBus.accept(object);
    }

    public Observable<Object> toObservable() {
        return eventBus;
    }

}

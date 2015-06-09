package com.angrychimps.appname.utils;

import com.squareup.otto.Bus;

public enum Otto {
    BUS;
    
    private final Bus bus = new Bus();

    public Bus getBus(){
        return bus;
    }
}

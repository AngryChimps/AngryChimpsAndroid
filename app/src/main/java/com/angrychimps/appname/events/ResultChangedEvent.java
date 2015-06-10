package com.angrychimps.appname.events;

public class ResultChangedEvent {
    public int position;
    public int count;

    public ResultChangedEvent(int position, int count) {
        this.position = position;
        this.count = count;
    }
}

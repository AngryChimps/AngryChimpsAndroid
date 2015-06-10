package com.angrychimps.appname.events;

public class ResultInsertedEvent {
    public int position;
    public int count;

    public ResultInsertedEvent(int position, int count) {
        this.position = position;
        this.count = count;
    }
}

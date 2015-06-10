package com.angrychimps.appname.events;

public class ResultRemovedEvent {
    public int position;
    public int count;

    public ResultRemovedEvent(int position, int count) {
        this.position = position;
        this.count = count;
    }
}

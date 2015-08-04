package com.angrychimps.citizenvet.events;

public class ResultMovedEvent {
    public int fromPosition;
    public int toPosition;

    public ResultMovedEvent(int fromPosition, int toPosition) {
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }
}

package com.angrychimps.appname;


public class Availability {

    private String start, end, calendarID;

    public Availability(String start, String end, String calendarID) {
        this.start = start;
        this.end = end;
        this.calendarID = calendarID;
    }

    public Availability(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getCalendarID() {
        return calendarID;
    }

    public void setCalendarID(String calendarID) {
        this.calendarID = calendarID;
    }
}

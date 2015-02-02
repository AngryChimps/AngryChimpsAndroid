package com.angrychimps.appname;

class TimeSlot {
    private String mTimeSlotId, mStartDate, mStartTime, mEndDate, mEndTime;

    public TimeSlot(String startDate, String startTime, String endDate, String endTime) {
        mStartDate = startDate;
        mStartTime = startTime;
        mEndDate = endDate;
        mEndTime = endTime;
        //set up timeSlotId
    }

    public String getTimeSlotId() {
        return mTimeSlotId;
    }

    public void setTimeSlotId(String timeSlotId) {
        mTimeSlotId = timeSlotId;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public void setStartDate(String startDate) {
        mStartDate = startDate;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public void setStartTime(String startTime) {
        mStartTime = startTime;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }

    public String getEndTime() {
        return mEndTime;
    }

    public void setEndTime(String endTime) {
        mEndTime = endTime;
    }
}

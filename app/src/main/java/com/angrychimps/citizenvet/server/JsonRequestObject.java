package com.angrychimps.citizenvet.server;

import org.json.JSONException;
import org.json.JSONObject;

/*
    This class builds JSONObjects for search and filtering in the StaggeredGridView
 */
public class JsonRequestObject {

    private String text;
    private int[] categories;
    private double latitude;
    private double longitude;
    private double radius_miles = 0;
    private boolean consumer_travels = true;
    private String starting_at;
    private String ending_at;
    private String sort;
    private int limit = 0;
    private int offset = 0;

    //get the current date and the data 14 days from now
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
//        df.getTimeZone();
//        String startDate = df.format(new Date());
//        Calendar c = Calendar.getInstance();
//        try {
//            c.setTime(df.parse(startDate));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        c.add(Calendar.DATE, 14);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
//        String endDate = df.format(c.getTime());
//        Log.i("startDate = ", startDate);
//        Log.i("endDate = ", endDate);

    public JSONObject getJsonObject(){
        JSONObject object = new JSONObject();
        JSONObject payload = new JSONObject();
        try {
            if(text != null) payload.put("text", text);
            if(categories != null) payload.put("categories", categories);
            payload.put("lat", latitude);
            payload.put("lon", longitude);
            if(radius_miles != 0) payload.put("radius_miles", radius_miles);
            if(!consumer_travels) payload.put("consumer_travels", false);
            if(starting_at != null) payload.put("starting_at", starting_at);
            if(ending_at != null) payload.put("ending_at", ending_at);
            if(sort != null) payload.put("sort", sort);
            if(limit != 0) payload.put("limit", limit);
            if(offset != 0) payload.put("offset", offset);
            object.put("payload", payload);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public String getText() {
        return text;
    }

    private void setText(String text) {
        this.text = text;
    }

    private void setCategories(int[] categories) {
        this.categories = categories;
    }

    private void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    private void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private void setRadius_miles(double radius_miles) {
        this.radius_miles = radius_miles;
    }

    private void setConsumer_travels(boolean consumer_travels) {
        this.consumer_travels = consumer_travels;
    }

    private void setStarting_at(String starting_at) {
        this.starting_at = starting_at;
    }

    private void setEnding_at(String ending_at) {
        this.ending_at = ending_at;
    }

    private void setSort(String sort) {
        this.sort = sort;
    }

    private void setLimit(int limit) {
        this.limit = limit;
    }

    private void setOffset(int offset) {
        this.offset = offset;
    }

    //Builder allows chaining
    public static class Builder{

        private final JsonRequestObject object;

        public Builder() {
            object = new JsonRequestObject();
        }

        public Builder setText(String text){
            object.setText(text);
            return this;
        }

        public Builder setCategories(int[] categories) {
            object.setCategories(categories);
            return this;
        }

        public Builder setLatitude(double latitude) {
            object.setLatitude(latitude);
            return this;
        }

        public Builder setLongitude(double longitude) {
            object.setLongitude(longitude);
            return this;
        }

        public Builder setRadius_miles(double radius_miles) {
            object.setRadius_miles(radius_miles);
            return this;
        }

        public Builder setConsumer_travels(boolean consumer_travels) {
            object.setConsumer_travels(consumer_travels);
            return this;
        }

        public Builder setStarting_at(String starting_at) {
            object.setStarting_at(starting_at);
            return this;
        }

        public Builder setEnding_at(String ending_at) {
            object.setEnding_at(ending_at);
            return this;
        }

        public Builder setSort(String sort) {
            object.setSort(sort);
            return this;
        }

        public Builder setLimit(int limit) {
            object.setLimit(limit);
            return this;
        }

        public Builder setOffset(int offset) {
            object.setOffset(offset);
            return this;
        }

        public JSONObject create(){
            return object.getJsonObject();
        }
    }
}

package com.angrychimps.appname.utils;

import android.content.Context;
import android.util.Log;

import com.angrychimps.appname.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

/*
    This class builds JSONObjects for search and filtering in the StaggeredGridView
 */
public class JsonRequestObjectBuilder {

    private final Context context;
    private String text;
    private int[] categories;
    private double radius_miles = 0;
    private boolean consumer_travels = true;
    private String starting_at;
    private String ending_at;
    private String sort;
    private int limit = 0;
    private int offset = 0;


    public JsonRequestObjectBuilder(Context context) {
        this.context = context;
    }

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
            payload.put("lat", MainActivity.currentLocation.getLatitude());
            payload.put("lon", MainActivity.currentLocation.getLongitude());
            if(radius_miles != 0) payload.put("radius_miles", radius_miles);
            if(!consumer_travels) payload.put("consumer_travels", false);
            if(starting_at != null) payload.put("starting_at", starting_at);
            if(ending_at != null) payload.put("ending_at", ending_at);
            if(sort != null) payload.put("sort", sort);
            if(limit != 0) payload.put("limit", limit);
            if(offset != 0) payload.put("offset", offset);
            object.put("payload", payload);
            Log.i("object = ", payload.toString());
            Log.i("object2 = ", object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int[] getCategories() {
        return categories;
    }

    public void setCategories(int[] categories) {
        this.categories = categories;
    }

    public double getRadius_miles() {
        return radius_miles;
    }

    public void setRadius_miles(double radius_miles) {
        this.radius_miles = radius_miles;
    }

    public boolean isConsumer_travels() {
        return consumer_travels;
    }

    public void setConsumer_travels(boolean consumer_travels) {
        this.consumer_travels = consumer_travels;
    }

    public String getStarting_at() {
        return starting_at;
    }

    public void setStarting_at(String starting_at) {
        this.starting_at = starting_at;
    }

    public String getEnding_at() {
        return ending_at;
    }

    public void setEnding_at(String ending_at) {
        this.ending_at = ending_at;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}

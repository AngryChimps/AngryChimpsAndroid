package com.angrychimps.appname;


import android.os.AsyncTask;

import com.angrychimps.appname.customer.search.ExpandableListAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JSONParser {

    private String  photo;
    private String companyId;
    private String calendarId;
    private String start;
    private String end;
    private String method;
    private String categoryId;
    private String icon;
    private String description;
    private String filename;
    private String locationId;
    private String addressId;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String phoneMobile;
    private String latitude;
    private String longitude;
    private String providerAdId;
    private String providerAdImmutableId;
    private String title;
    private String rating;
    private String ratingCount;
    private String serviceId;
    private String priceDiscount;
    private String priceOriginal;
    private String minsForService;
    private String minsNotice;
    private String otherUserId;
    private String isMobile;

    private static String email;
    private static String password;
    private static String userId;
    private static String sessionId;
    private static String street1Home;
    private static String street2Home;
    private static String cityHome;
    private static String stateHome;
    private static String zipHome;
    private static String phoneMain;
    private static String phoneMainMobile;

    private final ArrayList<String> listDataHeader = new ArrayList<>();
    private final HashMap<String, List<String>> listDataChild = new HashMap<>();

    public JSONParser(){
        if(sessionId == null) {
            getSession();
        }
    }

    public void getAuth(){
        ServerCommunication comm = new ServerCommunication();
        comm.execute("authPost");
    }

    void getSession(){
        ServerCommunication comm = new ServerCommunication();
        comm.execute("sessionGet");
    }

    public void getSearch(){
        ServerCommunication comm = new ServerCommunication();
        comm.execute("searchPost");
    }

    public ExpandableListAdapter getCategories(){
        ServerCommunication comm = new ServerCommunication();
        comm.execute("categoriesGet");
        return new ExpandableListAdapter(null, listDataHeader, listDataChild);
    }


    private class ServerCommunication extends AsyncTask<String, Void, String>{

        protected String doInBackground(String... arg0) {
            String serverURL = "http://devvy.angrychimps.com/api/v1/";
            JSONObject objectReceived = null;
            JSONObject payloadReceived = null;
            JSONObject objectSent = new JSONObject();
            JSONObject payloadSent = new JSONObject();
            try {
                switch(arg0[0]){
                    case "authPost":
                        payloadSent.put("email", email);
                        payloadSent.put("password", password);
                        objectSent.put("payload", payloadSent);
                        serverURL = serverURL + "auth/login";
                        objectReceived = new JSONObject(getHttpPost(serverURL, objectSent));
                        payloadReceived = objectReceived.getJSONObject("payload");
                        JSONObject memberReceived = payloadReceived.getJSONObject("member");
                        String memberId = memberReceived.getString("id");
                        String memberName = memberReceived.getString("name");
                        String memberPhoto = memberReceived.getString("photo");
                        String memberEmail = memberReceived.getString("email");
                        companyId = memberReceived.getString("company_ids");

//                        Log.i("memberId = ", memberId);
//                        Log.i("memberName = ", memberName);
//                        Log.i("memberPhoto = ", memberPhoto);
//                        Log.i("memberEmail = ", memberEmail);
//                        Log.i("companyId = ", companyId);

                        break;
                    case "availabilityPost": //not yet implemented
                        serverURL = serverURL + "availability?userId=" + userId;
                        getHttpGet(serverURL);
                        break;
                    case "availabilityDelete": //not yet implemented
                        serverURL = serverURL + "availability?userId=" + userId;
                        getHttpGet(serverURL);
                        break;
                    case "categoriesGet":
                        serverURL = serverURL + "categories";
                        objectReceived = new JSONObject(getHttpGet(serverURL));
                        payloadReceived = objectReceived.getJSONObject("payload");
                        JSONArray categoriesReceived = payloadReceived.getJSONArray("categories");
                        String header = null;
                        ArrayList<String> childList = new ArrayList<>();
                        for (int i=0;i<categoriesReceived.length();i++){
                            JSONObject arrayObject = categoriesReceived.getJSONObject(i);
                            if (Integer.parseInt(arrayObject.getString("id")) % 100 == 0){
                                listDataHeader.add(arrayObject.getString("name"));
                                if (header != null) {
                                    listDataChild.put(header, childList);
                                    childList = new ArrayList<>();
                                }
                                header = arrayObject.getString("name");
                            }else {
                                childList.add(arrayObject.getString("name"));
                                if(i == categoriesReceived.length()-1 ){
                                    listDataChild.put(header, childList);
                                }
                            }
                        }

                        break;
                    case "companyGet":
                        serverURL = serverURL + "company/" + companyId + "?userId=" + userId;
                        getHttpGet(serverURL);
                        break;
                    case "companyPost":
                        serverURL = serverURL + "company?userId=" + userId;
                        getHttpGet(serverURL);
                        break;
                    case "companyPut":
                        serverURL = serverURL + "company/" + companyId + "?userId=" + userId;
                        getHttpGet(serverURL);
                        break;
                    case "companyDelete":
                        serverURL = serverURL + "company/" + companyId + "?userId=" + userId;
                        getHttpGet(serverURL);
                        break;
                    case "companyMediaPost":
                        serverURL = serverURL + "companyMedia/" + companyId + "/" + providerAdId + "?userId=" + userId;
                        getHttpGet(serverURL);
                        break;
                    case "locationGet":
                        serverURL = serverURL + "location/" + locationId + "?userId=" + userId;
                        getHttpGet(serverURL);
                        break;
                    case "locationPost":
                        serverURL = serverURL + "location?userId=" + userId;
                        getHttpGet(serverURL);
                        break;
                    case "locationPut":
                        serverURL = serverURL + "location/" + locationId + "?userId=" + userId;
                        getHttpGet(serverURL);
                        break;
                    case "locationDelete":
                        serverURL = serverURL + "location/" + locationId + "?userId=" + userId;
                        getHttpGet(serverURL);
                        break;
                    case "memberGet":
                        serverURL = serverURL + "member/" + otherUserId;
                        getHttpGet(serverURL);
                        break;
                    case "memberPut":
                        payloadSent.put("email", email);
                        payloadSent.put("password", password);
                        objectSent.put("payload", payloadSent);
                        serverURL = serverURL + "member/" + userId + "?userId=" + userId;
                        objectReceived = new JSONObject(getHttpPut(serverURL, objectSent));
                        payloadReceived = objectReceived.getJSONObject("payload");
                        String name = payloadReceived.getString("name");
                        email = payloadReceived.getString("email");

//                        Log.i("memberPut = ", "Name: " + name + " Email: " + email);
                        break;
                    case "memberDelete":
                        serverURL = serverURL + "member/" + userId + "?userId=" + userId;
                        getHttpGet(serverURL);
                        break;
                    case "providerAdImmutableGet":
                        serverURL = serverURL + "providerAdImmutable/" + providerAdImmutableId; //check uri if this doesn't work
                        getHttpGet(serverURL);
                        break;
                    case "searchPost":
                        serverURL = serverURL + "search"; //check uri if this doesn't work
                        objectReceived = new JSONObject(getHttpPost(serverURL, null));
//                        Log.i("objectReceived", ""+objectReceived.toString());
                        payloadReceived = objectReceived.getJSONObject("payload");
//                        Log.i("payloadReceived", ""+payloadReceived.toString());
                        JSONArray resultsReceived = payloadReceived.getJSONArray("results");
//                        Log.i("resultsReceived", ""+resultsReceived.toString());
                        for (int i=0;i<resultsReceived.length();i++) {
                            JSONObject resultReceived = resultsReceived.getJSONObject(i);
                            JSONObject addressReceived = resultReceived.getJSONObject("address");
//                            Log.i("addressReceived", "" + addressReceived.toString());
                            JSONArray availsReceived = resultReceived.getJSONArray("availabilities");
//                            Log.i("availsReceived", "" + availsReceived.toString());

                            Address address = new Address(
                                    addressReceived.getString("street1"),
                                    addressReceived.optString("street2"),
                                    addressReceived.getString("city"),
                                    addressReceived.getString("state"),
                                    addressReceived.getString("zip"),
                                    addressReceived.optString("lat"),
                                    addressReceived.optString("long"));
                            ArrayList<Availability> availList = new ArrayList<>();
                            for (int j=0;j<availsReceived.length();j++) {
                                JSONObject availReceived = availsReceived.getJSONObject(j);
                                Availability avail = new Availability(
                                        availReceived.getString("start"),
                                        availReceived.getString("end"));
                            }
                            SearchResult result = new SearchResult(
                                    resultReceived.getString("provider_ad_immutable_id"),
                                    resultReceived.getString("provider_ad_id"),
                                    resultReceived.getString("company_name"),
                                    resultReceived.getString("title"),
                                    resultReceived.optString("photo"),
                                    address, availList);
                        }
                        break;
                    case "serviceGet":
                        serverURL = serverURL + "service?userId=" + userId;
                        getHttpGet(serverURL);
                        break;
                    case "servicePost":
                        serverURL = serverURL + "service/" + serviceId + "?userId=" + userId;
                        getHttpGet(serverURL);
                        break;
                    case "servicePut":
                        serverURL = serverURL + "service/" + serviceId + "?userId=" + userId;
                        getHttpGet(serverURL);
                        break;
                    case "serviceDelete":
                        serverURL = serverURL + "service/" + serviceId + "?userId=" + userId;
                        getHttpGet(serverURL);
                        break;
                    case "sessionGet":
                        serverURL = serverURL + "session";
                        objectReceived = new JSONObject(getHttpGet(serverURL));
                        payloadReceived = objectReceived.getJSONObject("payload");
                        sessionId = payloadReceived.getString("session_id");

//                        Log.i("Session ID: ", "Session ID = " + sessionId);
                        break;
                    case "signupPost":
                        serverURL = serverURL + "signup/registerProviderAd";
                        getHttpGet(serverURL);
                        break;
                    case "signupCompanyPost":
                        serverURL = serverURL + "signup/registerProviderCompany?userId=" + userId;
                        getHttpGet(serverURL);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
//            Log.i("url = ", serverURL);

            return "result";
        }

        private String getHttpGet(String serverURL) {
            HttpGet request = new HttpGet(serverURL);
            request.setHeader("angrychimps-api-session-token", sessionId);
            return getJSON(request);
        }

        private String getHttpPost(String serverURL, JSONObject object) throws IOException {
            HttpPost request = new HttpPost(serverURL);
            if(object != null) {
                StringEntity se = new StringEntity(object.toString());
                se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                request.setEntity(se);
            }
            request.setHeader("angrychimps-api-session-token", sessionId);
            return getJSON(request);
        }

        private String getHttpPut(String serverURL, JSONObject object) throws IOException {
            HttpPost request = new HttpPost(serverURL);
            StringEntity se = new StringEntity(object.toString());
            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            request.setEntity(se);
            request.setHeader("angrychimps-api-session-token", sessionId);
            return getJSON(request);
        }

        private String getHttpDelete(String serverURL) {
            HttpDelete request = new HttpDelete(serverURL);
            request.setHeader("angrychimps-api-session-token", sessionId);
            return getJSON(request);
        }

        private String getJSON(HttpUriRequest request) {
            HttpClient client = new DefaultHttpClient();
            HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
            InputStream inputStream = null;
            String result = null;
            try {
                HttpResponse response = client.execute(request);
                HttpEntity entity = response.getEntity();
                inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) sb.append(line).append("\n");
                result = sb.toString();
//                Log.i("JSON response = ", result);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Close the InputStream when you're done with it
                try {
                    if (inputStream != null) inputStream.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            return result;
        }

        protected void onPostExecute(String result) {

        }
    }
}

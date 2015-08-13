package com.angrychimps.citizenvet.network;

import android.util.Log;

import com.angrychimps.citizenvet.events.SessionIdReceivedEvent;
import com.angrychimps.citizenvet.models.received.Animal;
import com.angrychimps.citizenvet.models.received.CompanyLocationDetail;
import com.angrychimps.citizenvet.models.received.MessageDetail;
import com.angrychimps.citizenvet.models.received.Messages;
import com.angrychimps.citizenvet.models.received.ReviewDetail;
import com.angrychimps.citizenvet.models.received.SearchResults;
import com.angrychimps.citizenvet.models.received.Service;
import com.angrychimps.citizenvet.models.received.Session;
import com.angrychimps.citizenvet.models.received.UserLoginResponse;
import com.angrychimps.citizenvet.models.send.Inquiry;
import com.angrychimps.citizenvet.models.send.MessageSend;
import com.angrychimps.citizenvet.models.send.MessageStatus;
import com.angrychimps.citizenvet.models.send.MessagesRequest;
import com.angrychimps.citizenvet.models.send.ReviewPost;
import com.angrychimps.citizenvet.models.send.SearchRequest;
import com.angrychimps.citizenvet.models.send.SessionRequest;
import com.angrychimps.citizenvet.models.send.UserLogin;
import com.angrychimps.citizenvet.models.shared.Address;
import com.angrychimps.citizenvet.models.shared.Company;
import com.angrychimps.citizenvet.models.shared.CompanyLocation;
import com.angrychimps.citizenvet.models.shared.Member;
import com.angrychimps.citizenvet.models.shared.StaffMember;
import com.angrychimps.citizenvet.models.shared.UserLoginReset;
import com.angrychimps.citizenvet.network.api.AnimalAPI;
import com.angrychimps.citizenvet.network.api.AuthAPI;
import com.angrychimps.citizenvet.network.api.CompanyAPI;
import com.angrychimps.citizenvet.network.api.InquiryAPI;
import com.angrychimps.citizenvet.network.api.LocationAPI;
import com.angrychimps.citizenvet.network.api.MemberAPI;
import com.angrychimps.citizenvet.network.api.MessageAPI;
import com.angrychimps.citizenvet.network.api.MessagesAPI;
import com.angrychimps.citizenvet.network.api.ReviewAPI;
import com.angrychimps.citizenvet.network.api.SearchAPI;
import com.angrychimps.citizenvet.network.api.ServiceAPI;
import com.angrychimps.citizenvet.network.api.SessionAPI;
import com.angrychimps.citizenvet.network.api.StaffAPI;
import com.angrychimps.citizenvet.network.json_utils.PayloadSerializer;
import com.angrychimps.citizenvet.utils.Device;
import com.angrychimps.citizenvet.utils.Otto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public enum RestClient {
    API;

    private final RestAdapter restAdapter;
    private String sessionId;
    //private String userId;

    RestClient() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Animal.class, new PayloadSerializer<Animal>())
                .registerTypeAdapter(CompanyLocationDetail.class, new PayloadSerializer<CompanyLocationDetail>())
                .registerTypeAdapter(MessageDetail.class, new PayloadSerializer<MessageDetail>())
                .registerTypeAdapter(Messages.class, new PayloadSerializer<Messages>())
                .registerTypeAdapter(ReviewDetail.class, new PayloadSerializer<ReviewDetail>())
                .registerTypeAdapter(SearchResults.class, new PayloadSerializer<SearchResults>())
                .registerTypeAdapter(Service.class, new PayloadSerializer<Service>())
                .registerTypeAdapter(Session.class, new PayloadSerializer<Session>("session"))
                .registerTypeAdapter(UserLoginResponse.class, new PayloadSerializer<UserLoginResponse>())
                .registerTypeAdapter(Inquiry.class, new PayloadSerializer<Inquiry>())
                .registerTypeAdapter(MessageSend.class, new PayloadSerializer<MessageSend>())
                .registerTypeAdapter(MessagesRequest.class, new PayloadSerializer<MessagesRequest>())
                .registerTypeAdapter(MessageStatus.class, new PayloadSerializer<MessageStatus>())
                .registerTypeAdapter(ReviewPost.class, new PayloadSerializer<ReviewPost>())
                .registerTypeAdapter(SearchRequest.class, new PayloadSerializer<SearchRequest>())
                .registerTypeAdapter(SessionRequest.class, new PayloadSerializer<SessionRequest>())
                .registerTypeAdapter(UserLogin.class, new PayloadSerializer<UserLogin>())
                .registerTypeAdapter(Address.class, new PayloadSerializer<Address>())
                .registerTypeAdapter(Company.class, new PayloadSerializer<Company>())
                .registerTypeAdapter(CompanyLocation.class, new PayloadSerializer<CompanyLocation>())
                .registerTypeAdapter(Member.class, new PayloadSerializer<Member>("member"))
                .registerTypeAdapter(StaffMember.class, new PayloadSerializer<StaffMember>())
                .registerTypeAdapter(UserLoginReset.class, new PayloadSerializer<UserLoginReset>())
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                Log.i(null, "sessionId = " + sessionId);
                if (sessionId != null) request.addHeader("angrychimps-api-session-token", sessionId);
                //if(userId != null) request.addQueryParam("userId", userId);
            }
        };

        restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("http://devvy3.angrychimps.com/api/v1")
                .setConverter(new GsonConverter(gson))
                .setRequestInterceptor(requestInterceptor)
                .build();

        restAdapter.create(SessionAPI.class).post(new SessionRequest("", new Device().getDescription()), new Callback<Session>() {
            @Override public void success(Session session, Response response) {
                RestClient.this.sessionId = session.getId();
                Otto.BUS.getBus().post(new SessionIdReceivedEvent());
            }

            @Override public void failure(RetrofitError error) {
                Log.i(null, "FAILURE TO GET SESSION ID");
            }
        });
    }

    public AnimalAPI animal() {
        return restAdapter.create(AnimalAPI.class);
    }

    public AuthAPI auth() {
        return restAdapter.create(AuthAPI.class);
    }

    public CompanyAPI company() {
        return restAdapter.create(CompanyAPI.class);
    }

    public InquiryAPI inquiry() {
        return restAdapter.create(InquiryAPI.class);
    }

    public LocationAPI location() {
        return restAdapter.create(LocationAPI.class);
    }

    public MemberAPI member() {
        return restAdapter.create(MemberAPI.class);
    }

    public MessageAPI message() {
        return restAdapter.create(MessageAPI.class);
    }

    public MessagesAPI messages() {
        return restAdapter.create(MessagesAPI.class);
    }

    public ReviewAPI review() {
        return restAdapter.create(ReviewAPI.class);
    }

    public SearchAPI search() {
        return restAdapter.create(SearchAPI.class);
    }

    public ServiceAPI service() {
        return restAdapter.create(ServiceAPI.class);
    }

    public StaffAPI staff() {
        return restAdapter.create(StaffAPI.class);
    }
}

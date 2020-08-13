package com.abheisenberg.messagereader.api;

import com.abheisenberg.messagereader.sms.SMS;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MessagesAPI {
    /*
    API to get list of the data of the messages of the user stored on the server.
     */
    @GET("/messages")
    Call<ArrayList<SMS>> getMessages();


    /*
    API to post the
     */
    String API_ROUTE = "/add/messages";
    @Headers({
            "content-type : application/json"
    })
    @POST(API_ROUTE)
    Call<SMS> addMessages(@Body SMS messageList);
}

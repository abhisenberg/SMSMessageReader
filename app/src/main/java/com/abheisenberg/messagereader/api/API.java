package com.abheisenberg.messagereader.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    private static API instance = null;

    private MessagesAPI messagesAPI;

    public MessagesAPI getMessagesAPI(){
        return messagesAPI;
    }

    private API(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://message-assignment.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        messagesAPI = retrofit.create(MessagesAPI.class);
    }

    public static API getInstance(){
        if(instance == null){
            instance = new API();
        }
        return instance;
    }
}

package com.example.user.myapplication.network;

import com.example.user.myapplication.network.model.PCStatusModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class API {

    public static final String BASE_URL = "http://172.20.10.4:3000";

    private static API instance = null;

    private Retrofit retrofit;
    private PCStatusService pcStatusService;

    public static API getInstance() {
        return instance != null ? instance : new API();
    }

    private API() {
        instance = this;

        //  initialize retrofit instance
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //  initialize API service
        pcStatusService = retrofit.create(PCStatusService.class);
    }

    public interface PCStatusService {

        @GET("/test")
         Call<List<PCStatusModel>>  getPCStatus();
    }

    public PCStatusService getPcStatusService() {
        return pcStatusService;
    }
}

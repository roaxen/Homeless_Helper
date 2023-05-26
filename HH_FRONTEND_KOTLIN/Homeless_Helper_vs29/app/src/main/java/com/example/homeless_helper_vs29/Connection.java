package com.example.homeless_helper_vs29;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connection {
    private static Connection instance;
    private Retrofit retrofit;
    private final String BASE_URL = "http://10.0.2.2:8080";
   // private final String BASE_URL = "172.18.4.190"; // conexion pablo


    private Connection() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }


}

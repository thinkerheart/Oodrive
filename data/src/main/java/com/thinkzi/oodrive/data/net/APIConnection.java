package com.thinkzi.oodrive.data.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * provide a Retrofit instance to get data via webservice
 * */
public class APIConnection {

    private static volatile Retrofit _retrofit = null;
    private static String API_BASE_URL = "http://localhost:8080";

    public static IRestAPI getInstance() {

        if (_retrofit == null) {

            synchronized (APIConnection.class) {

                _retrofit = new Retrofit.Builder()
                        .baseUrl(API_BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

            }

        }

        return _retrofit.create(IRestAPI.class);

    }

}

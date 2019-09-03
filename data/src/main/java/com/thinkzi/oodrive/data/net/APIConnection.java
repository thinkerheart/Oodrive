package com.thinkzi.oodrive.data.net;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * provide a Retrofit instance to get data via webservice
 * */
public class APIConnection {

    private static OkHttpClient _okHttpClient;
    private static volatile Retrofit _retrofit = null;
    private static String API_BASE_URL = "http://localhost:8080";

    public static IRestAPI getInstance() {

        if (_okHttpClient == null)
            initOkHttp();

        if (_retrofit == null) {

            synchronized (APIConnection.class) {

                _retrofit = new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .client(_okHttpClient)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

            }

        }

        return _retrofit.create(IRestAPI.class);

    }

    private static void initOkHttp() {
        //Create a new Interceptor.
        Interceptor headerAuthorizationInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Request request = chain.request();
                Headers headers = request.headers().newBuilder().add("Authorization", Credentials.basic("noel", "foobar")).build();
                request = request.newBuilder().headers(headers).build();
                return chain.proceed(request);
            }
        };

        _okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(headerAuthorizationInterceptor)
                .build();
    }

}

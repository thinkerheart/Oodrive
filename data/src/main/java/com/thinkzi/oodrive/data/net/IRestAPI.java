package com.thinkzi.oodrive.data.net;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

/**
 * provide a interface to use Retrofit to get data via webservice
 * */
public interface IRestAPI {

    /**
     * get photos from server
     * */
    @Streaming
    @GET("/photos")
    Single<ResponseBody> getPhotoStream();

}

package com.thinkzi.oodrive.data.net;

import com.thinkzi.oodrive.data.model.ItemDataModel;
import com.thinkzi.oodrive.data.model.UserDataModel;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

/**
 * provide a interface to use Retrofit to get data via webservice
 * */
public interface IRestAPI {

    /**
     * get content of a folder from server
     * */
    @Streaming
    @GET("/items/{id}")
    Single<ResponseBody> getRemoteFolderContent(@Path("id")String _id);

    /**
     * get current user and retrieve the root item
     * */
    @GET("/me")
    Single<UserDataModel> getRemoteCurrentUser();

    /**
     * create new folder
     * */
    @Headers("Content-Type: application/json")
    @FormUrlEncoded
    @POST("/items/{id}")
    Single<ItemDataModel> createNewFolder(@Path("id")String _id, @Field("name")String _name);

}

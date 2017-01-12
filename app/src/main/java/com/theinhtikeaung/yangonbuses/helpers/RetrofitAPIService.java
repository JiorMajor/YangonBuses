package com.theinhtikeaung.yangonbuses.helpers;

import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.EncodedPath;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.PartMap;
import retrofit.http.Path;
import retrofit.http.QueryMap;
import retrofit.mime.TypedFile;
import rx.Observable;

public interface RetrofitAPIService {

    @Headers("Content-Type: application/json")
    @GET("/{path}")
    void getResult(@Path("path") String path, @QueryMap Map<String, String> params);

    @Headers("Content-Type: application/json")
    @GET("/{path}")
    void getResult(@Path("path") String path, @QueryMap Map<String, String> params, Callback<String> cb);

    @Headers("Content-Type: application/json")
    @POST("/{path}")
    void postResult(@Path("path") String path, @QueryMap Map<String, String> params);

    @Headers("Content-Type: application/json")
    @POST("/{path}")
    void postResult(@Path("path") String path, @QueryMap Map<String, String> params, Callback<String> cb);



    @Headers("Content-Type: application/json")
    @GET("/{path}")
    void getSynchJSONResult(@EncodedPath("path") String path, @QueryMap Map<String, String> params, Callback<String> cb);

    @Headers("Content-Type: application/json")
    @GET("/{path}")
    String getSynchJSONResult(@EncodedPath("path") String path, @QueryMap Map<String, String> params, @Body TypedJsonString bean);


    @Headers("Content-Type: application/json")
    @POST("/{path}")
    void postSynchJSONResult(@EncodedPath("path") String path, @QueryMap Map<String, String> params, Callback<String> cb);

    @Headers("Content-Type: application/json")
    @POST("/{path}")
    String postSynchJSONResult(@EncodedPath("path") String path, @QueryMap Map<String, String> params, @Body TypedJsonString bean);



    @Headers("Content-Type: application/json")
    @GET("/{path}")
    void getJSONResult(@EncodedPath("path") String path, @QueryMap Map<String, String> params, Callback<String> cb);

    @Headers("Content-Type: application/json")
    @GET("/{path}")
    void getJSONResult(@EncodedPath("path") String path, @QueryMap Map<String, String> params, @Body TypedJsonString bean, Callback<String> cb);


    @Headers("Content-Type: application/json")
    @POST("/{path}")
    void postJSONResult(@EncodedPath("path") String path, @QueryMap Map<String, String> params, Callback<String> cb);

    @Headers("Content-Type: application/json")
    @POST("/{path}")
    void postJSONResult(@EncodedPath("path") String path, @QueryMap Map<String, String> params, @Body TypedJsonString bean, Callback<String> cb);

    @FormUrlEncoded
    @POST("/")
    void postResult(@FieldMap HashMap<String, String> body, Callback<String> cb);

    @FormUrlEncoded
    @POST("/")
    Observable<String> postResult(@FieldMap HashMap<String, String> body);

    @GET("/")
    void getResult(@QueryMap HashMap<String, String> body, Callback<String> cb);

    @GET("/")
    Observable<String> getResult(@QueryMap HashMap<String, String> body);

    @Multipart
    @POST("/")
    void postResult(@Part("photo") TypedFile file, @PartMap HashMap<String, String> body, Callback<String> cb);

    @Multipart
    @POST("/")
    void postResultImage(@Part("image") TypedFile file, @PartMap HashMap<String, String> body, Callback<String> cb);

    @Multipart
    @POST("/")
    Observable<String> postResult(@Part("photo") TypedFile file, @PartMap HashMap<String, String> body);

    @Multipart
    @POST("/")
    void postResult(@Part("photo") TypedFile file, @Part("user_profile_id") String user_profile_id, Callback<String> cb);

    @Multipart
    @POST("/")
    Observable<String> postResult(@Part("photo") TypedFile file, @Part("user_profile_id") String user_profile_id);

    @Multipart
    @POST("/")
    void postFileResult(@Part("receipt_reference_image") TypedFile file, @PartMap HashMap<String, String> body, Callback<String> cb);

    @Multipart
    @POST("/")
    Observable<String> postFileResult(@Part("receipt_reference_image") TypedFile file, @PartMap HashMap<String, String> body);

}
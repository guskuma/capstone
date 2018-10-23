package com.guskuma.notifique.data.restapi;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GCEAPIService {

    public static final String BASE_URL = "https://capstone-project-c681e.appspot.com/_ah/api/";

    @POST("messages/v1/sendToTopic")
    Call<ResponseBody> sendMessage(@Body RequestBody requestBody);

}
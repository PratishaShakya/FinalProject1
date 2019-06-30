package com.example.finalproject.API;

import com.example.finalproject.Model.EventResponse;
import com.example.finalproject.Model.LoginResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EventAPI {

@FormUrlEncoded
    @POST("evnt.php")
    Call<EventResponse> addevents(@Query("apicall") String apiCall,
                                  @Field("title") String title,
                                  @Field("description") String description,
                                  @Field("start_time") String start_time,
                                  @Field("venue") String venue
);
}

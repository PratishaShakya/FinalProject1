package com.example.finalproject.API;

import com.example.finalproject.Model.LoginResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginAPI {
    @FormUrlEncoded
    @POST("loginapi.php")
    Call<ResponseBody> registerUser(@Query("apicall") String apiCall,
                                    @Field("email") String email,
                                    @Field("username") String username,
                                    @Field("phone_no") String phone_no,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("loginapi.php")
    Call<LoginResponse> loginUser(@Query("apicall") String apiCall,
                                  @Field("email") String email,
                                  @Field("password") String password);
@FormUrlEncoded
    @POST("evnt.php")
    Call<ResponseBody> addevents(@Query("apicall") String apiCall,
                                 @Field("title") String title,
                                 @Field("description") String description,
                                 @Field("start_time") String start_time,
                                 @Field("venue") String venue
                                 );
}

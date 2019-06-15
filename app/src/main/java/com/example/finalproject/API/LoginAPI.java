package com.example.finalproject.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginAPI {
    @FormUrlEncoded
    @POST("api.php")
    Call<ResponseBody> registerUser(@Query("apicall") String apiCall,
                                    @Field("email") String email,
                                    @Field("username") String username,
                                    @Field("phone_no") String phone_no,
                                    @Field("password") String password);
}

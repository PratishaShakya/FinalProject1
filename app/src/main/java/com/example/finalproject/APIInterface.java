package com.example.finalproject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

@POST("loginapi.php")
 //  Call<ResponseBody> performRegistration(@Query("email") String email,String username, String phone_no ,@Query("user_password") String password);
Call<ResponseBody> performRegistration(@Body RequestBody body,@Query("apicall") String apicall);

   @Headers("Content-Type: application/x-www-form-urlencoded")
   @POST("loginapi.php")
   Call<ResponseBody> performUserLogin(@Body RequestBody body, @Query("apicall") String apicall);


}

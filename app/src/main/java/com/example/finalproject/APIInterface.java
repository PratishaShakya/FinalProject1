package com.example.finalproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

@GET("Users.php")
   Call<User> performRegistration(String name, @Query("email") String email, @Query("user_password") String password, String remember_token, int roll_id);
@GET("login.php")
Call<User> performUserLogin(@Query("email") String email, @Query("user_password") String password);


}

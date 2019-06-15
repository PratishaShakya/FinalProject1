package com.example.finalproject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "http://192.168.1.64/LaraEventTickets-QuickAdminPanel/api/api.php?apicall=";
    public static final String URL_REGISTER = BASE_URL + "signup";
    public static final String URL_LOGIN= BASE_URL + "login";
   // public static Retrofit retrofit = null;

  /*  public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
*/

}

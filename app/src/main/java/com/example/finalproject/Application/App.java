package com.example.finalproject.Application;

import android.app.Application;

import com.example.finalproject.Generic.Client;
import com.example.finalproject.Generic.Keys;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.finalproject.ApiClient.BASE_URL;

public class App extends Application {

    public static String BASE_URL = Client.HTTPS + Client.ADMIN_BASE_URL;
    public static TinyDB tinyDB;

    @Override
    public void onCreate() {
        super.onCreate();
        tinyDB = new TinyDB(this);
    }

    public static TinyDB db() {
        return tinyDB;
    }

    public static Retrofit adminRetrofit(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        if (App.db().getBoolean(Keys.USER_LOGGED_IN)) {
            OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES);
            okHttpBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("Authorization", "Bearer " + App.db().getString(Keys.USER_TOKEN))
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                }
            });
            builder.client(okHttpBuilder.build());
        }
        Retrofit retrofit = builder.build();
        return retrofit;
    }
}

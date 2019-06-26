package com.example.finalproject.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.API.LoginAPI;
import com.example.finalproject.Application.App;
import com.example.finalproject.Generic.Keys;
import com.example.finalproject.Model.LoginResponse;
import com.example.finalproject.Model.UserData;
import com.example.finalproject.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrganisorSignIn extends AppCompatActivity {
    public EditText email, password;
    public Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisor_sign_in);

        email = (EditText) findViewById(R.id.orgEmail);
        password = (EditText) findViewById(R.id.orgPassword);
        login = (Button) findViewById(R.id.orgLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userLogin();
            }
        });

    }

    private void userLogin() {

        final String email1 = email.getText().toString();
        final String password1 = password.getText().toString();


        //validating inputs
        if (TextUtils.isEmpty(email1)) {
            email.setError("Please enter your email");
            email.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password1)) {
            password.setError("Please enter your password");
            password.requestFocus();
            return;
        } else {
            LoginAPI loginAPI = App.adminRetrofit().create(LoginAPI.class);
            loginAPI.loginUser("login", email1, password1).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        if (!response.body().error) {
                            UserData userData = response.body().normaluser;
                            App.db().putString(Keys.USER_NAME, userData.username);
                            App.db().putString(Keys.USER_EMAIL, userData.email);
                            App.db().putBoolean(Keys.USER_LOGGED_IN, true);
                            Toast.makeText(OrganisorSignIn.this, "Organizer logged successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), AddEventActivity.class));
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    //  Toast.makeText(SignInActivity.this, "user logged failed", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}
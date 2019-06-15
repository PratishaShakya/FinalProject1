package com.example.finalproject.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.finalproject.API.LoginAPI;
import com.example.finalproject.ApiClient;
import com.example.finalproject.Application.App;
import com.example.finalproject.PreConfig;
import com.example.finalproject.R;
import com.example.finalproject.RequestHandler;
import com.example.finalproject.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
*/
public class SignUpActivity extends AppCompatActivity {
    //private FirebaseAuth firebaseAuth;
    public EditText useremail, password, username, confirmpwd;
    private EditText phonenumber;
    private Button createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
       // setupUIViews();
//if the user is already logged in we will directly start the profile activity
       if (PreConfig.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return;
        }
        useremail = (EditText) findViewById(R.id.useremail);
        username = (EditText) findViewById(R.id.username);
        phonenumber = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.editText8);
        confirmpwd = (EditText) findViewById(R.id.editText9);
        createAccount= findViewById(R.id.button3);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // if (validate()) {
                    registerUser();
                 /*   String emai =useremail.getText().toString();
                    String pass = password.getText().toString();

                    databaseconnection bg = new databaseconnection((View.OnClickListener) this);
                    bg.execute(emai,pass);
               */ }


        });
        createAccount.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
//if user pressed on login
                                                 //we will open the login screen
                                                 finish();
                                                 startActivity(new Intent(SignUpActivity.this, SignInActivity.class));


                                             }
                                         });
    // firebaseAuth=FirebaseAuth.getInstance();
       /* createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (validate()) {
              /*  String user_email =useremail.getText().toString().trim();
                String user_password = password.getText().toString().trim();
             //   firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                //    @Override
                //    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this,"Account Created Successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
                        }
                        else {
                            Toast.makeText(SignUpActivity.this,"Account Creation Failed",Toast.LENGTH_SHORT).show();
                        }
                        if (task.getException() instanceof FirebaseAuthUserCollisionException)
                        {
                            Toast.makeText(SignUpActivity.this,
                                    "User with this email already exist.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        }

    });
*/
    ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("SignUp");
}


  /*  private boolean checkAccountEmailExistInFirebase(String useremail){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final boolean[] b = new boolean[1];
        firebaseAuth.fetchSignInMethodsForEmail(useremail).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                b[0]=!task.getResult().getSignInMethods().isEmpty();
            }
        });
        return b[0];
    }
    */

  /*  private void setupUIViews(){
        useremail = (EditText) findViewById(R.id.useremail);
        username = (EditText) findViewById(R.id.username);
        phonenumber = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.editText8);
        confirmpwd = (EditText) findViewById(R.id.editText9);
        createAccount= findViewById(R.id.button3);
    }
*/


   /* private Boolean validate(){
        Boolean res=false;
        String email = useremail.getText().toString();
        String name = username.getText().toString();
        String phone = phonenumber.getText().toString();
        String pwd = password.getText().toString();
        String cpwd = confirmpwd.getText().toString();



        String valid = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if(email.matches(valid)){
            res=true;
        }

        if (email.isEmpty() || name.isEmpty() || phone.isEmpty() && pwd.isEmpty() || cpwd.isEmpty()) {
            Toast.makeText(SignUpActivity.this, " fields should not be blank ", Toast.LENGTH_SHORT).show();
        } else {
            res=true;
        }
        return res;
    }
*/

    private void registerUser() {
        //first we will do the validations
        final String email = useremail.getText().toString();
        final String name = username.getText().toString();
        final String phone = phonenumber.getText().toString();
        final String pwd = password.getText().toString();
        final String cpwd = confirmpwd.getText().toString();
        if (TextUtils.isEmpty(name)) {
           username.setError("Please enter username");
            username.requestFocus();
            return;
        }

        else if (TextUtils.isEmpty(email)) {
        useremail.setError("Please enter your email");
          useremail.requestFocus();
            return;
        }

        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
           useremail.setError("Enter a valid email");
           useremail.requestFocus();
            return;
        }

        else if (TextUtils.isEmpty(pwd)) {
            password.setError("Enter a password");
          password.requestFocus();
            return;
        }
        else if (TextUtils.isEmpty(cpwd)) {
            password.setError("Enter a password");
            password.requestFocus();
            return;
        } else{
            LoginAPI loginAPI = App.adminRetrofit().create(LoginAPI.class);
            loginAPI.registerUser("signup",email,name,phone,pwd).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful() && response.body() != null){
                        Log.d("lol", "onResponse: "+response.body());
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }

        //if it passes all the validations

//        class RegisterUser extends AsyncTask<Void, Void, String> {
//
//            private ProgressBar progressBar;
//
//            @Override
//            protected String doInBackground(Void... voids) {
//                //creating request handler object
//                RequestHandler requestHandler = new RequestHandler();
//
//                //creating request parameters
//                HashMap<String, String> params = new HashMap<>();
//
//
//                params.put("email", email);
//                params.put("username", name);
//                params.put("phone", phone);
//                params.put("password", pwd);
//                params.put("ConfirmPassword",cpwd);
//
//
//
//                //returing the response
//
//                return requestHandler.sendPostRequest(ApiClient.URL_REGISTER, params);
//            }
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                //displaying the progress bar while user registers on the server
//                progressBar = (ProgressBar) findViewById(R.id.progressBar);
//                progressBar.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                //hiding the progressbar after completion
//                progressBar.setVisibility(View.GONE);
//
//                try {
//                    //converting response to json object
//                    JSONObject obj = new JSONObject(s);
//
//                    //if no error in response
//                    if (!obj.getBoolean("error")) {
//                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//
//                        //getting the user from the response
//                        JSONObject userJson = obj.getJSONObject("normaluser");
//
//                        //creating a new user object
//                        User user = new User(
//                        //       userJson.getInt("id"),
//
//                                userJson.getString("email"),
//                                userJson.getString("username"),
//                              //  userJson.getString("password"),
//                                userJson.getString("phone_no")
//                             //  userJson.getString("confirmpassword")
//                        );
//
//                        //storing the user in shared preferences
//                        PreConfig.getInstance(getApplicationContext()).userLogin(user);
//
//                        //starting the profile activity
//                        finish();
//                        Toast.makeText(getApplicationContext(), "User added successfully", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(getApplicationContext(), SignInActivity.class));
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        //executing the async task
//        RegisterUser ru = new RegisterUser();
//        ru.execute();
    }

}



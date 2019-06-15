package com.example.finalproject.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.ApiClient;
import com.example.finalproject.PreConfig;
import com.example.finalproject.R;
import com.example.finalproject.RequestHandler;
import com.example.finalproject.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/*import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
*/
public class SignInActivity extends AppCompatActivity {
    public EditText email, password;
    public Button login;
    public TextView create_Account;
    private TextView forgotPwd;
    onLoginFormActivityListener loginFormActivityListener;
   public interface onLoginFormActivityListener{
       public void performRegister();
       public void performLogin(String email);

   }

    // private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        //  firebaseAuth=FirebaseAuth.getInstance();
        // try {


        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPassword);
        login = (Button) findViewById(R.id.btnLogin);
        create_Account = (TextView) findViewById(R.id.lblCreate);
        forgotPwd = (TextView) findViewById(R.id.textView2);
//FirebaseUser user=firebaseAuth.getCurrentUser();
//firebaseAuth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String emailText = email.getText().toString();
                //  String passwordText = password.getText().toString();
                   /* if (emailText.equals(("jenisha")) && passwordText.equals("1234567")) {
                        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignInActivity.this, "Username Or Password Incorrect", Toast.LENGTH_SHORT).show();
                    }
*/
                //   validate(email.getText().toString(), password.getText().toString());
//                if( emailText.equals("" ) || passwordText.equals(""))
//                {
//                    Toast.makeText(MainActivity.this, " fields should not be blank ", Toast.LENGTH_SHORT).show();
//                }
//                if(passwordText.length() < 6 || passwordText.length()>12)
//                {
//                    Toast.makeText(MainActivity.this, "password must be 6 to 12 characters" , Toast.LENGTH_SHORT).show();
//                }
                //    String emai =   email.getText().toString();
                //  String pass = password.getText().toString();

                //  databaseconnection bg = new databaseconnection((View.OnClickListener) this);
                //   bg.execute(emailText,passwordText);
                userLogin();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


        forgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, MainActivity.class));
            }
        });

        create_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
        private void userLogin(){
            //first getting the values
           // final String email = email.getText().toString();
            final String email1 = email.getText().toString();
            final String password1 = password.getText().toString();

            //validating inputs
            if (TextUtils.isEmpty(email1)){
                email.setError("Please enter your email");
                email.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(password1)) {
                password.setError("Please enter your password");
              password.requestFocus();
                return;
            }

            //if everything is fine

            class UserLogin extends AsyncTask<Void, Void, String> {

                ProgressBar progressBar;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    progressBar = (ProgressBar) findViewById(R.id.progressBar);
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    progressBar.setVisibility(View.GONE);


                    try {
                        //converting response to json object
                        JSONObject obj = new JSONObject(s);

                        //if no error in response
                        if (!obj.getBoolean("error")) {
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                            //getting the user from the response
                            JSONObject userJson = obj.getJSONObject("user");

                            //creating a new user object
                            User user = new User(
                                 //   userJson.getInt("id"),
                                    userJson.getString("email"),
                                    userJson.getString("username"),

                                    userJson.getString("phone_no")
                                  //  userJson.getString("pssword"),
                               //     userJson.getString("confirmpassword")
                            );

                            //storing the user in shared preferences
                         PreConfig.getInstance(getApplicationContext()).userLogin(user);

                            //starting the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                protected String doInBackground(Void... voids) {
                    //creating request handler object
                    RequestHandler requestHandler = new RequestHandler();

                    //creating request parameters
                    HashMap<String, String> params = new HashMap<>();
                    params.put("email", email1);
                    params.put("password", password1);

                    //returing the response
                    return requestHandler.sendPostRequest(ApiClient.URL_LOGIN, params);
                }
            }

            UserLogin ul = new UserLogin();
            ul.execute();
        }
    }



        /*catch (Exception e){
            System.out.println("error");
        }
        */
    /*    private void validate(String userName, String userPassword) {


            firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        //Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        checkEmailVerification();
                    } else {
                        Toast.makeText(SignInActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    private void checkEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        startActivity(new Intent(SignInActivity.this, MainActivity.class));
    }

    }

*/

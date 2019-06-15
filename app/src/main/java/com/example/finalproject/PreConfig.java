package com.example.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.finalproject.Activity.SignInActivity;

public class PreConfig {
  /*  private SharedPreferences sharedPreferences;
    private Context context;
    public  PreConfig(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences(context.getString(R.string.pref_file),Context.MODE_PRIVATE);
    }
    public void writeLoginStatus(boolean status){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status),status);
        editor.commit();
    }
    public boolean readLoginStatus(){
        return sharedPreferences.getBoolean(context.getString(R.string.pref_name),false);
    }

public void writeName(String name)
{
    SharedPreferences.Editor editor=sharedPreferences.edit();
    editor.putString(context.getString(R.string.pref_name),name);
    editor.commit();
}
public  String readName()
{
    return  sharedPreferences.getString(context.getString(R.string.pref_name),"user");
}

    public void displayToast(String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
    */
  //the constants
  private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_PHONE_NO= "keyphone_no";
   // private static final String KEY_ID = "keyid";
 //   private static final String KEY_PASSWORD = "keypassword";
 //   private static final String KEY_CONFIRMPASSWORD = "confirmpassword";
    private static PreConfig mInstance;
    private static Context mCtx;

    private PreConfig(Context context) {
        mCtx = context;
    }

    public static synchronized PreConfig getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new PreConfig (context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
     //  editor.putInt(KEY_ID, user.getid());

        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_PHONE_NO, user.getPhone_no());
     //   editor.putString(KEY_PASSWORD, user.getPassword());
      //  editor.putString(KEY_CONFIRMPASSWORD, user.getConfirmpassword());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
          //     sharedPreferences.getInt(KEY_ID, -1),

                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_PHONE_NO, null)
           //     sharedPreferences.getString(KEY_PASSWORD, null),
             //   sharedPreferences.getString(KEY_CONFIRMPASSWORD, null)


                );

    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, SignInActivity.class));
    }
}


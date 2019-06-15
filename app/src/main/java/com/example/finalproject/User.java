package com.example.finalproject;

import com.google.gson.annotations.SerializedName;

public class User {
    /*@SerializedName("response")
    private String response;
    @SerializedName("name")
    private  String Name;

    public String getResponse() {
        return response;
    }

    public String getName() {
        return Name;
    }
    */
  // private int id;
    private String username, phone_no,email;

    public User(String username, String email, String phone_no) {
          //  this.id=id;
        this.username = username;
        this.email = email;
        this.phone_no=phone_no;
      //  this.password=password;
     //   this.confirmpassword=confirmpassword;
    }

 /*  public int getid() {
      return id;
   }
   */
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_no() {
        return phone_no;
    }
 //   public String getPassword(){
  //      return  password;
  //  }
  // public  String getConfirmpassword(){
  //      return confirmpassword;
  //  }


}

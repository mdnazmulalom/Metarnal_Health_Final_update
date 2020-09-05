package com.nazmul.metarnalhealth.doctors.remote;

import com.nazmul.metarnalhealth.Constant;
import com.nazmul.metarnalhealth.doctors.model.Doctors;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("retrofit/POST/readcontacts.php")
    Call<List<Doctors>> getContacts();

    @FormUrlEncoded
    @POST("retrofit/POST/addcontact.php")
    public Call<Doctors> insertUser(
            @Field("name") String name,
            @Field("email") String email);

    //for signup
    @FormUrlEncoded
    @POST("retrofit/POST/signup.php")
    Call<Doctors> signUp(
            @Field(Constant.KEY_NAME) String name,
            @Field(Constant.KEY_CELL) String cell,
            @Field(Constant.KEY_PASSWORD) String password);

    @FormUrlEncoded
    @POST("retrofit/POST/go.php")
    Call<Doctors> signup(
        @Field(Constant.KEY_NAME) String name,
        @Field(Constant.KEY_CELL) String cell,
        @Field(Constant.KEY_PASSWORD) String password
    );

   // for login
    @FormUrlEncoded
    @POST("retrofit/POST/login.php")
    Call<Doctors> login(
            @Field(Constant.KEY_CELL) String cell,
            @Field(Constant.KEY_PASSWORD) String password);


    @FormUrlEncoded
    @POST("retrofit/POST/editcontact.php")
    public Call<Doctors> editUser(
            @Field("id") String id,
            @Field("name") String name,
            @Field("email") String email);


    @FormUrlEncoded
    @POST("retrofit/POST/deletecontact.php")
    Call<Doctors> deleteUser(
            @Field("id") int id
    );








    @POST("MetarnalHealth/android/readdoctors.php")
    Call<List<Doctors>> getDoctors(
            @Query("item_type") String item_type,
            @Query("key") String keyword,
            @Query("cell") String cell
    );




    //for live data search
    @GET("MetarnalHealth/android/getdoctors.php")
    Call<List<Doctors>> getDoctors(
            @Query("item_type") String item_type,
            @Query("key") String keyword
    );
}

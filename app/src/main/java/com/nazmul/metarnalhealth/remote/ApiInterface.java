package com.nazmul.metarnalhealth.remote;

import com.nazmul.metarnalhealth.Constant;
import com.nazmul.metarnalhealth.doctors.model.Doctor_Appoinment_List;
import com.nazmul.metarnalhealth.doctors.model.Doctors;
import com.nazmul.metarnalhealth.mothers.model.Hospital;
import com.nazmul.metarnalhealth.mothers.model.Mother;

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







//
//    @POST("MetarnalHealth/android/readdoctors.php")
//    Call<List<Doctors>> getDoctors(String type, String key);
//

    @POST("MetarnalHealth/android/doctorsAppoinment.php")
    Call<Doctors> doctorsAppoinment(
            @Field("user_cell") String user_cell,
            @Field("name") String name,
            @Field("designation") String designation,
            @Field("doctors_cell") String doctors_cell,
            @Field("problem_descripion") String problem_descripion);


    //for live data search
    @GET("MetarnalHealth/android/getdoctors.php")
    Call<List<Doctors>> getDoctors(
            @Query("item_type") String item_type,
            @Query("key") String keyword
    );
    @GET("MetarnalHealth/android/getdoctors_appoinment.php")
    Call<List<Doctor_Appoinment_List>> getDoctorAppoinment(
            @Query("userCell") String userCell,
            @Query("key") String keyword
    );

    @GET("MetarnalHealth/android/getmother_appoinment.php")
    Call<List<Mother>> getMotherAppoinment(
            @Query("UserCell") String UserCell,
            @Query("key") String keyword
    );

    @GET("MetarnalHealth/android/gethospitalinfo.php")
    Call<List<Hospital>> gethospitalinfo(
            @Query("item_type") String item_type,
            @Query("key") String keyword
    );
}

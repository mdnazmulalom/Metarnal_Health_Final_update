package com.nazmul.metarnalhealth.doctors.model;

import com.google.gson.annotations.SerializedName;

public class Doctors {

//    @SerializedName("id")
//    private String id;
    @SerializedName("doctor_id")
    private String doctor_id;
    @SerializedName("name")
    private String name;
    @SerializedName("designation")
    private String designation;
    @SerializedName("speciallist")
    private String speciallist;
    @SerializedName("doctor_fee")
    private String doctor_fee;
    @SerializedName("cell")
    private String cell;
    @SerializedName("location")
    private String location;
    @SerializedName("gender")
    private String gender;

    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;


    public String getDoctorID() {
        return doctor_id;
    }
    public String getName() {
        return name;
    }
    public String getDesignation() {
        return designation;
    }
    public String getSpeciallist() {
        return speciallist;
    }
    public String getDoctor_fee() {
        return doctor_fee;
    }
    public String getCell() {
        return cell;
    }
    public String getLocation() {
        return location;
    }
    public String getGender() {
        return gender;
    }
    public String getValue() {
        return value;
    }
    public String getMassage() {
        return massage;
    }


}

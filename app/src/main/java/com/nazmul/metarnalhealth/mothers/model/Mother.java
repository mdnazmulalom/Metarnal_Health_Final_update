package com.nazmul.metarnalhealth.mothers.model;

import com.google.gson.annotations.SerializedName;

public class Mother {
//    @SerializedName("appoinment_id")
//    private String appoinment_id;
//
//    @SerializedName("doctor_name")
//    private String doctor_name;
//
//    @SerializedName("doctor_number")
//    private String 	doctor_number;
//
//    @SerializedName("mother_number")
//    private String 	mother_number;
//
//    @SerializedName("problem_descripion")
//    private String 	problem_descripion;
//
//    @SerializedName("appoinment_date")
//    private String 	appoinment_date;
//
//    @SerializedName("date")
//    private String 	date;
//
//
//    @SerializedName("time")
//    private String 	time;
//
//    @SerializedName("status")
//    private String 	status;
//
//
//
//    public String getMotherID() {
//        return appoinment_id;
//    }
//
//    public String getDoctor_name() {
//        return doctor_name;
//    }
//
//    public String getDoctor_number() {
//        return doctor_number;
//    }
//
//    public String getProblem_descripion() {
//        return problem_descripion;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public String getStatus() {
//        return status;
//    }

    @SerializedName("date")
    private String 	date;

    @SerializedName("appoinment_id")
    private String appoinment_id;
    @SerializedName("doctor_name")
    private String doctor_name;
    @SerializedName("doctor_number")
    private String doctor_number;
    @SerializedName("mother_number")
    private String 	mother_number;
    @SerializedName("problem_descripion")
    private String problem_descripion;
    @SerializedName("appoinment_date")
    private String appoinment_date;
    @SerializedName("status")
    private String status;

    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;


    public String getAppoinment_id() {
        return appoinment_id;
    }
    public String getDoctor_name() {
        return doctor_name;
    }
    public String getDoctor_number() {
        return doctor_number;
    }
    public String getMother_number() {
        return mother_number;
    }
    public String getProblem_descripion() {
        return problem_descripion;
    }
    public String getAppoinment_date() {
        return appoinment_date;
    }
    public String getStatus() {
        return status;
    }
    public String getValue() {
        return value;
    }
    public String getMassage() {
        return massage;
    }
    public String getDate() {
        return date;
    }

}


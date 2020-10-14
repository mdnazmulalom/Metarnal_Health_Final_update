package com.nazmul.metarnalhealth.doctors.model;

import com.google.gson.annotations.SerializedName;

public class Doctor_Appoinment_List {
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

    @SerializedName("chamber_type")
    private String chamber_type;

    @SerializedName("zoom_or_chamber_address")
    private String zoom_or_chamber_address;

    @SerializedName("bkash_number")
    private String bkash_number;

    @SerializedName("bkash_trans_id")
    private String bkash_trans_id;

    @SerializedName("bkash_amount")
    private String bkash_amount;

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
    public String getChamber_type() {
        return chamber_type;
    }

    public String getzoom_or_chamber_address() {
        return zoom_or_chamber_address;
    }
    public String getbkash_number() {
        return bkash_number;
    }
    public String getbkash_trans_id() {
        return bkash_trans_id;
    }
    public String getbkash_amount() {
        return bkash_amount;
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


}

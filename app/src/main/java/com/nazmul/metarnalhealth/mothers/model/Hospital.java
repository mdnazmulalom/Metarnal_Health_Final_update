package com.nazmul.metarnalhealth.mothers.model;

import com.google.gson.annotations.SerializedName;

import java.util.Locale;

public class Hospital {

    @SerializedName("id")
    private String id;

    @SerializedName("hospitalname")
    private String 	hospitalname;

    @SerializedName("category")
    private String 	category;

    @SerializedName("address")
    private String 	address;

    @SerializedName("website")
    private String 	website;

    @SerializedName("hospitalnumber")
    private String 	hospitalnumber;

    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;


    public String getID() {
        return id;
    }

    public String getHospitalname() {
        return hospitalname;
    }
    public String getCategory() {
        return category;
    }
    public String getAddress() {
        return address;
    }
    public String getWebsite() {
        return website;
    }
    public String gethospitalnumber() {
        return hospitalnumber;
    }
    public String getValue() {
        return value;
    }
    public String getMassage() {
        return massage;
    }

}

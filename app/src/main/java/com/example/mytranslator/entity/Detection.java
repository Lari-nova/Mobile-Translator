package com.example.mytranslator.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detection {

    @SerializedName("lang")
    @Expose
    private String lang;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}

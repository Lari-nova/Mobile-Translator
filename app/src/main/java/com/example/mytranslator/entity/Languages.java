package com.example.mytranslator.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

public class Languages {

    @SerializedName("dirs")
    @Expose
    private List<String> dirs = null;

    @SerializedName("langs")
    @Expose
    private HashMap<String, String> langs;

    public List<String> getDirs() {
        return dirs;
    }

    public void setDirs(List<String> dirs) {
        this.dirs = dirs;
    }

    public HashMap<String, String> getLangs() {
        return langs;
    }

    public void setLangs(HashMap<String, String> langs) {
        this.langs = langs;
    }
}

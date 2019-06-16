package com.example.mytranslator.ui;

import android.os.Bundle;

import java.util.ArrayList;

public interface ViewLanguageInterface {
    void showLangs(ArrayList<String> list);
    void replaceFragment();
    Bundle getFragmentArguments();
}

package com.example.mytranslator;

import android.app.Application;

import com.example.mytranslator.manager.ApplicationStateManager;
import com.example.mytranslator.manager.StateManager;
import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker;

public class MainApplication extends Application {

    private StateManager stateManager;


    @Override
    public void onCreate() {
        super.onCreate();
        stateManager = ApplicationStateManager.create();
        InternetAvailabilityChecker.init(this);
    }
}

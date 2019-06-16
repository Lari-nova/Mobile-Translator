package com.example.mytranslator.ui;

import com.example.mytranslator.manager.ApplicationStateManager;
import com.example.mytranslator.manager.StateManager;

public class ComponentPresenter implements Component {

    private ViewComponentInterface componentInterface;
    private StateManager stateManager;
    private String originalKey;
    private String originalValue;
    private String translationKey;
    private String translationValue;

    public ComponentPresenter() {
        stateManager = ApplicationStateManager.create();
        //initializeState();
    }

    @Override
    public void addComponentInterface(ViewComponentInterface componentInterface) {
        this.componentInterface = componentInterface;
    }

    @Override
    public void onStartView() {
        initializeState();
    }

    private void initializeState() {
        originalKey = stateManager.receiveOriginalKey();
        originalValue = stateManager.receiveOriginalValue();
        translationKey = stateManager.receiveTranslationKey();
        translationValue = stateManager.receiveTranslationValue();
        componentInterface.addTextOnButton(originalValue, translationValue);
    }
}

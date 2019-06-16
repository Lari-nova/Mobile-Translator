package com.example.mytranslator.manager;

public class ApplicationStateManager implements StateManager {

    private static StateManager stateManager;
    private String originalKey = "en";
    private String originalValue = "Английский";
    private String translationKey = "ru";
    private String translationValue = "Русский";

    private ApplicationStateManager() {

    }

    public static StateManager create() {
        if (stateManager == null) {
            stateManager = new ApplicationStateManager();
        }
        return stateManager;
    }

    @Override
    public void addOriginalKey(String key) {
        this.originalKey = key;
    }

    @Override
    public void addOriginalValue(String value) {
        this.originalValue = value;
    }

    @Override
    public String receiveOriginalKey() {
        return originalKey;
    }

    @Override
    public String receiveOriginalValue() {
        return originalValue;
    }

    @Override
    public void addTranslationKey(String key) {
        this.translationKey = key;
    }

    @Override
    public void addTranslationValue(String value) {
        this.translationValue = value;
    }

    @Override
    public String receiveTranslationKey() {
        return translationKey;
    }

    @Override
    public String receiveTranslationValue() {
        return translationValue;
    }
}

package com.example.mytranslator.manager;

public interface StateManager {
    void addOriginalKey(String key);
    void addOriginalValue(String value);
    String receiveOriginalKey();
    String receiveOriginalValue();

    void addTranslationKey(String key);
    void addTranslationValue(String value);
    String receiveTranslationKey();
    String receiveTranslationValue();

}

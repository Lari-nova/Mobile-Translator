package com.example.mytranslator.network;

import com.example.mytranslator.entity.Languages;
import com.example.mytranslator.entity.Translation;

import io.reactivex.Observable;

public interface InterfaceNetworkManager {
    Observable<Translation> subscribeTranslation(String apiKey, String text, String lang, String format, int options);
    Observable<Languages> subscribeLangs(String apiKey, String uiLangCode);
}
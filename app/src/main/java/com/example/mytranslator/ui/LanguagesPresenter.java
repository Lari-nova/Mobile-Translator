package com.example.mytranslator.ui;

import android.os.Bundle;

import com.example.mytranslator.fragments.LanguagesFragment;
import com.example.mytranslator.manager.ApplicationStateManager;
import com.example.mytranslator.manager.StateManager;
import com.example.mytranslator.resource.Constants;
import com.example.mytranslator.network.InterfaceNetworkManager;
import com.example.mytranslator.network.NetworkManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LanguagesPresenter implements Languages {

    private ViewLanguageInterface languageInterface;
    private InterfaceNetworkManager manager;
    private HashMap<String, String> keyLanguageMap;
    private StateManager stateManager;
    private ArrayList<String> allLenguage = new ArrayList<>();
    public LanguagesPresenter() {
        manager = new NetworkManager(Constants.BASE_URL);
        stateManager = ApplicationStateManager.create();
    }

    @Override
    public void addLanguageInterface(ViewLanguageInterface languageInterface) {
        this.languageInterface = languageInterface;
    }

    @Override
    public void loadLang() {
        subscribeLangs(Constants.API_KEY, Constants.UI_LANGUAGE_CODE);
    }

    @Override
    public void addSelectLanguage(String language) {

        if (languageInterface.getFragmentArguments() != null) {
            String bundleKey = languageInterface.getFragmentArguments().getString(LanguagesFragment.BUNDLE_KEY);
            if (bundleKey.equals(LanguagesFragment.ORIGINAL_VALUE)) {
                stateManager.addOriginalKey(selectKey(language));
                stateManager.addOriginalValue(language);
            } else if (bundleKey.equals(LanguagesFragment.TRANSLATION_VALUE)){
                stateManager.addTranslationKey(selectKey(language));
                stateManager.addTranslationValue(language);
            }
        }
        languageInterface.replaceFragment();
    }

    @Override
    public void userSearch(String word) {
        ArrayList<String> listClone = new ArrayList<>();
        for (String string : allLenguage) {
            if(string.matches("(?i)("+ word + ").*")){
                listClone.add(string);
            }
        }
        languageInterface.showLangs(listClone);
    }

    private String selectKey(String language) {
        Collection<String> collection = keyLanguageMap.keySet();
        for (String key : collection) {
            if (key != null && keyLanguageMap.get(key).equals(language)) {
                return key;
            }
        }
        return null;
    }

    private void subscribeLangs(String apiKey, String uiLangCode) {
        manager.subscribeLangs(apiKey, uiLangCode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(e -> {
                    keyLanguageMap = e.getLangs();
                    ArrayList<String> result = new ArrayList<>(keyLanguageMap.values());
                    Collections.sort(result, String::compareTo);
                    allLenguage = result;
                    languageInterface.showLangs(result);

                }).isDisposed();
    }
}

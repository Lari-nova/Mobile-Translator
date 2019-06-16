package com.example.mytranslator.ui;

import com.example.mytranslator.entity.Translation;
import com.example.mytranslator.manager.ApplicationStateManager;
import com.example.mytranslator.manager.StateManager;
import com.example.mytranslator.network.InterfaceNetworkManager;
import com.example.mytranslator.network.NetworkManager;
import com.example.mytranslator.resource.Constants;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ComponentPresenter implements Component {

    private ViewComponentInterface componentInterface;
    private InterfaceNetworkManager networkManager;

    private StateManager stateManager;
    private String originalKey;
    private String originalValue;
    private String translationKey;
    private String translationValue;

    public ComponentPresenter(InterfaceNetworkManager networkManager) {
        stateManager = ApplicationStateManager.create();
        this.networkManager = networkManager;
    }

    @Override
    public void addComponentInterface(ViewComponentInterface componentInterface) {
        this.componentInterface = componentInterface;
    }

    @Override
    public void onStartView() {
        initializeState();
    }

    @Override
    public void translateText(String text) {
        networkManager.subscribeTranslation(Constants.API_KEY, text, originalKey + "-" + translationKey, "plain", 1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Translation translation) -> {
                    componentInterface.showTranslation(translation.getText().get(0));
                }).isDisposed();
    }

    private void initializeState() {
        originalKey = stateManager.receiveOriginalKey();
        originalValue = stateManager.receiveOriginalValue();
        translationKey = stateManager.receiveTranslationKey();
        translationValue = stateManager.receiveTranslationValue();
        componentInterface.addTextOnButton(originalValue, translationValue);
    }
}
